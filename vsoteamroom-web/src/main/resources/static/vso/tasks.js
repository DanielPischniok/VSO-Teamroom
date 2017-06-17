/**
 * 
 */
$(document).ready(function() {
	
	$.ajax({
        url: "http://localhost:8080/vso/tasks/findAllTasks"
    }).then(function(data) {
    	$('.table').append('<tbody>');
	    	$.each(data, function(index, value){
	    		var createDate = new Date(value.createDate);
	    		var updateDate = new Date(value.updateDate);
	    		var endDate = new Date(value.endDate);
	    		$('.table').append('<tr><td>'+value.id+'</td><td>'+value.type+
	    						  '</td><td>'+value.project.projectDefinition.projectName+
	    						  '</td><td>'+value.reporter.username+
	    						  '</td><td>'+value.assignee.username+
	    						  '</td><td>'+createDate.toLocaleDateString("de-DE")+
	    						  '</td><td>'+updateDate.toLocaleDateString("de-DE")+
	    						  '</td><td>'+value.status+
	    						  '</td><td>'+value.priority+
	    						  '</td><td>'+endDate.toLocaleDateString("de-DE")+
	    						  '</td><td>'+value.percentageOfCompletion+
	    				          '</td><td><a href="aufgaben-leseansicht.html?task='+value.id+'">edit</a></td>');
	    	});
    	$('.table').append('</tbody>');
    	 	
       
    });
});