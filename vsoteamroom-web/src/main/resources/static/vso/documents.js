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
	    				treestring += "<li>" +"<a target='_blank' href='http://localhost:8080/vso/artifact/download?file="+artifact.id+"'>"+ artifact.title + "</a></li>";
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
    		    				treestring += "<li>" +"<a style='color:white' target='_blank' href='http://localhost:8080/vso/artifact/download?file="+artifact.id+"'>"+ artifact.title + "</a></li>";
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
    	    		    				treestring += "<li>" +"<a target='_blank' href='http://localhost:8080/vso/artifact/download?file="+artifact.id+"'>"+ artifact.title + "</a></li>";
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
    	$('.easy-tree').append(treestring);
    });
});


function searchDocument(){
	
	var filename = $('#searchinput').val();
	var url = "http://localhost:8080/vso/artifact/searchDocument?filename="+filename;
	if(!filename){
		url = "http://localhost:8080/vso/artifact/loadAll";
	}
	$.ajax({
        url: url
    }).then(function(data) {
    	var treeitems = [];
    	var itemarray = [];
    	if(data.length == 0){
    		$('.easy-tree').empty();
    		$('.easy-tree').append("Keine Dokumente gefunden");
    		return;
    	}
    	var treenodes = [];
    	while(itemarray.length != data.length){
	    	$.each(data, function(index, value){
	    		 if(!value.parentFolder){
	    			 var item = {text:value.folderName, children:[], artifacts: value.artifacts}
//	    			 if(value.artifacts){
//	    				 $.each(value.artifacts, function(index, artifact){
//		    	    		var artifact = {text:artifact.title, href: '#'};
//		    	    		item.children.push(artifact);
//		    	    	});
//	    			 }
	    			 treeitems.push(item);
	    			 itemarray.push(item);
	    			 treenodes.push(item);
	    		 }else{
	    			 var found = false;
	    			 $.each(treeitems, function(index, parent){
	    	    		 if(parent.text == value.parentFolder.folderName){
	    	    			 var item = {text:value.folderName, children:[], artifacts: value.artifacts}
//	    	    			 if(value.artifacts){
//	    	    				 $.each(value.artifacts, function(index, artifact){
//	    		    	    		var artifact = {text:artifact.title, href: '#'};
//	    		    	    		item.children.push(artifact);
//	    		    	    	});
//	    	    			 }
	    	    			 parent.children.push(item);
	    	    			 itemarray.push(item);
//	    	    			 treenodes.push(item);
	    	    			 treeitems.push(item);
	    	    			 found = true;
	    	    		 }
	    	    	});
	    			 if(!found){
	    				 var item = {text:value.parentFolder.folderName, children:[], artifacts: value.parentFolder.artifacts}
	    				 treenodes.push(item);
	    				 treeitems.push(item);
	    			 }
	    			 
	    			 
	    		 }
	    	});
    	}
    	
    	var json_data = JSON.stringify(treenodes);
//    	alert(json_data);
    	$('.easy-tree').empty();
//    	$('.easy-tree').append(json_data);
  //  	$('.easy-tree').EasyTree();

    	var treestring = ''
    	treestring += "<ul>";
    	$.each(treenodes, function(index, value){
    		treestring += "<li>" + value.text + "</li>";
    		if(value.artifacts.length > 0){
    			treestring += "<ul>";
	    			$.each(value.artifacts, function(index01, artifact){
	    				treestring += "<li>" +"<a target='_blank' href='http://localhost:8080/vso/artifact/download?file="+artifact.id+"'>"+ artifact.title + "</a></li>";
	    			});
    			treestring += "</ul>";
    		}
	    			
    		if(value.children.length > 0){
    			treestring += "<ul>";
    			$.each(value.children, function(index1, child1){
    				treestring += "<li>" + child1.text + "</li>";
    				if(child1.artifacts.length > 0){
    	    			treestring += "<ul>";
    		    			$.each(child1.artifacts, function(index01, artifact){
    		    				treestring += "<li>" +"<a style='color:white' target='_blank' href='http://localhost:8080/vso/artifact/download?file="+artifact.id+"'>"+ artifact.title + "</a></li>";
    		    			});
    		    				treestring += "</ul>";
    	    		}
    				if(child1.children.length > 0){
    	    			treestring += "<ul>";
    	    			$.each(child1.children, function(index2, child2){
    	    				treestring += "<li>" + child2.text + "</li>";
    	    				if(child1.artifacts.length > 0){
    	    	    			treestring += "<ul>";
    	    		    			$.each(child1.artifacts, function(index01, artifact){
    	    		    				treestring += "<li>" +"<a target='_blank' href='http://localhost:8080/vso/artifact/download?file="+artifact.id+"'>"+ artifact.title + "</a></li>";
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
    	$('.easy-tree').empty();
    	$('.easy-tree').append(treestring);
    });
};


