$(document).ready(function() {
	currentUser = Cookies.get('currentUser');
    if(!currentUser){
    	location.href = 'index.html';
    	return;
    }
    var user = $.parseJSON(currentUser);
    $('#username').html(user.surname);
});