<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="v" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
	<v:head title="Prijsverhoging" />
</head>

<body>
	<v:menu></v:menu>
	
	<h1>Prijsverhoging op alle artikels</h1>
	
	<form method="post" id="opslagform">
		<label>
			Percentage:<span>${fouten.percentage}</span>
			<input name="percentage" value="${param.percentage}" type="number" min="0.01" step="0.01" required autofocus >
		</label>
		
		<input type="submit" value="Prijs verhogen" id="submitknop" >
	
	</form>
	
<script>
document.getElementById("opslagform").onsubmit = function() {
	document.getElementById("submitknop").disabled = true;
}
</script>

</body>

</html>