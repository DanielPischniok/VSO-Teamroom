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