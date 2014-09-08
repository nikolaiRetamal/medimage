<%@page import="org.springframework.context.annotation.Import"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>{title}</title>
	<!-- Imports CSS -->
	<link href="resources/bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="resources/css/dropzone.css">
	<link rel="stylesheet" href="resources/css/basic.css">
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="contenu">
		<h1 class="titreRecherche">MEDIMAGE</h1>
		<div class="row">
			<form:form  id="recherche" action="/medimage/recherche"
			class="col-sm-12 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
				<div class="form-group">
					<label for="usage">Usage</label>
					<input type="text" class="form-control" id="usage" name="usage" placeholder="Entrez un usage">
				</div>
				<div class="form-group">
					<label for="metas">Mots-Clés</label>
					<!-- SELECT SUR LISTE de META -->
					<input type="text" class="form-control" id="metas" name="metas" placeholder="Entrez des métadonnées">
				</div>
				<div class="ui-widget" style="margin-top:2em; font-family:Arial">
				  	Métadonnées:
				  	<div id="log" style="height: 200px; width: 300px; overflow: auto;" class="ui-widget-content"></div>
				</div>
				<div class="form-group">
					<label for="tags">Mots-Clés</label>
					<input type="text" class="form-control" id="tags" name="tags" placeholder="Entrez des mots-clés">
				</div>
				<div class="ui-widget" style="margin-top:2em; font-family:Arial">
				  	Mots-Clés:
				  	<div id="log" style="height: 200px; width: 300px; overflow: auto;" class="ui-widget-content"></div>
				</div>
				<button type="submit" class="btn btn-default">Envoyer</button>
			</form:form>
		</div>
	</div>
</body>
</html>