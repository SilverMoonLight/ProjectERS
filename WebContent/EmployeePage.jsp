<%@  include file="headers.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee</title>
</head>
<body>
	<%@ include file="navbar.jsp" %>
	<c:if test="${ user.getUr_id() == 2 }">
		<c:redirect url="ManagerPage.jsp"></c:redirect>
	</c:if>
	<div class="header"><h1>Your Resolved Requests</h1></div>
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
		<c:forEach items="${ requests }" var="request">
		<tr>
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
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>