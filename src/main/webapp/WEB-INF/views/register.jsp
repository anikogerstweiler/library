<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="resources/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/library.css">
</head>
<body>
	<section id="menu">
		<%@ include file="menu.jspf"%>
	</section>
	<main class="container">
		<section id="register">
			<div class="header">
				<h1>Register</h1>
			</div>
			
			<div class="bookList" id="reg">
				<form:form role="form" method="POST" action="/smvc/register" commandName="userForm" >
					<fieldset>
						<div>
							<form:input type="hidden" value="1" name="enabled" path="enabled" />
							
							<label for="username" class="inputElement">Username</label>
							<form:input type="text" id="username" class="inputElement ${not empty status.getFieldError('username') ? 'error' : '' }" 
								name="username" path="username" placeholder=" Username"/>
							<form:errors path="username" cssClass="errorBox"/>
						
							<label for="firstName" class="inputElement">First name</label>
							<form:input type="text" id="firstName" class="inputElement ${not empty status.getFieldError('firstName') ? 'error' : '' }"  
								name="firstName" path="firstName" placeholder=" First name"/>
							<form:errors path="firstName" cssClass="errorBox"/>
							
							<label for="lastName" class="inputElement">Last name</label>
							<form:input type="text" id="lastName" class="inputElement ${not empty status.getFieldError('lastName') ? 'error' : '' }" 
								name="lastName" path="lastName" placeholder=" Last name"/>
							<form:errors path="lastName" cssClass="errorBox"/>
							
							<label for="pwd" class="inputElement">Password</label>
							<form:input type="password" id="pwd" class="inputElement ${not empty status.getFieldError('pwd') ? 'error' : '' }" name="pwd" path="pwd"/>
							<form:errors path="pwd" cssClass="errorBox"/>
							
							<button type="submit">Register</button>
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
