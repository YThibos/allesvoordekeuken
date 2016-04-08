<%@ page contentType='text/html' pageEncoding='UTF-8' session='false' %>
<%@ taglib prefix="v" uri="http://vdab.be/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>

<head>
	<v:head title="Artikel kortingen"></v:head>
</head>

<body>
	<v:menu/>

	<c:if test="${not empty gevondenArtikel }">
	
		<h2>Kortingen ${gevondenArtikel.naam }</h2>
		
		<ul>
		<c:forEach items="${gevondenArtikel.kortingen }" var="korting">
			<li>${korting }</li>
		</c:forEach>
		</ul>
	
	</c:if>
	
	<span>${fouten.id }</span>
	
	<h2>Alle artikels</h2>
	
	<ul>
		<c:forEach items="${alleArtikels}" var="artikel">
			<c:url value="" var="artikelURL">
				<c:param name="id" value="${artikel.id}" />
			</c:url>
			<li><a href="${artikelURL}" >${artikel.naam }</a>
		</c:forEach>
	</ul>
	
	
</body>

</html>