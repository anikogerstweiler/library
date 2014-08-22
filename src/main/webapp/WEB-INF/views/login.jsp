<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="resources/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/library.css">
</head>
<body>
	<main class="container">
		<section id="menu">
		<%@ include file="menu.jspf" %>
		</section>
		<section id="login">
			<form action="j_spring_security_check" name="f" method="post">
				<div class="header">
					<h1>Login</h1>
				</div>
				<div class="column">
					<c:if test="${error == true}">
						<label class="error">Wrong user name or password</label>
					</c:if>
					
                    <label for="name" class="inputElement"> Username </label>
                    <input type="text" id="name" name="j_username" class="inputElement">
                    
                    <label for="pwd" class="inputElement">Password</label>
                    <input type="password" id="pwd" maxlength="4" name="j_password" class="inputElement">
                    
	                <button type="submit" class="submit">Login</button>
	                <label class="rememberme">
	                	<input type="checkbox" name="_spring_security_remember_me">Remember me
	                </label>
                </div>
                <div class="footer">
                	<small id="footer">You don't have an account? <a href="/smvc/register" class="menulink">Register here</a></small>
                </div>
			</form>
		</section>
	</main>

</body>
</html>
