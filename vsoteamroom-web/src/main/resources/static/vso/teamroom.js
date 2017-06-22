/**
 * 
 */


$(document).ready(function() {
	
	$.ajax({
        url: "http://localhost:8080/vso/teamrooms/findAllTeamrooms"
    }).then(function(data) {
    	$('.table').append('<tbody>');
	    	$.each(data, function(index, value){
	    		$('.table').append('<tr><td>'+value.id+'</td><td>'+value.roomname+'</td><td><a href="http://localhost:8080/vso/teamrooms/download?room='+
	    				value.id+'" target="_blank">export</a></td>');
	    	});
    	$('.table').append('</tbody>');
    	 	
       
    });
});


function reload(){
	$.ajax({
        url: "http://localhost:8080/vso/teamrooms/findAllTeamrooms"
    }).then(function(data) {
    	$('.table > tbody').empty();
	    	$.each(data, function(index, value){
	    		$('.table').append('<tr><td>'+value.id+'</td><td>'+value.roomname+'</td><td><a href="http://localhost:8080/vso/teamrooms/download?room='+
	    				value.id+'" target="_blank">export</a></td>');
	    	});
    	 	
       
    });
}

function saveTeamroom(){
	var option = $('input[name=optradio]:checked').val();
	var enteredName = $('#name').val();
	
	var input = document.getElementById('userInput');
//	if(option == 'edit'){
//		alert('Funktion nicht implementiert');
//		return;
//	}
	
	if (!input.files) {
	    }
	else if (!input.files[0]) {
		sendTeamroomToBackend(enteredName, '', option);
	    }
	else{
		var file = $('#userInput').prop('files')[0];
		var fr = new FileReader();
		fr.readAsText(file);
		fr.onload = function() {
			var users = fr.result;
			sendTeamroomToBackend(enteredName, users, option);
		}
	}
}

function sendTeamroomToBackend(roomname, users, option){
	$.ajax({
		method: "POST",
        url: "http://localhost:8080/vso/teamrooms/create",
        data: { room: roomname, userdata: users, selected: option }
    }).then(function(data) {
    	if(data == "SUCCESS"){
    		reload();
    		$('#errorText').empty();
    		$('.alert-danger').hide();
    		$('.alert-success').show();
    	}else{
    		$('#errorText').append('Der Name existiert bereits');
    		$('.alert-danger').show();
    	} 	
       
    });
	
}