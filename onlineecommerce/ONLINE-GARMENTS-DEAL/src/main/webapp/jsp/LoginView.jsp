<%@page import="com.online.garments.deal.controller.LoginCtl"%>
<%@page import="com.online.garments.deal.util.ServletUtility"%>
<%@page import="com.online.garments.deal.util.DataUtility"%>
<%@page import="com.online.garments.deal.controller.OGDView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

</head>
<body>
	<%@ include file="Header.jsp"%>
<br>
<nav aria-label="breadcrumb" role="navigation"> <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OGDView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Login</li>
	</ol>
	</nav>
<br>	
	<div col-md-5img-thumbnail">
		<div id="feedback">
			<div class="container">
				<div class="col-md-5">
					<div class="form-area">
						<form role="form" action="<%=OGDView.LOGIN_CTL%>" method="post" >
							<br style="clear: both">
		
							<jsp:useBean id="bean" class="com.online.garments.deal.bean.LoginBean"
         					   scope="request"></jsp:useBean>
         			   <% String uri=(String)request.getAttribute("uri");%>
		
             			 <input type="hidden" name="uri" value="<%=uri%>">
             			 
             	<input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            
							<h3 style="margin-bottom: 15px; text-align: left: ;">Login</h3>
							
								<b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
							
							
							<div class="form-group">
								<input type="text" class="form-control"  name="login"
									placeholder="Login Id" value="<%=DataUtility.getStringData(bean.getLogin())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("login", request)%></font>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" 
									name="password" placeholder="Password" value="<%=DataUtility.getStringData(bean.getPassword()) %>" >
						<font   color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
							</div>
							<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=LoginCtl.OP_SIGN_IN %>">
							<%-- 	<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=LoginCtl.OP_SIGN_UP %>"> --%>
								<%-- <a href="<%=OGDView.FORGET_PASSWORD_CTL%>"><b>Forget my password</b></a> --%>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--feedback-->
		<br>
		<div style="margin-top: 147px">
		<%@ include file="Footer.jsp"%>
		</div>
</body>
</html>