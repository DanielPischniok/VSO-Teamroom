/**
 * 
 */


$(document).ready(function() {
	
	$.ajax({
        url: "http://localhost:8080//vso/teamrooms/findAllTeamrooms"
    }).then(function(data) {
    	$('.table').append('<tbody>');
	    	$.each(data, function(index, value){
	    		$('.table').append('<tr><td>'+value.id+'</td><td>'+value.roomname+'</td><td>export</td>');
	    	});
    	$('.table').append('</tbody>');
    	 	
       
    });
});


function reload(){
	$.ajax({
        url: "http://localhost:8080//vso/teamrooms/findAllTeamrooms"
    }).then(function(data) {
    	$('.table > tbody').empty();
	    	$.each(data, function(index, value){
	    		$('.table').append('<tr><td>'+value.id+'</td><td>'+value.roomname+'</td><td>export</td>');
	    	});
    	 	
       
    });
}

function saveTeamroom(){
	var option = $('input[name=optradio]:checked').val();
	var enteredName = $('#name').val();
	
	var input = document.getElementById('userInput');
	if(option == 'edit'){
		alert('Funktion nicht implementiert');
		return;
	}
	
	if (!input.files) {
	    }
	else if (!input.files[0]) {
		sendTeamroomToBackend(enteredName, '');
	    }
	else{
		var file = $('#userInput').prop('files')[0];
		var fr = new FileReader();
		fr.readAsText(file);
		fr.onload = function() {
			var users = fr.result;
			sendTeamroomToBackend(enteredName, users);
		}
	}
}

function sendTeamroomToBackend(roomname, users){
	$.ajax({
        url: "http://localhost:8080/vso/teamrooms/create?roomname="+roomname+"&userdata="+encodeURIComponent(users)
    }).then(function(data) {
    	if(data == "SUCCESS"){
    		reload();
    	}else{
    		alert('Der Teamroom existiert bereits');
    	} 	
       
    });
	
}