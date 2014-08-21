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
		<%@ include file="menu.jspf" %>
	</section>
	<section id="books">
		<div class="header">
			<h1>Books</h1>
		</div>
		<input type="text" id="search" placeholder="Search for book">
		<hr>
		<c:forEach var="book" items="${books}">
			<ul>
				<li>
					<fieldset>
						<h2>${book.title}</h2>
						<small id="author">${book.author}</small>
						<p>
<%-- 							<img alt="${book.title}" src="${book.image}"> --%>
							${book.description}
						</p>
						<div class="attributes">
							<span class="attribute">
								<em>Publication year: </em> ${book.year}
							</span>
							<span class="attribute">
								<em>ISBN: </em> ${book.isbn}
							</span>
							<span class="attribute">
								<a href="/smvc/borrow?id=${book.id}" class="borrow"><em>Borrow</em></a>
							</span>
						</div>
					</fieldset>
				</li>
				<hr>
			</ul>
		</c:forEach>
		<span class="attributes" style="display: block; width: 200px;">Page comes here</span>
		<div class="footer">
			<small id="footer">${today}</small>
		</div>

	</section>
	</main>
</body>
</html>
