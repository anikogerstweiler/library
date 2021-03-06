<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<c:import url="base.jsp">
	<c:param name="title">
		Login
	</c:param>
	
	<c:param name="content">
<%-- 		<section id="login"> --%>
			<form action="j_spring_security_check" name="f" method="post" id="login">
				
				<div class="column">
					<div class="welcomeMsg">
						<h3>WELCOME TO THE EPAM LIBRARY</h3>
						<h5>View your Personal Record by logging in with your username and password. <br> 
						You can then loan, check what you have out on loan and any overdues and reservations.</h5>
					</div>
					<c:if test="${error == true}">
						<label class="errorMessage">Wrong user name or password</label>
					</c:if>
					
                    <input type="text" id="name" name="j_username" class="inputElement" placeholder=" Username" autofocus="autofocus">
                    
                    <input type="password" id="pwd" name="j_password" class="inputElement" placeholder=" Password">
                    
	                <button type="submit">Login</button>
	                <label class="rememberme">
	                	<input type="checkbox" name="_spring_security_remember_me" checked="checked">Remember me
	                </label>
                </div>
			</form>   
<%-- 		</section> --%>
	</c:param>
</c:import>