<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<!-- Optional theme -->
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="resources/css/basic.css">
	<link rel="stylesheet" href="resources/css/dropzone.css">
	<title>Home</title>
</head>
<body>
	<div id="header" class="center-block">
		<h1>Importation</h1>
	</div>
	<div id="contenu" style="width:100%">
		<div class="row">
			<form:form  method="POST" action="${pageContext.request.contextPath}/import" role="form" id="dropzone-form"
			class="col-sm-12 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 dropzone">
				<div class="form-group">
					<label for="usage">Usage</label>
					<input type="text" class="form-control" id="usage" placeholder="Entrez un usage">
				</div>
				<div class="form-group">
					<label for="examen">Examen</label>
					<input type="text" class="form-control" id="examen" placeholder="Entrez le nom de l'examen">
				</div>
				<div class="dropzone-previews"></div> <!-- this is were the previews should be shown. -->
				<button type="submit" class="btn btn-default">Envoyer</button>
				
			</form:form>
		</div>
	</div>

</body>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="resources/js/dropzone.js"></script>
<script src="resources/js/import.js"></script>
</html>
