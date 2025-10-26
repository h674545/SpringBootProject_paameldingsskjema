<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="no">
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/simple.css">
	<title>Påmeldingsbekreftelse</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/simple.css">
</head>
<body>
<h2>Påmeldingsbekreftelse</h2>

<c:if test="${not empty deltager}">
	<p>Påmeldingen er mottatt for:</p>
	<p>
		<c:out value="${deltager.fornavn}" /> <br/>
		<c:out value="${deltager.etternavn}" /> <br/>
		<c:out value="${deltager.mobil}" /> <br/>
		<c:out value="${deltager.kjonn}" />
	</p>
</c:if>

<c:if test="${empty deltager}">
	<p>Ingen påmelding mottatt. Gå tilbake for å registrere påmelding.</p>
</c:if>

<p><a href="${pageContext.request.contextPath}/deltagerliste">Gå til deltagerlisten</a></p>
</body>
</html>

