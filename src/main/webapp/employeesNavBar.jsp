<%@page import="FunctionLayer.entities.Employee"%>
<% Employee empNavBar = (Employee) session.getAttribute("employee"); %>
<link href="css/NavBarCss.css" rel="stylesheet" type="text/css"/>
<ul>
    <li><a href="FrontController?command=Overview">Overview</a></li>
    <li><a href="FrontController?command=GetOrders">Orders</a></li>
    <li><a href="FrontController?command=UpdateStylePage">Style page</a></li>
    <% if(empNavBar.getAuthenticationLevel() >= 2){ %>
    <li><a href="FrontController?command=UpdateStaff">Update staff</a></li>
    <%}%>
</ul>
