<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="no">
<head>
	<meta charset="UTF-8">
	<title>Deltagerliste</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/simple.css">
</head>
<body>

<h2>Deltagerliste</h2>

<c:if test="${empty deltagere}">
	<p>Ingen påmeldte deltagere enda.</p>
</c:if>

<c:if test="${not empty deltagere}">
<table>
	<thead>
	<tr>
		<th>Fornavn</th>
		<th>Etternavn</th>
		<th>Mobil</th>
		<th>Kjønn</th>
	</tr>
	</thead>
	<tbody>
		<c:forEach var="d" items="${deltagere}">
			<tr>
				<td><c:out value="${d.fornavn}" default="(ikke oppgitt)" /></td>
				<td><c:out value="${d.etternavn}" default="(ikke oppgitt)" /></td>
				<td><c:out value="${d.mobil}" default="(ikke oppgitt)" /></td>
				<td><c:out value="${d.kjonn}" default="(ikke oppgitt)" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>

<p><a href="${pageContext.request.contextPath}/paamelding">Ny påmelding</a></p>
</body>
</html>
