<%@page import="FunctionLayer.entities.Employee"%>
<% Employee empNavBar = (Employee) session.getAttribute("employee"); %>
<link href="../../css/navBar.css" rel="stylesheet" type="text/css"/>
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
        background-color: activeborder;
        text-decoration: none;
    }
</style>
<ul>
    <li><a href="FrontController?command=Overview">Overview</a></li>
    <li><a href="FrontController?command=GetOrders">Orders</a></li>
    <li><a href="FrontController?command=UpdateStylePage">Style page</a></li>
        <% if (empNavBar.getAuthenticationLevel() >= 2) { %>
    <li><a href="FrontController?command=UpdateStaff" style="width: 100%">Update staff</a></li>
        <%}%>
</ul>
