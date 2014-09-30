<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="base.jsp">
	<c:param name="title">
		Register
	</c:param>
	
	<c:param name="content">
		<div class="bookList" id="reg">
			<h3>Please enter your personal information to continue.</h3>
			<small>If you have previously registered with us, <a href="/smvc/login">click here</a></small>
			<form:form role="form" method="POST" action="/smvc/register" commandName="user" >
				<fieldset>
					<div>
						<label for="username" class="inputElement">Username</label>
						<form:input type="text" id="username" class="inputElement ${not empty status.getFieldError('username') ? 'error' : '' }" 
							name="username" path="username" placeholder=" Username"/>
						<form:errors path="username" cssClass="errorMessage"/>
					
						<label for="firstName" class="inputElement">First name</label>
						<form:input type="text" id="firstName" class="inputElement ${not empty status.getFieldError('firstname') ? 'error' : '' }"  
							name="firstname" path="firstname" placeholder=" First name"/>
						<form:errors path="firstname" cssClass="errorMessage"/>
						
						<label for="lastName" class="inputElement">Last name</label>
						<form:input type="text" id="lastName" class="inputElement ${not empty status.getFieldError('lastname') ? 'error' : '' }" 
							name="lastname" path="lastname" placeholder=" Last name"/>
						<form:errors path="lastname" cssClass="errorMessage"/>
						
						<label for="pwd" class="inputElement">Password</label>
						<form:input type="password" id="pwd" class="inputElement ${not empty status.getFieldError('pwd') ? 'error' : '' }" name="password" path="password"/>
						<form:errors path="password" cssClass="errorMessage"/>
						
						<button type="submit">Register</button>
					</div>
				</fieldset>
			</form:form>
		</div>   
	</c:param>
</c:import>