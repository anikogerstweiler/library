<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		<section id="books" >
			<div class="header">
				<h1>Manage Books</h1>
			</div>
			
<!-- 			<div id="addBook"> -->
<!-- 				<h3>Add new book</h3> -->
<%-- 				<form:form role="form" method="POST" action="/smvc/addbook" commandName="addBookForm" > --%>
<!-- 					<fieldset> -->
<!-- 						<div> -->
<%-- 							<form:input type="text" id="title" class="inputElement ${not empty status.getFieldError('title') ? 'error' : '' }"  --%>
<%-- 								name="title" path="title" autofocus="autofocus" placeholder=" Title"/> --%>
<%-- 							<form:errors path="title" cssClass="errorMessage"/> --%>
						
<%-- 							<form:input type="text" id="author" class="inputElement ${not empty status.getFieldError('author') ? 'error' : '' }"   --%>
<%-- 								name="author" path="author" placeholder=" Author"/> --%>
<%-- 							<form:errors path="author" cssClass="errorMessage"/> --%>
							
<%-- 							<form:input type="year" id="year" class="inputElement ${not empty status.getFieldError('author') ? 'error' : '' }"  --%>
<%-- 								name="year" path="year" placeholder=" YYYY eg. 1987"/> --%>
<%-- 							<form:errors path="year" cssClass="errorMessage"/> --%>
							
<%-- 							<form:input type="text" id="isbn" class="inputElement ${not empty status.getFieldError('author') ? 'error' : '' }"  --%>
<%-- 								name="isbn" path="isbn" placeholder=" ISBN number"/> --%>
<%-- 							<form:errors path="isbn" cssClass="errorMessage"/> --%>
							
<%-- 							<form:textarea id="description" class="inputElement ${not empty status.getFieldError('author') ? 'error' : '' }"  --%>
<%-- 								name="description" path="description" placeholder=" Description"/> --%>
<%-- 							<form:errors path="description" cssClass="errorMessage"/> --%>
							
<!-- 							<button type="submit">Save</button> -->
<!-- 						</div> -->
<!-- 					</fieldset> -->
<%-- 				</form:form> --%>
<!-- 			</div> -->
			
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
									<img src="<c:url value="/resources/details.png"/>" class="crudImage crudIcon" onclick="showDetails(${book.id}, '${book.description}')">
									<a href="<c:url value="/managebook/${book.id}"/>" class="crudIcon">
										<img src="<c:url value="/resources/delete.png"/>" class="crudImage">
									</a>
									<a href="addbook" class="crudIcon" onclick="return popitup('addbook')">
										<img src="<c:url value="/resources/edit.png"/>" class="crudImage">
									</a>
								</td>
							</tr>
							
							<tr id="${book.id}">
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
<script src="<c:url value="/resources/js/managebook.js"/>"></script>
</html>
