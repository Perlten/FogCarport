<%@page import="FunctionLayer.entities.Employee"%>
<% Employee empNavBar = (Employee) session.getAttribute("employee"); %>
<!--This is the only working solution at the moment-->
<style>
    ul {
        list-style-type: none;
        overflow: hidden;
        padding: 0;
        background-color: orange;
    }

    li a {
        display: block;
        width: 120px;
        background-color: darkorange;
        color: white;
        text-align: center;
        font-size: large;
        padding: 10px;
        text-decoration: none;
    }

    li{
        float: left;
    }

    li a:hover {
        color: black;
        background-color: activeborder;
        text-decoration: none;
    }
</style>

<ul>
    <li style="float: right;"><a href="FrontController?command=LogOut">Log Out</a></li>
    <li><a href="FrontController?command=Overview">Overview</a></li>
    <li><a href="FrontController?command=GetOrders">Orders</a></li>
        <% if (empNavBar.getAuthenticationLevel() >= 2) { %>
    <li><a href="FrontController?command=UpdateStylePage">Stylings</a></li>
    <li><a href="FrontController?command=UpdateStaff" style="width: 100%">Update staff</a></li>
        <%}%>
        <% if (empNavBar.getAuthenticationLevel() >= 3) { %>
    <li><a href="FrontController?command=AllEventsPage">All Events</a></li>
        <%}%>
</ul>

<%@include file="includeErrorBanner.jsp" %>
