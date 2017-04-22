<%@ include file="headers.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Requests</title>
</head>
<body>
	<%@ include file="navbarMan.jsp" %>
	<div class="header"><h1>Requests</h1></div>
	<table class="table table-condensed table-hover">
		<tr>
			<th>Amount</th>
			<th>Description</th>
			<th>Submitted Date</th>
			<th>Resolved Date</th>
			<th>Employee Name</th>
			<th>Manager Name</th>
			<th>Type</th>
			<th>Decision</th>
		</tr>
		<c:forEach items="${ employeeRequests }" var="request">
		<tr>
			
			<c:if test="${ request.timeResolved != null }">
			<td><fmt:formatNumber value="${request.amount }" type="currency" /></td>
			<td>${request.description }</td>
			<td>${request.timeSubmitted }</td>
			<td>${request.timeResolved }</td>
			<c:forEach items="${ users }" var="user">
			
  						<c:if test="${ user.u_id == request.authorId }">
  								<td>${ user.firstname } ${ user.lastname }</td>
  						</c:if>
			
						</c:forEach>
						<c:forEach items="${ users }" var="user">
			
  						<c:if test="${ user.u_id == request.resolverId }">
  								<td>${ user.firstname } ${ user.lastname }</td>
  						</c:if>
			
						</c:forEach>
						<td>${ types.get(request.type -1).type }</td>
			<td> ${ statuses.get(request.status -2 ).status } </td>
			
			</c:if>
			<c:if test="${ request.timeResolved == null }">
			<td><fmt:formatNumber value="${request.amount }" type="currency" /></td>
			<td>${request.description }</td>
			<td>${request.timeSubmitted }</td>
			<td>Not Completed</td>
			<c:forEach items="${ users }" var="user">
			
  						<c:if test="${ user.u_id == request.authorId }">
  								<td>${ user.firstname } ${ user.lastname }</td>
  						</c:if>
			
						</c:forEach>
			<td>Not Completed</td>
			<td>${ types.get(request.type -1).type }</td>
			<td> Pending </td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
</body>
</html>