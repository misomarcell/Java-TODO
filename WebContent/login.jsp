<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="./stylesheet/style.css">
	<link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
</head> 
<body> 

<script>
	$(document).ready(function() {
		$("#register").on("click", function() {  
			if ( $("#password").val() != "" && $("#password2").val() != "") {
				console.log("Click");
				$.ajax({
					type : "POST", 
					url : "./register",
					data : {
						email : $("#email").val(),
						password: $("#password").val(),
						pasword2: $("#password2").val()
					},
					success : function(result) {
						alert(result)
					}
				});
				
			} else {
				alert("not match");
			}
			
		});
	});
</script>
${message} 
	<center><span class="title">TODO</span></center>
	<div class="main-container">
		<div class="login-container">
			<form method="POST" action="./login" >
				<input class="login-input" type="text" name="email" placeholder="E-mail" />
				<input class="login-input" type="password" name="password" placeholder="Password" />
				<input class="login-button" type="submit" value="Login" name="login" />
				<input class="login-input" type="password" name="password2" placeholder="Password again" />
				<input class="login-button" type="button" value="Register" id="register" />
			</form>	
		</div>
	</div>
</body>
</html>