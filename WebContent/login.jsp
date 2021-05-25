<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet"
	href="https://cdnj`s.cloudflare.com/ajax/libs/bootswatch/4.6.0/litera/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
<link rel="stylesheet" href="assets/css/style.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
* {
	margin: 0 auto;
}

header {
	padding: 20px;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	background-color: #eeeeee;
	margin-bottom: 30px;
	padding-left: 100px;
}

.box {
	width: 400px;
	text-align: center;
	align-items: center;
	align-content: center;
	border: 1px solid lightgrey;
	height: 100%;
	text-align: center;
	border-radius: 5px 5px 5px 5px;
	margin: auto;
}

.box p {
	padding: 10px;
}

input[type="text"] {
	margin-bottom: 15px;
}

input[type="password"] {
	margin-bottom: 15px;
}

.login {
	padding: 15px;
}

button {
	height: 40px;
}

section {
	text-align: center;
}

.error {
	color: red;
}
</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css"
	rel="stylesheet" />
<script th:inline="javascript">
	toastr.options = {
		"closeButton" : true,
		"debug" : true,
		"newestOnTop" : true,
		"progressBar" : true,
		"positionClass" : "toast-bottom-right",
		"preventDuplicates" : false,
		"showDuration" : "300",
		"hideDuration" : "1000",
		"timeOut" : "5000",
		"extendedTimeOut" : "1000",
		"showEasing" : "swing",
		"hideEasing" : "linear",
		"showMethod" : "show",
		"hideMethod" : "hide"
	}
	function showMessage(message) {
		toastr["error"](message);
	}
</script>
</head>
<body>
	<% if(session != null && session.getAttribute("message") != null){ %>
	<% String message = (String) session.getAttribute("message"); %>
	<% if(message != null) {%>
	<% pageContext.setAttribute("message",message); %>
	<c:set var="message" value="${message}" />
	<script>
				window.addEventListener("load", function() {
					showMessage('<c:out value="${message}"/>')
				});
			</script>
	<% session.setAttribute("message", null); %>
	<%} %>
	<%}%>
	<div class="container">
		<header>
		<p style="font-size: 40pt;">Mobile Store</p>
		</header>
		<section>
		<form action="login" method="post">
			<div class="box">
				<p style="background-color: #eeeeee; text-align: left;">Please
					sign in</p>
				<div class="login">
					<input type="text" name="username" class="form-control"
						placeholder="User Name" required="" autofocus="" />
					<c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'userName' }">
									<span class="error">${customerError.message}</span>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
					<input type="password" name="password" class="form-control"
						placeholder="Password" required="" autofocus="" />
					<c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'password' }">
									<span class="error">${customerError.message}</span>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
					<button class="btn btn-success btn-block center" type="submit">Login</button>
				</div>
			</div>
		</form>
		</section>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>

</body>
</html>