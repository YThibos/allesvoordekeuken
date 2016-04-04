<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://vdab.be/tags" prefix="vdab"%>

<!doctype html>
<html lang="nl">

<head>
<vdab:head title="${empty artikel ? 'Artikel zoeken' : artikel.naam}" />
</head>

<body>
	<vdab:menu />
	
	<h1>Artikel zoeken</h1>
	
	<form>
		<label>Nummer:<span>${fouten.id}</span> 
		<input name="id" value="${param.id}" required autofocus type="number" min="1">
		</label> 
		<input type="submit" value="Zoeken">
	</form>
	
	<c:if test="${not empty param and empty fouten and empty artikel}">
		Artikel niet gevonden
	</c:if>
	
	<c:if test="${not empty artikel}">
		<h3>${artikel.naam}</h3> 
		<br>Aankoopprijs: <fmt:formatNumber value="${artikel.aankoopprijs}" />
		<br>Verkoopprijs: <fmt:formatNumber value="${artikel.verkoopprijs}"/>
		<c:set var="winst" value="${(artikel.verkoopprijs - artikel.aankoopprijs) / artikel.aankoopprijs * 100 }" />
		<br>Winst: <fmt:formatNumber value="${winst}" />%
	</c:if>
	
</body>
</html>