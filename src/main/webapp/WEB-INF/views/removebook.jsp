<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>My Books</title>
<link rel="stylesheet" type="text/css" href="resources/reset.css">
<link rel="stylesheet" type="text/css" href="resources/library.css">
</head>
<body>
	<section id="menu">
		<%@ include file="menu.jspf" %>
	</section>
	
	<main class="container">
		<section id="books">
			<div class="header">
				<h1>Remove Book</h1>
			</div>
			
			<div class="loanedBooks">
				<table class="hiredbooks">
					<thead>
						<tr>
							<td>Title</td>
							<td>Author</td>
							<td>Year</td>
							<td>ISBN</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="book" items="${books}">
							<tr>
								<td>${book.title}</td>
								<td>${book.author}</td>
								<td>${book.year}</td>
								<td>${book.isbn}</td>
								<td>
									<a href="<c:url value="/removebook/${book.id}"/>" class="removeIcon">
										<img src="<c:url value="/resources/delete.png"/>" class="removeImage">
									</a>
								</td>
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
