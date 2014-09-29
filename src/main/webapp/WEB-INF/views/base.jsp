<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html>
<head>
	<title>Library</title>
	<link rel="stylesheet" type="text/css" href="/smvc/resources/reset.css">
	<link rel="stylesheet" type="text/css" href="/smvc/resources/library.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
</head>
<body>
	<section id="menu">
		<nav>
			<ul>
				<sec:authorize access="isAnonymous()">
					<li>
						<a href="/smvc/register" class="menulink">Register</a>
					</li>
				</sec:authorize>
		
				<sec:authorize access="hasRole('ROLE_USER')">
					<li>
						<a href="/smvc/books" class="menulink">Books</a>
					</li>
				</sec:authorize>
		
				<sec:authorize access="hasRole('ROLE_USER')">
					<li>
						<a href="/smvc/loanedbooks" class="menulink">Loaned books</a>
					</li>
				</sec:authorize>
		
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li>
						<a href="/smvc/maintainbook" class="menulink">Books</a>
					</li>
				</sec:authorize>
		
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li>
						<a href="/smvc/addbook" class="menulink">Add book</a>
					</li>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
					<li>
						<a href="<c:url value="/j_spring_security_logout"/>" class="menulink">Logout</a>
					</li>
				</sec:authorize>
				
				 <sec:authorize access="isAuthenticated()">
			         <li class="loggedUserName">
			         	Welcome <sec:authentication property="name" /> !
			         </li>
		 		</sec:authorize>
			</ul>
		</nav>
	</section>
	
	<main class="container">
		<section id="books">
			<div class="header">
				<h1>${param.title}</h1>
			</div>

			${param.content}
			
			<div class="footer">
				<small id="footer">${today}</small>
			</div>
		</section>
	</main>
	
</body>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
<script src="<c:url value="/resources/js/books.js"/>"></script>
<script src="<c:url value="/resources/js/managebook.js"/>"></script>
</html>
