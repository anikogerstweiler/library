<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:import url="base.jsp">
	<c:param name="title">
		My Books
	</c:param>
	
	<c:param name="content">
		<div class="loanedBooks">
				<table class="hiredbooks" id="hiredbooks">
					<thead>
						<tr>
							<td class="fixed">Title</td>
							<td class="fixed">From</td>
							<td class="fixed">To</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="books" items="${mybooks}">
							<c:set var="hiredbook" value="${books[0]}"/>
							<c:set var="book" value="${books[1]}"/>
							<tr>
								<td class="fixed">${book.title}</td>
								<td>${hiredbook.fromdate}</td>
								<td>${hiredbook.todate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
	</c:param>
</c:import>