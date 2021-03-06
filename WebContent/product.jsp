<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>list product</title>
<style type="text/css">
* {
	margin: 0 auto;
	padding: 0;
}

header {
	padding: 20px;
	font-family: Verdana, Geneva, Tahoma, sans-serif;
	float: left;
}

section {
	padding-left: 5px;
}

.product {
	width: 260px;
	border: 1px solid lightgrey;
	padding: 10px;
	margin: 10px;
	display: inline-block;
	vertical-align: top;
	clear: both;
}

.image {
	width: 240px;
	height: 260px;
	margin-bottom: 5px;
}

.header-right {
	float: right;
}
</style>

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
		<header class="col-md-12"
			style="height: 200px;background-color: #EEEEEE; padding-top:30px;">

		<span class=" header-right"> <%
 	if (session.getAttribute("username") == null) {
 %> <a style="background-color: red;" class="btn btn-danger"
			href="/ManagementProduct/login"><i class="fa fa-logout"></i>Login</a>
			<%
				} else {
			%> <a style="background-color: red;" class="btn btn-danger"
			href="/ManagementProduct/logout"><i class="fa fa-logout"></i>
				Logout</a> <%
 	}
 %> <a style="background-color: white;" class="btn"
			href="/ManagementProduct/cart"><i class="fa fa-shopping-cart"></i>
				View Cart</a><br>
		</span>
		<p style="font-size: 40pt;">Products</p>
		<span>All the available product in our store</span> </header>
		<section> <c:if test="${listProduct != null}">
			<c:forEach items="${listProduct}" var="product">
				<div class="product">
					<div>
						<h3>${product.name}</h3>
						<img src="images/${product.image}" width="500" height="750"
							class="image"> <input type="hidden" name="modelDescription"
							value="${product.description}" />
						<p>${product.description}</p>
						<p>
							<fmt:formatNumber type="number" maxFractionDigits="3"
								value="${product.unitPrice }" />
							USD
						</p>
						<p>${product.unitInStock} unitsinstock</p>
						<a class="btn btn-primary"
							href="/ManagementProduct/product-detail?id=<c:out value='${product.productId}'/>">Detail</a>
						<a class="btn btn-warning"
							href="/ManagementProduct/cart-order?id=<c:out value='${product.productId}'/>"><i
							class="fa fa-shopping-cart"></i>Order now</a>
					</div>
				</div>
			</c:forEach>
		</c:if> </section>
	</div>

</body>
</html>