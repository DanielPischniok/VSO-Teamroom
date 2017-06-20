//*** Global Variable

var currentUser = null;


function login() {
	var user = $('#exampleInputEmail1');
	var pass = $('#exampleInputPassword1');
	
	$.ajax({
        url: "http://localhost:8080/vso/auth/login?user="+user.val()+"&pass="+pass.val()
    }).then(function(data) {
    	
    	if(data){
    		currentUser = data;
    		Cookies.set('currentUser', data);
    		location.href = 'start.html';
    		
    		
    	}else{
    		currentUser = null;
    		Cookies.remove('currentUser');
    		$('#errorText').append('Login nicht erfolgreich, überprüfen Sie ihre Eingaben!');
    		$('.alert').show();
    	} 
       
    	
       
    });
	
             
}


function closeAlert(){
	$('#errorText').empty();
	$('.alert').hide();
}

function logout(){
	currentUser = null;
	Cookies.remove('currentUser');
	location.href = 'index.html';
}