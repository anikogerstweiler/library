<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Update Book</title>
	<link rel="stylesheet" type="text/css" href="resources/reset.css">
	<link rel="stylesheet" type="text/css" href="resources/library.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
</head>
<body>
	<section id="menu">
		<%@ include file="menu.jspf"%>
	</section>
	<main class="container">
		<section id="books">
			<div class="header">
				<h1>Update Book</h1>
			</div>
			<div class="bookList" id="updateBook">
				<form:form role="form" method="POST" action="/smvc/updatebook" commandName="book">
					<fieldset>
						<div>
							<form:input type="hidden" name="id" id="id" value="${book.id}" path="id"/>
							
							<label for="title" class="inputElement">Title</label>
							<form:input type="text" id="title" class="inputElement ${not empty status.getFieldError('title') ? 'error' : '' }"  
								name="title"  path="title" autofocus="autofocus" value="${book.title}"/>
 							<form:errors path="title" cssClass="errorMessage"/>
						
							<label for="author" class="inputElement">Author</label>
 							<form:input type="text" id="author" class="inputElement ${not empty status.getFieldError('author') ? 'error' : '' }"  
 								name="author" path="author" value="${book.author}"/>
 							<form:errors path="author" cssClass="errorMessage"/>
							
							<label for="year" class="inputElement">Year</label>
 							<form:input type="year" id="year" class="inputElement ${not empty status.getFieldError('year') ? 'error' : '' }" 
 								name="year" path="year" value="${book.year}"/>
 							<form:errors path="year" cssClass="errorMessage"/>
							
							<label for="isbn" class="inputElement">ISBN</label>
 							<form:input type="text" id="isbn" class="inputElement ${not empty status.getFieldError('isbn') ? 'error' : '' }" 
 								name="isbn" path="isbn" value="${book.isbn}"/>
 							<form:errors path="isbn" cssClass="errorMessage"/>
							
							<label for="description" class="inputElement">Description</label>
 							<form:textarea id="description" class="inputElement ${not empty status.getFieldError('description') ? 'error' : '' }" 
 								name="description" path="description" value="${book.description}"/>
							<form:errors path="description" cssClass="errorMessage"/>
							
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