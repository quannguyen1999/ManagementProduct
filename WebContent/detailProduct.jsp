<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
	background-color: #eeeeee;
	margin-bottom: 30px;
	padding-left: 100px;
	height: 170px;
}

input[type="text"], input[type="number"], input[type="textarea"],
	textarea {
	margin-bottom: 15px;
}

input[type="file"] {
	margin-bottom: 15px;
	padding: 10px;
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
	width: 90%;
	height: 720px;
}

th {
	text-align: right;
	padding-right: 20px;
}

td {
	padding-left: 20px;
}

table {
	float: left;
	clear: both;
	margin-left: 30px;
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
	function dialogSuccess(message) {
		toastr["success"](message);
	}
	function dialogError(message){
		toastr["error"](message);
	}
		
</script>
</head>
<body>
	<% if(session != null && session.getAttribute("success") != null){ %>
	<% String message = (String) session.getAttribute("success"); %>
	<% if(message != null) {%>
	<% pageContext.setAttribute("message",message); %>
	<c:set var="message" value="${message}" />
	<script>
				window.addEventListener("load", function() {
					dialogSuccess('<c:out value="${message}"/>')
				});
			</script>
	<% session.setAttribute("success", null); %>
	<%} %>
	<%} else{%>
	<% String message = (String) session.getAttribute("error"); %>
	<% if(message != null) {%>
	<% pageContext.setAttribute("message",message); %>
	<c:set var="message" value="${message}" />
	<script>
				window.addEventListener("load", function() {
					dialogError('<c:out value="${message}"/>')
				});
			</script>
	<% session.setAttribute("error", null); %>
	<%} %>
	<%} %>
	<div class="container">
		<header>
		<button type="button" class="btn btn-danger" style="float: right;">Logout</button>
		<p style="font-size: 40pt; padding: 0;">Products</p>
		<span>Add products</span> </header>

		<section class="col-md-12">
		<div class="col-md-6">
			<img src="images/${product.image}" alt="" width="100%" height="650px">
		</div>
		<div class="col-md-6 section-content">
			<span style="font-size: 20px;">${product.name}</span> <b>
				<p>
					Item code: <span
						style="border-radius: 5px; padding: 5px; font-weight: bold; background-color: orange; color: white;">${product.productId }</span>
				</p>
			</b> <b><p>Manufactor: ${product.manufacturer }</p></b> <b><p>
					Catogory: <a href="">${product.category}</a>
				</p></b> <b><p>Available units in stock:</p></b>
			<p>
				<fmt:formatNumber type="number" maxFractionDigits="3"
					value="${product.unitPrice }" />
				USD
			</p>
			<a href="/ManagementProduct/product" class="btn btn-success"><i
				class="fa fa-arrow-left"></i> Back
				</button> <a
				href="/ManagementProduct/cart-order-detail?id=<c:out value='${product.productId}'/>"
				class="btn btn-warning"><i class="fa fa-shopping-cart"></i>
					Order Now</a>
		</div>
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