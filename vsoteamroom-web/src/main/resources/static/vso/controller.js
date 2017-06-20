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
    		alert('Login nicht erfolgreich')
    	} 
       
    	
       
    });
	
             
}


function logout(){
	currentUser = null;
	Cookies.remove('currentUser');
	location.href = 'index.html';
}