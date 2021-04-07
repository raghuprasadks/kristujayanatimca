<%@page import="com.online.garments.deal.util.ServletUtility"%>
<%@page import="com.online.garments.deal.bean.ProductBean"%>
<%@page import="com.online.garments.deal.model.ProductModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>
</head>
<body>
<%@ include file="Header.jsp" %>
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
							<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
						</div>
						<h4 class="price">current price: <span><%=pBean.getPrice()%></span></h4>
						
						
						
					</div>
				</div>
			</div>
		</div>
	</div>
<div style="margin-top: 100px">
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>