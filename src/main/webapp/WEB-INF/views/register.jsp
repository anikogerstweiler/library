<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="resources/reset.css">
    <link rel="stylesheet" type="text/css" href="resources/library.css">
</head>
<body>
	<main class="container">
		<section id="menu">
			<ul>
				<li>Home</li>
				<li>Register</li>
				<li>Books</li>
				<li>My Books</li>
				<li><a href="<c:url value="/j_spring_security_logout"/>">Logout</a></li>
			</ul>
		</section>
		
		<section id="register">
			<form>
				<div class="header">
					<h1>Register</h1>
						Hello <sec:authentication property="principal.lastname"/> <sec:authentication property="principal.firstname"/>
						<a href="<c:url value="/j_spring_security_logout"/>">logout</a>
				</div>
					<div class="column">
	                    <label for="name"> Username </label>
	                    <input type="text" id="name">
	                    <label for="firstName"> First name </label>
	                    <input type="text" id="firstName">
	                    <label for="lastName"> Last name </label>
	                    <input type="text" id="lastName">
	                    <label for="pwd">Password</label>
	                    <input type="password" id="pwd" maxlength="4">
		                <button type="submit">Register</button>
	                </div>
                <div class="footer"></div>
			</form>
		</section>
	</main>

</body>
</html>
