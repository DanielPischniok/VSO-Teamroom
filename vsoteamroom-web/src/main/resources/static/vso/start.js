/**
 * 
 */
$(document).ready(function() {
	
	$.ajax({
        url: "http://localhost:8080/vso/tasks/findLatestTasks"
    }).then(function(data) {
    	$('#lastTasks').append('<tbody>');
	    	$.each(data, function(index, value){
	    		var updateDate = new Date(value.updateDate);
	    		$('#lastTasks').append('<tr><td>'+value.type+'</td><td>'+value.project.projectDefinition.projectName+
	    						  '</td><td>'+value.reporter.username+
	    						  '</td><td>'+updateDate.toLocaleDateString("de-DE")+
	    						  '</td></tr>');
	    	});
    	$('#lastTasks').append('</tbody>');
    	 	
       
    });
	
	currentUser = Cookies.get('currentUser');
	if(!currentUser){
    	location.href = 'index.html';
    	return;
    }
    var user = $.parseJSON(currentUser);
	
	$.ajax({
        url: "http://localhost:8080/vso/tasks/findLatestUserTasks?user="+user.username
    }).then(function(data2) {
    	$('#lastUserTasks').append('<tbody>');
	    	$.each(data2, function(index1, value1){
	    		var updateDate1 = new Date(value1.updateDate);
	    		$('#lastUserTasks').append('<tr><td>'+value1.type+'</td><td>'+value1.project.projectDefinition.projectName+
	    						  '</td><td>'+value1.reporter.username+
	    						  '</td><td>'+value1.assignee.username+
	    						  '</td><td>'+updateDate1.toLocaleDateString("de-DE")+
	    						  '</td></tr>');
	    	});
    	$('#lastUserTasks').append('</tbody>');
    	 	
       
    });
});

