<%@ include file="headers.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${user.firstname } Personal Info</title>
</head>
<body>
	<%@ include file="navbar.jsp" %>
	<div class="header"><h1> ${user.firstname } Personal Info</h1></div>
	<h4 id="response"></h4>
	<table class="table table-condensed" id="table">
		<tr>
		<th>UserName</th>
		<th>Email</th>
		<th>Name</th>
		</tr>
		<tr>
		<td>${user.username }</td>
		<td>${ user.email }</td>
		<td>${ user.firstname } ${ user.lastname }</td>
		</tr>
	</table>
	<input type="hidden" value="${ user.password }">
	<button  id="update">Update Info</button>
	<script type="text/javascript" src="js/form.js"></script>
</body>
</html>