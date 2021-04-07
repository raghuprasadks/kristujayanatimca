<%@page import="com.online.garments.deal.controller.ProductCtl"%>
<%@page import="com.online.garments.deal.util.ServletUtility"%>
<%@page import="com.online.garments.deal.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
<nav aria-label="breadcrumb" role="navigation"> <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OGDView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Product</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=OGDView.PRODUCT_CTL%>" method="post" enctype="multipart/form-data" >
        
       <jsp:useBean id="bean" class="com.online.garments.deal.bean.ProductBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Product</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                
                			<div class="form-group">
								<input type="text" class="form-control"  name="code"
									placeholder="Item Code" value="<%=DataUtility.getStringData(bean.getItemCode())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("code", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="name"
									placeholder="Product Name" value="<%=DataUtility.getStringData(bean.getProductName())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
							
        					<div class="form-group">
								<input type="text" class="form-control"  name="quantity"
									placeholder="Product Quantity" value="<%=DataUtility.getStringData(bean.getProductQuantity())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("quantity", request)%></font>
							</div>
						
							
							<div class="form-group">
								<input type="text" class="form-control"  name="choice"
									placeholder="Product Choice" value="<%=DataUtility.getStringData(bean.getProductChoice())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("choice", request)%></font>
							</div>
							<div class="form-group">
								<input type="text" class="form-control"  name="price"
									placeholder="Product Price" value="<%=(bean.getPrice()!=0.0)?bean.getPrice():""%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("price", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="file" class="form-control"  name="photo"
									placeholder="Upload Image" value="<%=DataUtility.getStringData(bean.getImage())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("photo", request)%></font>
							</div>
							
					
                    
            
        <input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ProductCtl.OP_SAVE%>">
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ProductCtl.OP_RESET%>">
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>
<div style="margin-top: 3px">
		<%@ include file="Footer.jsp"%>
		</div>
</body>
</html>