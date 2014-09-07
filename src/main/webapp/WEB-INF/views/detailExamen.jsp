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
	
		<div class="descriptionExamen">
		
		</div>
		<!-- Utiliser http://fooplugins.com/plugins/footable-jquery/#/ -->
		<table id="dicoms">
			<thead>
				<tr>
				<th data-toggle="true">
			    	Examen
				</th>
				<th data-sort-ignore="true">
					Usage
				</th>
				<th data-hide="phone,tablet">
					Date
				</th>
				<th data-hide="phone,tablet" data-name="Date Of Birth">
					DOB
				</th>
				<th data-hide="phone">
				  Status
				</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dicom" items="${dicoms}">
					<tr>
						<c:out value="${dicom.nom_examen}"/>
					</tr>
					<tr>
						<c:out value="${dicom.nom_usage}"/>
					</tr>
					<tr>
						<c:out value="${dicom.date}"/>
					</tr>
					<tr>
						<c:out value="${dicom.tags}"/>
					</tr>
					<tr>
						<c:out value="${dicom.metas}"/>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>