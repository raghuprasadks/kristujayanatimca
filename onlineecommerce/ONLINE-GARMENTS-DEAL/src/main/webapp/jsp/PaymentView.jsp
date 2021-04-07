<%@page import="com.online.garments.deal.controller.CustomerCtl"%>
<%@page import="com.online.garments.deal.util.ServletUtility"%>
<%@page import="com.online.garments.deal.util.DataUtility"%>
<%@page import="com.online.garments.deal.bean.ProductBean"%>
<%@page import="com.online.garments.deal.model.ProductModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
<nav aria-label="breadcrumb" role="navigation"> <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OGDView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item"><a href="<%=OGDView.PRODUCT_LIST_CTL%>">Products</a></li>
		<li class="breadcrumb-item"><a href="<%=OGDView.CUSTOMER_CTL%>">Customer</a></li>
		<li class="breadcrumb-item active" aria-current="page">Payment</li>
	</ol>
	</nav>

<div class="container">
		<div class="card">
			<div class="container-fliud">
				<div class="wrapper row">
					<div class="preview col-md-6">
						<% Long pid=(Long)session.getAttribute("pid");
							ProductModel pModel=new ProductModel();
							ProductBean pBean=pModel.findByPk(pid);
						%>
						<div class="preview-pic tab-content">
						  <div class="tab-pane active" id="pic-1"><img src="<%=OGDView.APP_CONTEXT%>/image/<%=pBean.getImage()%>" /></div>
						  
						</div>
						
						
					</div>
					<div class="details col-md-6">
						<h3 class="product-title"><%=pBean.getProductName() %></h3>
						<div class="rating">
							<div class="stars">
								<span class="fa fa-star checked"></span>
								<span class="fa fa-star checked"></span>
								<span class="fa fa-star checked"></span>
								<span class="fa fa-star"></span>
								<span class="fa fa-star"></span>
							</div>
							<span class="review-no">41 reviews</span>
						</div>
						<h4 class="price">current price: <span><%=pBean.getPrice()%></span></h4>
						
						
						
					</div>
				</div>
			</div>
		</div>
	</div>
<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=OGDView.CUSTOMER_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="com.online.garments.deal.bean.PaymentBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Add Payment Detail</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                	<div class="form-group">
								<input type="text" class="form-control"  name="CName"
									placeholder="Card Holder Name" value="" > 
									
					</div>
        			<div class="form-group">
								<input type="text" class="form-control"  name="CNum"
									placeholder="Card Number "  > 
									
							</div>
							
        					<div class="form-group">
								<input type="text" class="form-control"  name="month"
									placeholder="Month"  > 
									
							</div>

							
							<div class="form-group">
								<input type="text" class="form-control"  name="year"
									placeholder="Year"  > 
									
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="Cvv"
									placeholder="CVV"  > 
									
							</div>
							
					
                    
            
        <input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=CustomerCtl.OP_PAYMENT%>">
								
        </form>
    </div>
</div>
</div> </div>

 <!--feedback-->
<br>
<div style="margin-top: 83px">
		<%@ include file="Footer.jsp"%>
		</div>
</body>
</html>