/**
 * 
 */
$(document).ready(function() {
	
	$.ajax({
        url: "http://localhost:8080/vso/users/findAllUsers"
    }).then(function(data) {
    	$('.table').append('<tbody>');
	    	$.each(data, function(index, value){
	    		$('.table').append('<tr><td>'+value.surname+' '+value.name+'</td><td>'+value.phonenumber+
	    						  '</td><td>'+value.email+'</td></tr>');
	    	});
    	$('.table').append('</tbody>');
    	 	
       
    });
});

