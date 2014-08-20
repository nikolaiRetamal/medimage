<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Importation</title>
	<!-- Imports CSS -->
	<link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="resources/css/dropzone.css">
	<link rel="stylesheet" href="resources/css/basic.css">
</head>
<body>
	<div id="header" class="center-block">
		<h1>Importation</h1>
	</div>
	<div id="contenu" style="width:100%">
		<div class="row">
			<form:form  id="dropzone-form" action="/medimage/upload"
			class="col-sm-12 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
				<div class="fallback">
			    	<input name="file" type="file" multiple />
				</div>
				<div class="form-group">
					<label for="usage">Usage</label>
					<input type="text" class="form-control" id="usage" name="usage" placeholder="Entrez un usage">
				</div>
				<div class="form-group">
					<label for="examen">Examen</label>
					<input type="text" class="form-control" id="examen" name="examen" placeholder="Entrez le nom de l'examen">
				</div>
				<button type="submit" class="btn btn-default">Envoyer</button>
				<div class="dz-default dz-message file-dropzone text-center well col-sm-12">
					<span class="glyphicon glyphicon-paperclip"></span> 
					<span>
						Glissez ici les fichiers .dcm
					</span><br>
					<span>OU</span><br>
					<span>Cliquez pour parcourir</span>
                </div>
				<div class="dropzone-previews"></div>
			</form:form>
		</div>
	</div>

</body>
<!-- Imports JS -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/dropzone.js"></script>
<script src="resources/js/import.js"></script>
</html>
