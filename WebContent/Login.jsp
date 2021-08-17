<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
</head>
<body>	
<div style="position: relative;">
	<img src="bank.jpg" class="img-fluid" alt="Responsive image">
<div style="position: absolute;top: 8px;
  left: 16px;">
		<form action="${pageContext.request.contextPath}/UserLoginServlet" method="post">
		   	<h1>Login</h1>
		    	
		    <div>
				<input name="form-username" type="text" class="input username" placeholder="Username" onfocus="this.value=''" required/>
		    	<input name="form-password" type="password" class="input password" placeholder="Password" onfocus="this.value=''" required/>
		    </div>
		    <div>
		    	<input type="submit" name="submit" value="Login"/>
		    </div>
		</form>
</div>
</div>
</body>
</html>