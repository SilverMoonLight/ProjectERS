<%@ include file="headers.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Requests</title>
</head>
<body>
	<%@ include file="navbar.jsp" %>
<div class="header"><h1>Your Pending Requests</h1></div>
	
	<c:if test="${ employeePending.size() != 0 }">
	<table class="table table-condensed table-hover">
		<tr>
			<th>Amount</th>
			<th>Description</th>
			<th>Submitted Date</th>
			<th>Name</th>
			<th>Type</th>
		</tr>
		<c:forEach items="${ employeePending }" var="request">
		<tr>
			<td><fmt:formatNumber value="${request.amount }" type="currency" /></td>
			<td>${request.description }</td>
			<td>${request.timeSubmitted }</td>
			<c:forEach items="${ users }" var="user">
			
  						<c:if test="${ user.u_id == request.authorId }">
  								<td>${ user.firstname } ${ user.lastname }</td>
  						</c:if>
			
						</c:forEach>
			<td>${ types.get(request.type -1).type }</td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${ employeePending.size() == 0 }">
		<strong class="alert alert-info">You have no pending requests</strong>
	</c:if>
</body>
</html>