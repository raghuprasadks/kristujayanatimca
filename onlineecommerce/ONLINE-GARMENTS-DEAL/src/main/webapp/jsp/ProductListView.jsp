<%@page import="com.online.garments.deal.util.PropertyReader"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.online.garments.deal.bean.ProductBean"%>
<%@page import="com.online.garments.deal.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
<nav aria-label="breadcrumb" role="navigation"> <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OGDView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Products</li>
	</ol>
</nav>

<center>
<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
</center>
<hr>
<form action="<%=OGDView.PRODUCT_LIST_CTL%>" method="post">
<div class="container">
    <h3 class="h3">Products</h3>
    
    <div class="row">
    		<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;
					
					
					ProductBean bean = null;
					
					List list = ServletUtility.getList(request);
					
					Iterator<ProductBean> it = list.iterator();
					
					while (it.hasNext()) {
						bean = it.next();
				%>
    
        <div class="col-md-3 col-sm-6">
            <div class="product-grid4">
                <div class="product-image4">
                    <a href="#">
                        <img class="pic-1" src="<%=OGDView.APP_CONTEXT%>/image/<%=bean.getImage()%>">
                        <img class="pic-2" src="<%=OGDView.APP_CONTEXT%>/image/<%=bean.getImage()%>">
                    </a>
                    
                    
                </div>
                <div class="product-content">
                    <h3 class="title"><a href="#"><%=bean.getProductName()%></a></h3>
                    <div class="price">
                        <%=PropertyReader.getValue("cur")%><%=bean.getPrice()%>
                        <!-- <span>$16.00</span> -->
                    </div>
                    <%if(userLoggedIn){%>
                    <%if(userBean.getRole()!=3){ %>
                    <a class="add-to-cart" href="ProductListCtl?idd=<%=bean.getId()%>">Delete</a>
                    <a class="add-to-cart" href="<%=OGDView.APP_CONTEXT%>/ctl/ProductCtl?id=<%=bean.getId()%>">Edit</a>
                    <%}else{ %>
                     <a class="add-to-cart" href="CustomerCtl?pid=<%=bean.getId()%>">Book</a>
                    <%} %>
                    <%}else{ %>
                    <a class="add-to-cart" href="CustomerCtl?pid=<%=bean.getId()%>">Book</a>
                    <%} %>
                </div>
            </div>
        </div>
	<%} %>
    </div>
</div>
<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
<hr>


<%@ include file="Footer.jsp" %>
</body>
