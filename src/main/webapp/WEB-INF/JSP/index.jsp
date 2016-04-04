<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
	<vdab:head title="Welkom" />
</head>

<body>

<header>

	<vdab:menu />
	
	<h1>Alles voor de keuken</h1>
	
	<img alt="Logo" src="<c:url value='/images/logo${randomNumber}.jpg' />" id="logo"/>

</header>

</body>

</html>