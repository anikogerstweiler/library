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
				<table class="hiredbooks" id="hiredbooks">
					<thead>
						<tr>
							<td class="fixed">Title</td>
							<td class="fixed">Author</td>
							<td class="fixed">From</td>
							<td class="fixed">To</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="books" items="${mybooks}">
							<c:set var="hiredbook" value="${books[0]}"/>
							<c:set var="book" value="${books[1]}"/>
							<tr>
								<td class="fixed">${book.title}</td>
								<td class="fixed">${book.author}</td>
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
