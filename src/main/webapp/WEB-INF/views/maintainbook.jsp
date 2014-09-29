<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="base.jsp">
	<c:param name="title">
		Maintain Books
	</c:param>
	
	<c:param name="content">
		<div class="loanedBooks">
			<table class="hiredbooks" id="maintained">
				<thead>
					<tr>
						<td class="fixed">Title</td>
						<td class="fixed">Author</td>
						<td>Year</td>
						<td class="fixed">ISBN</td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="book" items="${books}">
						<tr class="clickablerow">
							<td class="fixed" onclick="showDetails(${book.id}, '${book.description}')">
								<abbr title="${book.title}">
									${book.shortTitle}
								</abbr>
							</td>
							
							<td class="fixed" onclick="showDetails(${book.id}, '${book.description}')">
								<abbr title="${book.author}">
									${book.shortAuthor}
								</abbr>
							</td>
							
							<td onclick="showDetails(${book.id}, '${book.description}')">
								${book.year}
							</td>
							
							<td class="fixed" onclick="showDetails(${book.id}, '${book.description}')">
								${book.isbn}
							</td>
							
							<td>
								<a href="<c:url value="/maintainbook/${book.id}"/>" class="crudIcon">
									<img src="<c:url value="/resources/delete.png"/>" class="crudImage">
								</a>
								<a href="<c:url value="/updatebook/${book.id}"/>" class="crudIcon">
									<img src="<c:url value="/resources/edit.png"/>" class="crudImage">
								</a>
							</td>
						</tr>
						
						<!-- The description loads here -->
						<tr id="${book.id}" class="descriptionrow"></tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:param>
</c:import>