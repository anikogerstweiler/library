<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<title>My Books</title>
	<link rel="stylesheet" type="text/css" href="resources/reset.css">
	<link rel="stylesheet" type="text/css" href="resources/library.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
</head>
<body>
	<section id="menu">
		<%@ include file="menu.jspf" %>
	</section>
	
	<main class="container">
		<section id="books">
			<div class="header">
				<h1>Loaned Books</h1>
			</div>
			
			<div class="loanedBooks">
				<table class="hiredbooks">
					<thead>
						<tr>
							<td>Title</td>
							<td>Author</td>
							<td>From</td>
							<td>To</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="books" items="${mybooks}">
							<c:set var="hiredbook" value="${books[0]}"/>
							<c:set var="book" value="${books[1]}"/>
							<tr>
								<td>${book.title}</td>
								<td>${book.author}</td>
								<td>${hiredbook.fromdate}</td>
								<td>${hiredbook.todate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div class="footer">
				<small id="footer">${today}</small>
			</div>
		</section>
	</main>
</body>
</html>
