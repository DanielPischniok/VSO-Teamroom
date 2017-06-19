/**
 * 
 */
$(document).ready(function() {
	
	currentUser = Cookies.get('currentUser');
	if(!currentUser){
    	location.href = 'index.html';
    	return;
    }
    var user = $.parseJSON(currentUser);
	
    
	$.ajax({
        url: "http://localhost:8080/vso/users/loadProfile?user="+user.username
    }).then(function(data) {
    	if(!data){
    		return;
    	}
    	
    	$('#name').val(data.name);
    	$('#vorname').val(data.surname);
    	$('#inputPhone').val(data.phonenumber);
    	$('#inputEmail3').val(data.email);
    	
    	 	
       
    });
});


function saveProfile(){
	currentUser = Cookies.get('currentUser');
	if(!currentUser){
    	location.href = 'index.html';
    	return;
    }
    var user = $.parseJSON(currentUser);
	var name = $('#name').val();
	var vorname = $('#vorname').val();
	var phone = $('#inputPhone').val();
	var email = $('#inputEmail3').val();
	var pass1 = $('#inputPassword3').val();
	var pass2 = $('#inputPassword4').val();
	
	$.ajax({
		method: "POST",
        url: "http://localhost:8080/vso/users/saveProfile",
        data: { username:user.username, nachname: name, uservorname: vorname, userphone: phone, useremail: email, userpass1: pass1, userpass2: pass2 }
    }).then(function(data2) {
    	
       
    });
	
	
}