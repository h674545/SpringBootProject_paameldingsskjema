<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="no">
<head>
	<meta charset="UTF-8">
	<title>logginn</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/simple.css">
</head>

<body>
	<h2>Logg inn</h2>

	<c:if test="${feil}">
		<p style="color:red;"><c:out value="${errorMessage}" /></p>
	</c:if>

<c:url var="postUrl" value="/innlogging"/>
<form action="${postUrl}" method="post">

    Mobilnummer:
    <input type="text" name="mobil" placeholder="Mobilnummer" required pattern="\d{8}" /><br/>

    Passord:
    <input type="password" id="passord" name="passord" placeholder="Passord" required minlength="8"/><br/>

    <button type="submit">Logg inn</button>
</form>
</body>
</html>