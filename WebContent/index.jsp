<%@ include file="/include.jsp"%>

<html>
<head>
<title>Welcome to Festival Event Registration System</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" ></meta>

 <link rel="StyleSheet" href="css/login.css" type="text/css" ></link>
 
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


<script type="text/javascript">
	function validateForm() {
		var uname = document.forms["logForm"]["USERNAME"].value;
		var password = document.forms["logForm"]["PASSWORD"].value;
		if (uname == null || uname == "") {
			alert("Please provide Username");
			return false;
		}
		if (password == null || password == "") {
			alert("Please provide Password");
			return false;
		}
	}
</script>
</head>
<style>
.error {
	color: red;
	font-size: 13px;
	font-weight: bold;
}

</style>

<body>
	
	<%
		HttpSession session = request.getSession();
		session.invalidate();
	%>
	
	<div class="container-fluid bg">
	     <div class="row">
	     <div class="col-md-4 col-sm-4 col-xs-12"></div>
	     <div class="col-md-4 col-sm-4 col-xs-12">
	
	<!-- form start -->
<form  class = "form-container" method="post" name="logForm" action="searchVisitor.htm"  onsubmit="return validateForm()">

				
<div class="form-group" id="content">
	<h3>Event Registration System</h3>
</div>

  <div class="form-group">
    <label for="exampleInputEmail1">Visitor Name</label>
    <input type="text" class="form-control" id="exampleInputEmail1"  name="USERNAME" placeholder="User Name">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="PASSWORD" placeholder="Password">
  </div>  
  
  <div class="form-group">
   <span class="error">${ERROR}</span>
  </div>
  
  <div class="form-group" id="content">New Visitor:</div>
  
   <div class="form-group" id="content">
<a href="/FestivalPortalR2_Participant/registration.jsp">Register
	here</a>
</div>

  <button type="submit" class="btn btn-success btn-block">Login</button>
</form>
	</div>
	<div class="col-md-4 col-sm-4 col-xs-12"></div>
	</div>
	</div>
	
	

</body>

</html>
