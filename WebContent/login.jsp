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
				<td>UserName</td>
				<td><input type="text" name="username" /></td>
				<td>
					<c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'userName' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
				<td class="error">
					<c:if test="${listCustomerError != null}">
						<c:forEach items="${listCustomerError}" var="customerError">
							<tr>
								<c:if test="${customerError.field == 'password' }">
									<td><span class="error">${customerError.message}</span></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:if>
				</td>
			</tr>
		</table>
		<input type="submit" value="Login" />
	</form>

</body>
</html>