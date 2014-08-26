<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Add Book</title>
<link rel="stylesheet" type="text/css" href="resources/reset.css">
<link rel="stylesheet" type="text/css" href="resources/library.css">
</head>
<body>
	<section id="menu">
		<%@ include file="menu.jspf"%>
	</section>
	<main class="container">
	<section id="books">
		<div class="header">
			<h1>Books</h1>
		</div>
		<div class="bookList, column">
			<form:form role="form" method="POST" action="/smvc/addbook" commandName="addBookForm">
			<form:errors path="*" cssClass="errorBox"/>
				<fieldset>
					<div>
						<label for="title" class="inputElement">Title</label>
						<form:input type="text" id="title" class="inputElement" name="title" path="title"/>
					
						<label for="author" class="inputElement">Author</label>
						<form:input type="text" id="author" class="inputElement" name="author" path="author"/>
						
						<label for="year" class="inputElement">Year</label>
						<form:input type="year" id="year" class="inputElement" name="year" path="year"/>
						
						<label for="isbn" class="inputElement">ISBN</label>
						<form:input type="text" id="isbn" class="inputElement" name="isbn" path="isbn"/>
						
						<label for="description" class="inputElement">Description</label>
						<form:textarea id="description" class="inputElement" name="description" path="description" />
						
						<button type="submit">Save</button>
					</div>
				</fieldset>
			</form:form>

		</div>
		<div class="footer">
			<small id="footer">${today}</small>
		</div>

	</section>
	</main>
</body>
</html>
