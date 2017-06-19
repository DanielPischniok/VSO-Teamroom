/**
 * 
 */
$(document).ready(function() {
	
	var myParam = location.search.split('task=')[1];
	
	if(!myParam){
		return;
	}
	
	$.ajax({
        url: "http://localhost:8080/vso/tasks/findById?id="+myParam
    }).then(function(data) {
    	if(!data){
    		return;
    	}
    	
    	var startDate = new Date(data.startDate);
    	var endDate = new Date(data.endDate);
    	$('h2').append('"'+data.tasktitle+'"');
    	$('#breadcrumpLink').append(data.tasktitle);
    	$('#project').append('<option value="1">'+data.project.projectDefinition.projectName+'</option>');
    	$('#category').append('<option value="1">'+data.type+'</option>');
    	$('#thema').val(data.tasktitle);
    	$('#beschreibung').append(data.description);
    	$('#prio').append('<option value="1">'+data.priority+'</option>');
    	$('#time').val(data.estimation+' h');
    	$('#assignee').val(data.assignee.username);
    	$('#vontime').val(startDate.toLocaleDateString("de-DE"));
    	$('#bistime').val(endDate.toLocaleDateString("de-DE"));
       
    });
	
	$.ajax({
        url: "http://localhost:8080/vso/tasks/loadComments?taskid="+myParam
    }).then(function(data) {
    	if(!data){
    		return;
    	}
    	
    	$.each(data, function(index, value){
    		var createDate = new Date(value.createDate);
    		
    		$('#1a').append('<div class="user_comment">'+
										'<div class="user_comment_name">'+value.createdBy.username+'</div>'+
										'<div class="user_comment_text">'+value.text+'</div>'+
										'<div class="user_comment_date small">'+createDate.toLocaleDateString("de-DE")+'</div></div><hr>');
    	});
       
    });
});


function addOwnComment(){
    var myParam = location.search.split('task=')[1];
	
	if(!myParam){
		return;
	}
	
	currentUser = Cookies.get('currentUser');
    if(!currentUser){
    	location.href = 'index.html';
    	return;
    }
    var user = $.parseJSON(currentUser);
	var comment = $('#comment').val();
    
	$.ajax({
		method: "POST",
        url: "http://localhost:8080/vso/tasks/createComment",
        data: { task: myParam, user: user.username, text: comment }
    }).then(function(data2) {
    	$('#comment').val('')
    	$.ajax({
            url: "http://localhost:8080/vso/tasks/loadComments?taskid="+myParam
        }).then(function(data) {
        	if(!data){
        		return;
        	}
        	$('.user_comment').nextAll('hr').remove();
        	$('.user_comment').remove();
        	$.each(data, function(index, value){
        		var createDate = new Date(value.createDate);
        		
        		$('#1a').append('<div class="user_comment">'+
    										'<div class="user_comment_name">'+value.createdBy.username+'</div>'+
    										'<div class="user_comment_text">'+value.text+'</div>'+
    										'<div class="user_comment_date small">'+createDate.toLocaleDateString("de-DE")+'</div></div><hr>');
        	});
           
        });
       
    });
}