<%@ include file="headers.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees</title>
</head>
<body>
	<%@ include file="navbarMan.jsp" %>
	<div class="header"><h1>Employees</h1></div>
	<table class="table table-condensed">
		<tr>
			<th>Name</th>
			<th>Username</th>
			<th>Email</th>
			<th>View Requests</th>
		</tr>
		<c:forEach items="${ employees }" var="employee">
		<tr>
			<td>${ employee.firstname } ${ employee.lastname} </td>
			<td>${ employee.username }</td>
			<td>${ employee.email }</td>
			<td><a href="ViewRequest.do?id=${ employee.u_id }">View Requests</a> </td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>