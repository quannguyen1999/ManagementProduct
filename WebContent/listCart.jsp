<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>
	<a href="/ManagementProduct/cart-clear">Clear Cart</a>
	<a href="/ManagementProduct/cart-checkout">Check out</a>
	<a href="/ManagementProduct/product">Continue shopping</a>
	<table border="1">
		<tr>
			<th>id</th>
			<th>name</th>
			<th>Quantity</th>
			<th>UnitPrice</th>
			<th>image</th>
			<th>function</th>
		</tr>
		<c:if test="${listProducts != null}">
			<c:forEach items="${listProducts}" var="product">
				<tr>
					<td>${product.productId}</td>
					<td>${product.name}</td>
					<td>${product.unitInStock}</td>
					<td>${product.unitPrice}</td>
					<td><img src="images/${product.image}" width="500"
						height="750"></td>
					<td>
						<a href="/ManagementProduct/cart-remove?id=<c:out value='${product.productId}'/>">remove</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</body>
</html>