<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="no">
<head>
	<meta charset="UTF-8">
	<title>Påmelding</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/simple.css">

	<script>
	function validatePassword() {
		const passord = document.getElementById("passord").value;
		const passord2 = document.getElementById("passord2").value;
		const messageElement = document.getElementById("passwordMatchMessage");

		if (passord !== passord2) {
			messageElement.textContent = "Passordene må være like. Prøv igjen.";
			return false; 
		} else {
			messageElement.textContent = "";
			return true; 
		}
	}
	
	</script>
</head>

<body>
	<h2>Påmelding</h2>

	<c:if test="${feil}">
		<p style="color:red;"><c:out value="${errorMessage}" /></p>
	</c:if>

	<c:url var="postUrl" value="/paameldt"/>
	<form action="${postUrl}" method="post" onsubmit="return validatePassword();">

		Fornavn <input type="text" name="fornavn" placeholder="Fornavn" required /><br/>
		Etternavn <input type="text" name="etternavn" placeholder="Etternavn" required /><br/>

		Passord <input type="password" id="passord" name="passord" placeholder="Passord" required minlength="8"/><br/>
		Gjenta passord <input type="password" id="passord2" name="passord2" placeholder="Gjenta passord" required minlength="8"/><br/>
		<span id="passwordMatchMessage" style="color: red;"></span><br/>

		Mobilnummer <input type="text" name="mobil" placeholder="Mobilnummer" required pattern="\d{8}" /><br/>

		Kjønn:
		<label for="kjonnMann">Mann</label>
		<input id="kjonnMann" type="radio" name="kjonn" value="Mann" checked />
		<label for="kjonnKvinne">Kvinne</label>
		<input id="kjonnKvinne" type="radio" name="kjonn" value="Kvinne" />
		<br/><br/>

        <button type="submit">Meld meg på</button>
	</form>
</body>
</html>
