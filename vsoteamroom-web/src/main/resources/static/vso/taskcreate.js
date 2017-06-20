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
    $('#username').html(user.name);
    
    
    //lade Projektnamen
    
    $.ajax({
        url: "http://localhost:8080/vso/projects/loadAll"
    }).then(function(data) {
    	jQuery.each(data, function(index, value){
	    		
    			jQuery('#project').append('<option value="'+value.projectDefinition.projectName+'">'+value.projectDefinition.projectName+'</option>')
	    	});
    	 	
       
    });
    
    $.ajax({
        url: "http://localhost:8080/vso/users/findAllUsers"
    }).then(function(data) {
    	var allusers = [];
    	jQuery.each(data, function(index, value){
	    		allusers.push(value.username);
	    	});
    	jQuery('#assignee').autocomplete({
    	      source: allusers
        });
    	
    	jQuery('#watchers').autocomplete({
  	      source: allusers
      });
    });
    
});


function createTask(){
	currentUser = Cookies.get('currentUser');
    if(!currentUser){
    	location.href = 'index.html';
    	return;
    }
    var user = jQuery.parseJSON(currentUser);
    
    var project = jQuery('#project').val();
    var category = jQuery('#category').val();
    var title = jQuery('#thema').val();
    var description = jQuery('#beschreibung').val();
    var prio = jQuery('#prio').val();
    var time = jQuery('#time').val();
    var assignee = jQuery('#assignee').val();
    var watcher = jQuery('#watchers').val();
    var vondate = jQuery('#vondate').val();
    var bisdate = jQuery('#bisdate').val();
    
    jQuery.ajax({
		method: "POST",
        url: "http://localhost:8080/vso/tasks/createTask",
        data: { user: user.username, userproject: project, usercategory: category, usertitle: title, userdescr: description, userprio: prio, 
        			usertime: time, userassignee: assignee, userwatcher: watcher, uservondate: vondate, userbisdate: bisdate}
    }).then(function(data2) {
       if(data2 == 'SUCCESS'){
    	   location.href = 'aufgaben-uebersicht.html';
       	return;
       }else{
    	   alert('Speicher nicht erfolgreich, bitte kontrollieren Sie alle Daten');
       }
    });
}