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
	<a href="/ManagementProduct/logout">Logout</a>
	<h2>Demo JSP-Servlet Upload File</h2>
	<form method="post" action="product-insert"
		enctype="multipart/form-data">
		<table style="with: 50%">
			<tr>
				<td>Product Name</td>
				<td><input type="text" name="name" /></td>
				<td><c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'name' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td>unitPrice</td>
				<td><input type="number" name="unitPrice" /></td>
				<td><c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'unitPrice' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td>unitInStock</td>
				<td><input type="number" name="unitInStock" /></td>
				<td><c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'unitInStock' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td>description</td>
				<td><input type="text" name="description" /></td>
				<td><c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'description' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td>manufacturer</td>
				<td><input type="text" name="manufacturer" /></td>
				<td><c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'manufacturer' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td>category</td>
				<td><input type="text" name="category" /></td>
				<td><c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'category' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td>typeConditionProduct</td>
				<td><input type="radio" name="typeConditionProduct" value="0"
					checked="checked" />new <input type="radio"
					name="typeConditionProduct" VALUE="1" />old <input type="radio"
					name="typeConditionProduct" VALUE="2" />refurbished</td>
			</tr>
			<tr>
				<td>image</td>
				<td><input type="file" name="image" size="60" /></td>
				<td><c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'image' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Add Product"></td>
			</tr>
		</table>
	</form>
</body>
</html>