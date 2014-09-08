window.onload = function() {

	$("#dropzone-form").dropzone({
		  // The configuration we've talked about above
		  url:"/medimage/upload",
		  autoProcessQueue: false,
		  autoDiscover: false,
		  uploadMultiple: true,
		  parallelUploads: 1000,
		  previewsContainer: ".dropzone-previews",
		  maxFiles: 2000,
		
		  // The setting up of the dropzone
		  	init: function() {
			    var myDropzone = this;
			    console.log("hello");
				// First change the button to actually tell Dropzone to process the queue.
				$("button[type=submit]").on('click',function(e) {
				// Make sure that the form isn't actually being sent.
					e.preventDefault();
					e.stopPropagation();
					console.log("Je lance l'upload");
					myDropzone.processQueue();
				});
		
				// Listen to the sendingmultiple event. In this case, it's the sendingmultiple event instead
				// of the sending event because uploadMultiple is set to true.
				this.on("sendingmultiple", function() {
					console.log("sendingmultiple");
				});
				this.on("successmultiple", function(files, response) {
					console.log("successmultiple");
				});
				this.on("errormultiple", function(files, response) {
					console.log("error");
				});
		  	}
	});
	
	
	$(function() {
	    $( "#tags" ).autocomplete({
	      source: function(request, response) {
		            $.ajax({
	              url: "/medimage/getTags",
	  			  data:request,
		  		   transformResult: function(response) {		  	 
			  			return {      	
			  			  //must convert json to javascript object before process
			  			  suggestions: $.map($.parseJSON(response), function(item) {
			  				  console.log(response);
			  			      return { value: item.nom, data: item.idTag };
			  			   })
			  	 
			  			 };
		  	 
		  	            }
	          });
	      },
	      minLength: 3,
	      select: function( event, ui ) {
	          log( ui.item ?
	            "Selected: " + ui.item.value + " aka " + ui.item.id :
	            "Nothing selected, input was " + this.value );
	        }
	      });
	    });
	
	
}