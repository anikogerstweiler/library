<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html ng-app="bookApp">
<head>
	<title>Library</title>
	<link rel="stylesheet" type="text/css" href="/smvc/resources/reset.css">
	<link rel="stylesheet" type="text/css" href="/smvc/resources/library.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
</head>
<body>
	<div class="welcome">
<!-- 		<nav> -->
<!-- 			<ul> -->
				 <sec:authorize access="isAuthenticated()">
			         <div class="loggedUserName">
			         	Welcome <sec:authentication property="name" /> !
			         </div>
		 		</sec:authorize>
<!-- 			</ul> -->
<!-- 		</nav> -->
	</div>
	
	<main class="container">
		<div class="header">
			<h1>${param.title}</h1>
		</div>
		<section>
				<div id="menu">
					<nav>
						<ul ng-controller="MenuCtrl">
							<sec:authorize access="isAnonymous()">
								<li>
									<div>
										<a href="/smvc/register" class="menulink">Register</a>
									</div>
								</li>
							</sec:authorize>
					
							<sec:authorize access="hasRole('ROLE_USER')">
								<li>
									<div ng-class="getClass('/books')">
										<a href="/smvc/books" class="menulink">Books</a>
									</div>
								</li>
							</sec:authorize>
					
							<sec:authorize access="hasRole('ROLE_USER')">
								<li>
									<div ng-class="getClass('/loanedbooks')">
										<a href="/smvc/loanedbooks" class="menulink">Loaned books</a>
									</div>
								</li>
							</sec:authorize>
					
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<li>
									<div ng-class="getClass('/maintainbook')">
										<a href="/smvc/maintainbook" class="menulink">Books</a>
									</div>
								</li>
							</sec:authorize>
					
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<li>
									<div ng-class="getClass('/addbook')">
										<a href="/smvc/addbook" class="menulink">Add book</a>
									</div>
								</li>
							</sec:authorize>
							
							<sec:authorize access="isAuthenticated()">
								<li>
									<div>
										<a href="<c:url value="/j_spring_security_logout"/>" class="menulink">Logout</a>
									</div>
								</li>
							</sec:authorize>
						</ul>
					</nav>
				</div>
				<div>
					${param.content}
				
				</div>
		</section>
		<div class="footer">
			<small id="footer">${today}</small>
		</div>
	</main>
	
</body>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
<script src="<c:url value="/resources/js/books.js"/>"></script>
<script src="<c:url value="/resources/js/managebook.js"/>"></script>
<%-- <script src="<c:url value="/resources/js/base.js"/>"></script> --%>
</html>
