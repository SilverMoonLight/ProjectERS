<%@ include file="headers.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reimbursement</title>
</head>
<body>
	<%@ include file="navbar.jsp" %>
	<div class="header"><h1>Submit A Reimbursements</h1></div>
	<form action="reimbursements.do" method="post">
	<div class="form-group">
    <label for="amount" class="col-sm-2 control-label">Amount</label>
    <div class="col-sm-10">
      <input type="number" class="form-control" name="amount" placeholder="$">
    </div>
    </div>
    <div class="form-group">
    <label for="description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-10">
    <input type="text" class="form-control" name="description" placeholder="Description">
    </div>
    </div>
    	<select class="form-control form-control-sm" name="type">
		<c:forEach items="${ types }" var="rt">
			
  				<option>${rt.type }</option>
			
		</c:forEach>
		</select>
		<div class="form-group row">
      <div class="offset-sm-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Submit Reimbursement</button>
      </div>
    </div>
	</form>
</body>
</html>