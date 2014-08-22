<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false"%>
<html>
<head>
<title>Books</title>
<link rel="stylesheet" type="text/css" href="resources/reset.css">
<link rel="stylesheet" type="text/css" href="resources/library.css">
</head>
<body>
	<main class="container">
	<section id="menu">
		<%@ include file="menu.jspf"%>
	</section>
	<section id="books">
		<div class="header">
			<h1>Books</h1>
		</div>
		<hr>
		<div class="bookList">
			<form role="form" action="/smvc/borrow?step=done" method="POST">
				<fieldset>
					<h2>${book.title}</h2>
					<small id="author">${book.author}</small>
					<p>
						${book.description}
					</p>
					<div class="attributes">
						<span class="attribute"> 
							<em>Publication year: </em> ${book.year}
						</span> 
						<span class="attribute"> 
							<em>ISBN: </em> ${book.isbn}
						</span> 
					</div>
					<div>
						<label for="fromdate" class="inputElement">Start date</label>
						<input type="date" id="fromdate" class="inputElement" name="fromdate">
						
						<label for="todate" class="inputElement">End date</label>
						<input type="date" id="todate" class="inputElement" name="todate">
						
						<button type="submit">Borrow</button>
					</div>
				</fieldset>
			</form>

		</div>
		<div class="footer">
			<small id="footer">${today}</small>
		</div>

	</section>
	</main>
</body>
<script
	src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.js"></script>
<script
	src="//angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js"></script>
<script src="<c:url value="/resources/js/books.js"/>"></script>
</html>
