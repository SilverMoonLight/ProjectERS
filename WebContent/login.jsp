<%@ include file="headers.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.css"/>
<title>Login</title>
</head>
<body>
			
			<c:if test="${ not empty nouser }">
			<strong class="alert alert-warning">${ nouser }</strong>
			</c:if>
	<div class="container">
		
		<form class="form-horizontal" method="post" action="login.do">
  <div class="form-group">
  	<label for="none" class="col-sm-12 control-label">Welcome to MedForce Reimbursement Portal</label>
    <label for="user" class="col-sm-2 control-label">UserName</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="user" placeholder="Username">
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="password" placeholder="Password">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Login in</button>
    </div>
  </div>
</form>
		
	</div>

</body>
</html>