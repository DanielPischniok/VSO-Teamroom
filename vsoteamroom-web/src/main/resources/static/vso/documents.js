$(document).ready(function() {
	
	$.ajax({
        url: "http://localhost:8080/vso/artifact/loadAll"
    }).then(function(data) {
    	var treeitems = [];
    	var itemarray = [];
    	if(data.length == 0){
    		return;
    	}
    	
    	while(itemarray.length != data.length){
    		
	    	$.each(data, function(index, value){
	    		 if(!value.parentFolder){
	    			 var item = {name:value.folderName, childs:[], artifacts: value.artifacts}
	    			 treeitems.push(item);
	    			 itemarray.push(item);
	    		 }else{
	    			 $.each(treeitems, function(index, parent){
	    	    		 if(parent.name == value.parentFolder.folderName){
	    	    			 var item = {name:value.folderName, childs:[], artifacts: value.artifacts}
	    	    			 parent.childs.push(item);
	    	    			 itemarray.push(item);
	    	    		 }
	    	    	});
	    		 }
	    	});
    	}
    	
    	var treestring = ''
    	treestring += "<ul>";
    	$.each(treeitems, function(index, value){
    		treestring += "<li>" + value.name + "</li>";
    		if(value.artifacts.length > 0){
    			treestring += "<ul>";
	    			$.each(value.value.artifacts, function(index01, artifact){
	    				treestring += "<li>" +"<a href='#'>"+ artifact.title + "</a></li>";
	    			});
    			treestring += "</ul>";
    		}
	    			
    		if(value.childs.length > 0){
    			treestring += "<ul>";
    			$.each(value.childs, function(index1, child1){
    				treestring += "<li>" + child1.name + "</li>";
    				if(child1.artifacts.length > 0){
    	    			treestring += "<ul>";
    		    			$.each(child1.artifacts, function(index01, artifact){
    		    				treestring += "<li>" +"<a style='color:white' href='#'>"+ artifact.title + "</a></li>";
    		    			});
    		    				treestring += "</ul>";
    	    		}
    				if(child1.childs.length > 0){
    	    			treestring += "<ul>";
    	    			$.each(child1.childs, function(index2, child2){
    	    				treestring += "<li>" + child2.name + "</li>";
    	    				if(child1.artifacts.length > 0){
    	    	    			treestring += "<ul>";
    	    		    			$.each(child1.artifacts, function(index01, artifact){
    	    		    				treestring += "<li>" +"<a href='#'>"+ artifact.title + "</a></li>";
    	    		    			});
    	    		    				treestring += "</ul>";
    	    	    		}
    	    			});
    	    			treestring += "</ul>";
    	    		}
    			});
    			treestring += "</ul>";
    		}
    	});
    	treestring += "</ul>";
    	alert(treestring);
    	$('.easy-tree').append(treestring);
    });
});


