<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>
	<div class="container">
		<header> <a href="/ManagementProduct/logout"
			class="btn btn-danger" style="float: right;">Logout</a>
		<p style="font-size: 40pt; padding: 0;">Products</p>
		<span>Add products</span> </header>

		<section> <span style="font-size: 20pt">Add new
			product</span>
		<hr style="padding: 0; margin: 0; border: 0.5px solid lightgrey;">
		<br>
		<form method="post" action="product-insert"
			enctype="multipart/form-data">
			<table>
				<tr>
					<th>Product name</th>
					<td><input type="text" name="name" class="form-control"
						placeholder="" required="" autofocus="" /></td>
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
					<th>Unit price</th>
					<td><input type="number" name="unitPrice" class="form-control"
						placeholder="" required="" autofocus="" /></td>
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
					<th>Units in stock</th>
					<td><input type="number" value="0" name="unitInStock"
						class="form-control" placeholder="" required="" autofocus="" /></td>
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
					<th>Description</th>
					<td><textarea cols="35" name="description"></textarea></td>
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
					<th>Manufacturer</th>
					<td><input type="text" name="manufacturer"
						class="form-control" placeholder="" required="" autofocus="" /></td>
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
					<th>Category</th>
					<td><input type="text" name="category" class="form-control"
						placeholder="" required="" autofocus="" /></td>
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
					<th>Condition</th>
					<td><input type="radio" name="typeConditionProduct" value="0"
						checked>New <input type="radio"
						name="typeConditionProduct" style="margin-left: 5px;" value="1">Old
						<input type="radio" name="typeConditionProduct"
						style="margin-left: 5px;" value="2">Refurbished</td>
				</tr>
				<tr>
					<th>Product Image File</th>
					<td><input type="file" id="image" name="image"
						accept=".jpg, .jpeg, .png" size="60"></td>
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
					<th></th>
					<td>
						<button class="btn btn-primary center" type="submit">Add
							product</button>
					</td>
				</tr>
			</table>
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