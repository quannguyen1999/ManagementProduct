<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
	.error{
		color:red;
	}
</style>
</head>
<body>
	<form action="login" method="post">
		<table style="with: 50%">
			<tr>
				<td>Product Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>unitPrice</td>
				<td><input type="password" name="unitPrice" /></td>
			</tr>
				<tr>
				<td>unitInStock</td>
				<td><input type="text" name="unitInStock" /></td>
			</tr>
				<tr>
				<td>description</td>
				<td><input type="text" name="unitPrice" /></td>
			</tr>
				<tr>
				<td>unitPrice</td>
				<td><input type="text" name="unitPrice" /></td>
			</tr>
				<tr>
				<td>manufacturer</td>
				<td><input type="text" name="manufacturer" /></td>
			</tr>
				<tr>
				<td>category</td>
				<td><input type="text" name="category" /></td>
			</tr>
				<tr>
				<td>typeConditionProduct</td>
				<td><input type="text" name="typeConditionProduct" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Add Product"></td>
			</tr>
		</table>
		<input type="submit" value="Login" />
	</form>

</body>
</html>