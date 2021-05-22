<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	
	<table>
		<tr>
			<td>${product.productId}</td>
			<td><img src="images/${product.image}" width="500"
						height="750"></td>
			<td>
				<a href="/ManagementProduct/product">back</a>
				<a href="/ManagementProduct/cart-order-detail?id=<c:out value='${product.productId}'/>">Order now</a>
			</td>
		</tr>
	</table>
</body>
</html>