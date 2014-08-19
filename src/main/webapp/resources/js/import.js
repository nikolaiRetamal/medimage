window.onload = function() {
	

	
	$("#dropzone-form").dropzone({
		  // The configuration we've talked about above
		  url:"/medimage/import",
		  autoProcessQueue: false,
		  autoDiscover: false,
		  uploadMultiple: true,
		  parallelUploads: 100,
		  previewsContainer: ".dropzone-previews",
		  maxFiles: 100,
		
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
					setTimeout(function(){
						console.log("effective!");
						myDropzone.processQueue();
					}, 1500);
					
				});
		
				// Listen to the sendingmultiple event. In this case, it's the sendingmultiple event instead
				// of the sending event because uploadMultiple is set to true.
				this.on("sendingmultiple", function() {
				  // Gets triggered when the form is actually being sent.
				  // Hide the success button or the complete form.
				});
				this.on("successmultiple", function(files, response) {
				  // Gets triggered when the files have successfully been sent.
				  // Redirect user or notify of success.
				});
				this.on("errormultiple", function(files, response) {
				  // Gets triggered when there was an error sending the files.
				  // Maybe show form again, and notify user of error
				});
		  	}
	});

	Dropzone.autoDiscover = false;
}