<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html>
<head>
<title>Books</title>
<link rel="stylesheet" type="text/css" href="resources/reset.css">
<link rel="stylesheet" type="text/css" href="resources/library.css">
</head>
<body ng-app="bookApp">
	<section id="menu">
		<%@ include file="menu.jspf" %>
	</section>
	
	<main class="container">
	<section id="books" ng-controller="bookCtrl">
		<div class="header">
			<h1>Books</h1>
		</div>
		
		<div class="bookList">
			<input type="text" id="search" placeholder=" Search for author, title" ng-model="search">
			<ul ng-repeat="book in filteredBooks = (books | filter:search) | startFrom:(currentPage-1)*entryLimit | limitTo:entryLimit">
				<li>
					<fieldset>
						<h2>{{book.title}}</h2>
						<small id="author">{{book.author}}</small>
						<p>{{book.description}}</p>
						
						<div class="attributes">
							<span class="attribute">
								<em>Publication year: </em> {{book.year}}
							</span>
							
							<span class="attribute">
								<em>ISBN: </em> {{book.isbn}}
							</span>
							
							<span class="attribute">
								<sec:authorize access="hasRole('ROLE_USER')">
									<a href="/smvc/loan?id={{book.id}}" class="borrow"><em>Loan</em></a>
								</sec:authorize>
							</span>
						</div>
					</fieldset>
				</li>
				<hr class="bookline">
			</ul>
		</div>
			<div class="pagination">
				<pagination data-boundary-links="true"
							total-items="numberOfItems" num-pages="noOfPages"
							ng-model="currentPage" max-size="maxSize" class="pagination"
							items-per-page="entryLimit" data-previous-text="&laquo;"
							data-next-text="&raquo;">
				</pagination>
			</div>
		
		<div class="footer">
			<small id="footer">${today}</small>
		</div>

	</section>
	</main>
</body>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
<script src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
<script src="<c:url value="/resources/js/books.js"/>"></script>
</html>
