<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
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
<title>Add product</title>
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

tr, td, th {
	vertical-align: middle;
}
</style>
</head>
<body>
	<div class="container">
		<header>
		<p style="font-size: 40pt; padding: 0;">Cart</p>
		<span>All the selected products in your cart</span> </header>
		<aside class="col-md-1"></aside>
		<section class="col-md-10">
		<div>
			<a href="/ManagementProduct/cart-clear" style="float: left;"
				class="btn btn-danger"> <i class="fas fa-times-circle"></i>
				Clear Cart
			</a> <a href="/ManagementProduct/cart-checkout" style="float: right;"
				class="btn btn-success"> <i class="fa fa-shopping-cart"></i>
				Check out
			</a> <span style="clear: both;"></span>
		</div>
		<div>
			<table class="table table-hover">
				<tr>
					<th style="width: 40%;">Product</th>
					<th>Quantity</th>
					<th>Unit price</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
				<c:if test="${listProducts != null}">
					<c:set var="total" value="${0}" />
					<c:forEach items="${listProducts}" var="product">
						<tr>
							<td style="width: 40%;">${product.name}</td>
							<td>${product.unitInStock}</td>
							<td><fmt:formatNumber type="number" maxFractionDigits="3"
									value="${product.unitPrice }"/></td>
							<td><fmt:formatNumber type="number" maxFractionDigits="3"
									value="${product.unitPrice }" /></td>
							<td><a
								href="/ManagementProduct/cart-remove?id=<c:out value='${product.productId}'/>"
								class="btn-danger"> <i class="fas fa-times-circle "></i>
									Remove
							</a></td>
							<c:set var="total"
								value="${total + product.unitPrice*product.unitInStock}" />
						</tr>
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td style="font-weight: bold;">Grant total</td>
						<td colspan="2 " style="text-align: left;">
								<fmt:formatNumber type="number" maxFractionDigits="3"
									value="${total }" />
								</td>
					</tr>
				</c:if>
			</table>
			<a href="/ManagementProduct/product" style="float: left;"
				class="btn btn-success "> <i class="fas fa-arrow-circle-left "></i>
				Continue shopping
			</a>

		</div>
		</section>
		<aside class="col-md-1 "></aside>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.slim.min.js "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js "></script>
</body>
</html>