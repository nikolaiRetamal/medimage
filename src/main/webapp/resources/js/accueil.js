var metas = new Array();
var tagList;
window.onload = function() {
	
	$('#ajoutMeta').on('click', function(){
		var newMeta = '<span class="metadata">'+ $('#metaKey').val() + ' : '+ $('#metaValue').val()+'</span>';
		$('#metaKey').val('');
		$('#metaValue').val('');
		$('#listeMetadatas').append(newMeta);
	});
	
    $( "#tags" ).autocomplete({source: function (request, response) {
        $.ajax({
            url: "/medimage/getTags",
            dataType: "json",	            
            data: {
            	term: request.term
            },
            success: function (data) {
            	//L'ajax renvoie une map<String,String> contenant les 
            	//descriptor ou synonym contenant la chaîne associés à leur DescriptorUI
            	tagList = data;
            	//On sauve la liste puisqu'il faudra retrouver les DescriptorUI
	  			console.log("Retour de recherche : "+data.length+" "+data);
            	if(jQuery.isEmptyObject(data)){
            		//En cas de retour vide, on considère que c'est une saisie libre
            		//on propose la saisie de l'utilisateur
		  			console.log("Recherche vide");
		  			response([request.term]);
            	}else{
            		//On renvoie une liste ne comprenant que les noms
		  			console.log("Recherche réussie !");
	            	response($.map( data, function(value, key ) {return key;}));	            		
            	}	            
            },
            error: function (message) {
	  			console.log("Retour nok...");
	  			console.log(message);
            }
        });
    },
    autoFill: true,
    mustMatch: true,
    selectFirst: true,
    minLength: 4,
      select: function( event, ui ) {
    	  var choix = '<span class="tag">'+ui.item.value +'</span>';
          console.log( "le User a choisi"+ choix);
          $('#chosenTags').append(choix);
          //Par défaut on sauvera la valeur saisie : Saisie libre
          var val = choix;	          
          if(!jQuery.isEmptyObject(tagList)){
        	 //Si tagList n'est pas vide on sauvera le DescriptorUI
    	    val = $.map( tagList, function(value, key ) { 
        	  if(choix==key){
        		  return value;
        	  }
        	}); 
          } 
          $('#chosenTagsValue').val($('#chosenTagsValue').val()+val+'\n'); 
          console.log( "Ce qui correspond à "+ val);
        }
    });
    $("#usage").autocomplete({source: function (request, response) {
        $.ajax({
            url: "/medimage/getUsages",
            dataType: "json",	            
            data: {
            	term: request.term
            },
            success: function (data) {
            	//L'ajax renvoie une map<String,String> contenant les 
            	//descriptor ou synonym contenant la chaîne associés à leur DescriptorUI
            	tagList = data;
            	//On sauve la liste puisqu'il faudra retrouver les DescriptorUI
	  			console.log("Retour de recherche : "+data.length+" "+data);
            	if(jQuery.isEmptyObject(data)){
            		//En cas de retour vide, on considère que c'est une saisie libre
            		//on propose la saisie de l'utilisateur
		  			console.log("Recherche vide");
		  			response([request.term]);
            	}else{
            		//On renvoie une liste ne comprenant que les noms
		  			console.log("Recherche réussie !");
	            	response($.map( data, function(value, key ) {return key;}));	            		
            	}	            
            },
            error: function (message) {
	  			console.log("Retour nok...");
	  			console.log(message);
            }
        });
    },
    autoFill: true,
    mustMatch: true,
    selectFirst: true,
    minLength: 4/*,
      select: function( event, ui ) {
    	  var choix = '<span class="tag">'+ui.item.value +'</span>';
          console.log( "le User a choisi"+ choix);
          $('#chosenTags').append(choix);
          //Par défaut on sauvera la valeur saisie : Saisie libre
          var val = choix;	          
          if(!jQuery.isEmptyObject(tagList)){
        	 //Si tagList n'est pas vide on sauvera le DescriptorUI
    	    val = $.map( tagList, function(value, key ) { 
        	  if(choix==key){
        		  return value;
        	  }
        	}); 
          } 
          $('#chosenTagsValue').val($('#chosenTagsValue').val()+val+'\n'); 
          console.log( "Ce qui correspond à "+ val);
        }*/
    });
}