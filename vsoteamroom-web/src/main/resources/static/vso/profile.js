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
    	
    	$('#name').val(data.surname);
    	$('#vorname').val(data.name);
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
    	
    	if(!data2 || data2 == 'ERROR'){
    		$('#errorText').append('Profil nicht gespeichert, überprüfen Sie Ihre Eingaben!');
    		$('.alert-danger').show();
    		return;
    	}
    	
    	if(data2 == 'SUCCESS'){
    		$('#errorText').empty();
    		$('.alert-danger').hide();
    		$('.alert-success').show();
    		refreshUser(user);
    	}
    	
    	
       
    });
	
	
}

function refreshUser(currentUser){
	$.ajax({
        url: "http://localhost:8080/vso/users/loadProfile?user="+currentUser.username
    }).then(function(data) {
    	if(!data){
    		return;
    	}
    	Cookies.remove('currentUser');
    	Cookies.set('currentUser', data);
    	
    	 	
       
    });
}

function closeAlert(){
	$('#errorText').empty();
	$('.alert').hide();
}