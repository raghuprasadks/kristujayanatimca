<%@page import="com.online.garments.deal.model.CustomerModel"%>
<%@page import="com.online.garments.deal.controller.CustomerListCtl"%>
<%@page import="com.online.garments.deal.controller.CustomerCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.online.garments.deal.bean.CustomerBean"%>
<%@page import="com.online.garments.deal.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CustomerList</title>
</head>
<body>
<%@ include file="Header.jsp" %>
<br>
<nav aria-label="breadcrumb" role="navigation"> <nav
		aria-label="breadcrumb" role="navigation">
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="<%=OGDView.WELCOME_CTL%>">Home</a></li>
		<li class="breadcrumb-item active" aria-current="page">Customer</li>
	</ol>
	</nav>
	</nav>
	<center>
		<h3>Customer</h3>
		<b><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></b>
			<b><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></b>
	</center>
	<form action="<%=OGDView.CUSTOMER_LIST_CTL%>" method="post">
<table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">S No.</th>
      <th scope="col">Name</th>
      <th scope="col">Item Code</th>
      <th scope="col">Contact No</th>
      <th scope="col">Age</th>
      <th scope="col">Address</th>
    </tr>
  </thead>
  <tbody>
  <%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					CustomerBean bean = null;
					List list = ServletUtility.getList(request);
					Iterator<CustomerBean> iterator = list.iterator();
					while (iterator.hasNext()) {
						bean = iterator.next();
				%>
    <tr>
      <th scope="row"><%=index++%></th>
      <td><%=bean.getName()%></td>
      <td><%=bean.getItemCode() %></td>
      <td><%=bean.getContectNo() %></td>
      <td><%=bean.getAge()%></td>
      <td><%=bean.getAddress() %></td>
    </tr>    
  <%} %>
  </tbody>
</table>

<table  >
  <thead >
    <tr>
      <th ><input type="submit" name="operation" class="btn btn-primary pull-center" value="<%=CustomerListCtl.OP_PREVIOUS%>" <%=(pageNo == 1) ? "disabled" : ""%>></th>
      <%
						CustomerModel model = new CustomerModel();
					%>
      <th ><input type="submit" name="operation" class="btn btn-primary pull-center" value="<%=CustomerListCtl.OP_NEXT%>"
     <%=((list.size() < pageSize) || model.nextPK() - 1 == bean.getId()) ? "disabled" : ""%> ></th>
    </tr>
  </thead>
 
</table>
<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
</form>
<br>
<div style="margin-top: 193px">
<%@ include file="Footer.jsp" %>
</div>
</body>
</html>