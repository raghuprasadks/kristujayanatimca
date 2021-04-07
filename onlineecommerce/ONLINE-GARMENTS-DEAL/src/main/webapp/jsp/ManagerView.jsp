<%@page import="com.online.garments.deal.controller.ManagerCtl"%>
<%@page import="com.online.garments.deal.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.online.garments.deal.util.ServletUtility"%>
<%@page import="com.online.garments.deal.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
<nav aria-label="breadcrumb" role="navigation"> <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OGDView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Manager</li>
	</ol>
	</nav>

<div col-md-5 img-thumbnail">
           <div id="feedback"> <div class="container">
<div class="col-md-5">
    <div class="form-area">  
        <form role="form" action="<%=OGDView.MANAGER_CTL%>" method="post" >
        
       <jsp:useBean id="bean" class="com.online.garments.deal.bean.ManagerBean"
         					   scope="request"></jsp:useBean>
         					   
         					   <input type="hidden" name="id" value="<%=bean.getId()%>">
              <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
              <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
              <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
              <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
			
        <br style="clear:both">
                    <h3 style="margin-bottom: 15px; text-align: left: ;">Manager</h3>
                    <b><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font></b>
                
              <b><font color="Green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font></b>
                
                <%
							HashMap map = new HashMap();
							map.put("1", "Admin");
							map.put("2", "Manager");
				%>
                			<div class="form-group">
								<%=HTMLUtility.getList("role", String.valueOf(bean.getRole()), map)%> 
									<font  color="red"><%=ServletUtility.getErrorMessage("role", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="name"
									placeholder="Name" value="<%=DataUtility.getStringData(bean.getName())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("name", request)%></font>
							</div>
							
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
							
							<div class="form-group">
								<input type="password" class="form-control" 
									name="confirmPassword" placeholder="Confirm Password" value="<%=DataUtility.getStringData(bean.getConfirmPassword()) %>" >
						<font   color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="email"
									placeholder="Email Id" value="<%=DataUtility.getStringData(bean.getEmailId())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
							</div>
							
							<div class="form-group">
								<input type="text" class="form-control"  name="contact"
									placeholder="Contact No" value="<%=DataUtility.getStringData(bean.getContactNo())%>" > 
									<font  color="red"><%=ServletUtility.getErrorMessage("contact", request)%></font>
							</div>
							
					<div class="form-group">
                    <textarea class="form-control"  name="address"  placeholder="Address"  rows="4"><%=DataUtility.getStringData(bean.getAddress()) %></textarea>
                    <font  color="red"><%=ServletUtility.getErrorMessage("address", request)%></font>
                    </div>
                    
            
        <input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ManagerCtl.OP_SAVE%>">
								<input type="submit" name="operation"
								class="btn btn-primary pull-right" value="<%=ManagerCtl.OP_RESET%>">
        </form>
    </div>
</div>
</div> </div> <!--feedback-->
<br>

		<%@ include file="Footer.jsp"%>
		
</body>
</html>