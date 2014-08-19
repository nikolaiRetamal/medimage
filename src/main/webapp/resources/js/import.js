window.onload = function() {
	

	
	$("#dropzone-form").dropzone({
		  // The configuration we've talked about above
		  url:"/medimage/aide",
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
    /*$('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
 
                $("#uploaded-files").append(
                        $('<tr/>')
                        .append($('<td/>').text(file.fileName))
                        .append($('<td/>').text(file.fileSize))
                        .append($('<td/>').text(file.fileType))
                        .append($('<td/>').html("<a href='medimage/get/"+index+"'>Click</a>"))
                        )//end $("#uploaded-files").append()
            });
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        },
 
        dropZone: $('#dropzone')
    });*/
}