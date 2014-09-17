<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="resources/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/library.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
</head>
<body>
	<section id="menu">
	<%@ include file="menu.jspf" %>
	</section>
	<main class="container">
		<section id="login">
			<div class="header">
				<h1>Error</h1>
			</div>
			
			<div class="booklist" id="noPermissionMessage">
				<h3>Sorry, you have no permission to watch this page!</h3>
	            <img alt="bookshelf" src="<c:url value="/resources/bookshelf.png"/>" >
            </div>
               
            <div class="footer"></div>
		</section>
	</main>
</body>
</html>
