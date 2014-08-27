<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Books</title>
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
				<h1>Loan Book</h1>
			</div>
			
			<div class="bookList">
				<c:if test="${bookAvailable == false}">
					<label>Book can not be borrow this time!</label>
				</c:if>
				<form:form role="form" method="POST" action="/smvc/loan" commandName="loanForm">
					<fieldset>
						<form:input type="hidden" name="id" id="id" value="${book.id}" path="id"/>
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
							<form:input type="date" id="fromDate" class="inputElement" name="fromDate" path="fromDate"/>
							
							<label for="todate" class="inputElement">End date</label>
							<form:input type="date" id="toDate" class="inputElement" name="toDate" path="toDate"/>
							
							<button type="submit">Borrow</button>
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
