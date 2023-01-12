<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title" /></title>
<link href="/myapp/resources/css/index.css" type="text/css"
	rel="stylesheet" />
<link href="/myapp/resources/css/header.css" type="text/css"
	rel="stylesheet" />
<link href="/myapp/resources/css/footer.css" type="text/css"
	rel="stylesheet" />
<link href="/myapp/resources/css/menu.css" type="text/css"
	rel="stylesheet" />


</head>
<body>
	<header>
		<tiles:insertAttribute name="header" />
	</header>

	<section>
		<tiles:insertAttribute name="menu" />
	</section>

	<section>
		<tiles:insertAttribute name="body" />
	</section>

	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>

</body>
</html>