<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>

<c:import url="base.jsp">
	<c:param name="title">
		Error
	</c:param>
	
	<c:param name="content">
			<section id="login" class="booklist">
<!-- 			<div class="booklist" id="noPermissionMessage"> -->
				<h3>Sorry, you have no permission to watch this page!</h3>
	            <img alt="bookshelf" src="<c:url value="/resources/bookshelf.png"/>" >
<!--             </div>    -->
		</section>
	</c:param>
</c:import>