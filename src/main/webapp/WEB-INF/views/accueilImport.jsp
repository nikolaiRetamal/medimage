<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Latest compiled and minified CSS -->
		<link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
	<!-- Optional theme -->
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="resources/css/dropzone.css">
	<link rel="stylesheet" href="resources/css/basic.css">
	<title>Home</title>
</head>
<body>
	<div id="header" class="center-block">
		<h1>Importation</h1>
	</div>
	<div id="contenu" style="width:100%">
		<div class="row">
			<!--<form:form  method="POST" action="${pageContext.request.contextPath}/upload" role="form"
			class="col-sm-12 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
				<div class="form-group">
					<label for="usage">Usage</label>
					<input type="text" class="form-control" id="usage" placeholder="Entrez un usage">
				</div>
				<div class="form-group">
					<label for="examen">Examen</label>
					<input type="text" class="form-control" id="examen" placeholder="Entrez le nom de l'examen">
				</div>
				<button type="submit" class="btn btn-default">Envoyer</button>
			</form:form>-->
			
			<form:form action="${pageContext.request.contextPath}/upload" id="dropzone-form"
			class="col-sm-12 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 dropzone"
			style="background:yellow;height:100px;"
			method="POST" enctype="multipart/form-data">
				<div class="fallback">
			    	<input name="file" type="file" multiple />
				</div>
				<div class="dropzone-previews"></div>
				<button type="submit" class="btn btn-default">Envoyer</button>
			</form:form>
			
			<!--<form:form action="${pageContext.request.contextPath}/upload" 
			style="width:500px;padding:20px" enctype="multipart/form-data">
 
			    <input id="fileupload" type="file" name="files[]"  multiple>
				<button type="submit" class="btn btn-default">Envoyer</button>
			 
			</form:form>-->
		</div>
	</div>

</body>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/js/dropzone.js"></script>
<!--<script src="resources/js/jquery.ui.widget.js"></script>
<script src="resources/js/jquery.iframe-transport.js"></script>
<script src="resources/js/jquery.fileupload.js"></script>-->
<script src="resources/js/import.js"></script>
</html>
