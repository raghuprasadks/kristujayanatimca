<%@page import="com.online.garments.deal.controller.LoginCtl"%>
<%@page import="com.online.garments.deal.controller.OGDView"%>
<%@page import="com.online.garments.deal.bean.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<!-- <link href="/ONLINE-GARMENTS-DEAL/css/main.css" rel="stylesheet"> -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" /> -->
<link href="/ONLINE-GARMENTS-DEAL/css/product.css" rel="stylesheet">

</head>
<body>

	<%
		LoginBean userBean = (LoginBean) session.getAttribute("user");

		boolean userLoggedIn = userBean != null;

		String welcomeMsg = "Hi, ";

		if (userLoggedIn) {
			welcomeMsg += userBean.getLogin();
		} else {
			welcomeMsg += "Guest";
		}
	%>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"> <a
		class="navbar-brand" href="#">ONLINE GARMENTS
		DEAL</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="<%=OGDView.WELCOME_CTL%>">Home <span class="sr-only">(current)</span></a>
			</li>
			<%
				if (userLoggedIn) {
			%>
			<%
				if (userBean.getRole() == 1) {
			%>
			
			<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.MANAGER_CTL%>">Add Manager</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.PRODUCT_CTL%>">Add Product</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.PRODUCT_LIST_CTL%>">Products</a></li>
				<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.CUSTOMER_LIST_CTL%>">Customer</a></li>
				<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.PAYMENT_LIST_CTL%>">Payments</a></li>
				
				<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.BOOK_LIST_CTL%>">Book List</a></li>
			
			<%
				} else if (userBean.getRole() == 2) {
			%>
			
			<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.PRODUCT_CTL%>">Add Product</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.PRODUCT_LIST_CTL%>">Products</a></li>
				<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.CUSTOMER_LIST_CTL%>">Customer</a></li>
				<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.PAYMENT_LIST_CTL%>">Payments</a></li>
				<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.BOOK_LIST_CTL%>">Book List</a></li>
				
			<%
				} else if (userBean.getRole() == 3) {
			%>
			
			<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.PRODUCT_LIST_CTL%>">Products</a></li>
				
			<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.BOOK_LIST_CTL%>">Book List</a></li>
			<%
				
			%>
			<%
				}} else {
			%>
			<li class="nav-item"><a class="nav-link"
				href="<%=OGDView.PRODUCT_LIST_CTL%>">Products</a></li>
			<!-- <li class="nav-item"><a class="nav-link" href="#">Contact Us</a></li>
			<li class="nav-item"><a class="nav-link" href="#">About Us</a></li> -->
			<%
				}
			%>
		</ul>
		<form class="form-inline my-2 my-lg-0">
	
			<%
				if (userLoggedIn) {
			%>
			<a class="nav-link" class="btn btn-outline-success my-2 my-sm-0"
				href="<%=OGDView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</a>
			<%
				} else {
			%>
			<a class="nav-link" class="btn btn-outline-success my-2 my-sm-0"
				href="<%=OGDView.LOGIN_CTL%>">Login</a>
			<%
				}
			%>
		</form>
	</div>
	</nav>
</body>
</html>