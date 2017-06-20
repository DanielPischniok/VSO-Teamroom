(function( $ ) {
    $.fn.makeSortable = function() {
        return this.each(function() {					
        	var tbody = $(this).children('tbody')[0];  
        	var cachedTableHTML = tbody.innerHTML;   
			var priority = [];	

        	var priorityComparator = function(tr1, tr2) {			
        		var tds1 = $(tr1).find('td').toArray();
        		var tds2 = $(tr2).find('td').toArray();
        		for (var i = 0; i < priority.length; i++) {
        			if( tds1[priority[i]].innerText == tds2[ priority[i]].innerText ) {
        				continue;
        			} else {
        				return (tds1[ priority[i] ].innerText > tds2[ priority[i]].innerText) ? 1 : -1;
        			}
        		}
        		return 0;
        	};

        	var refresh = function(trs) {
        		$(tbody).empty();
        		trs.forEach(function(tr) {
        			$(tbody).append(tr);
        		});
        	};

        	$(this).find('th').bind("click", function() {
        		thIndex = $(this).index();
        		priorityIndex = priority.indexOf(thIndex);
        		if (priorityIndex == -1) {
        			priority.push(thIndex);
	            	$(this).css('box-shadow', 'inset 0px 0px 6px 1px #ddd');
        		} else {
        			priority.splice(priorityIndex, 1);
	            	$(this).css('box-shadow', 'none');
        		}
        		tbody.innerHTML = cachedTableHTML;
        		if (priority.length != 0) {
					var tr =  $(tbody).find('tr')
	        						  .toArray()
	        						  .sort(priorityComparator);
		        	refresh(tr);
        		}       		
		}); 
    	});
    }; 
}( jQuery ));
