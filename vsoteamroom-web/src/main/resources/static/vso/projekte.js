/**
 * 
 */
$(document).ready(function() {
	
	$.ajax({
        url: "http://localhost:8080/vso/projects/loadAll"
    }).then(function(data) {
	    	$.each(data, function(index, value){
	    		$('.list-group').append('<li class="list-group-item"><span class="glyphicon glyphicon-chevron-right"></span><a href="/" data-toggle="tooltip" title="Alle Aufgaben aus '+value.projectDefinition.projectName+' anzeigen">'+value.projectDefinition.projectName+'</a> | <a href="aufgabe-erstellen.html">Aufgabe erstellen</a> | <a href="/">Verwalten</a></li>');
	    	});
    	 	
       
    });
});


//<li class="list-group-item"><span class="glyphicon glyphicon-chevron-right"></span><a href="/" data-toggle="tooltip" title="Alle Aufgaben aus {project_name} anzeigen">{project_name}</a> | <a href="aufgabe-erstellen.html">Aufgabe erstellen</a> | <a href="/">Verwalten</a></li>