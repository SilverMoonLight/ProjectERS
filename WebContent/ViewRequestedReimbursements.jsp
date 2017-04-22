<%@ include file="headers.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Requested Reimbursements</title>
</head>
<body>
	<%@ include file="navbarMan.jsp" %>
	<div class="header"><h1>Requests</h1></div>
	<c:if test="${ pendingRS.size() != 0 }">
	<table class="table table-condensed">
		<tr>
			<th>Amount</th>
			<th>Description</th>
			<th>Submitted Date</th>
			<th>Employee Name</th>
			<th>Type</th>
			<th>Decision</th>
		</tr>
		<c:forEach items="${ pendingRS }" var="request">
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
			<td> <form action="decision.do" method="post">
					<input type="hidden" value="${ request.id }" name="number" />
					<select class="form-control form-control-sm" name="type">
						<c:forEach items="${ statuses }" var="st">
			
  							<option>${st.status }</option>
			
						</c:forEach>
					</select>
					<button type="submit" class="btn btn-primary">Submit Decision</button>
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${ pendingRS.size() == 0 }">
		<strong class="alert alert-info">There are no pending requests</strong>
	</c:if>
</body>
</html>