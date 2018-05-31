<%@page import="FunctionLayer.entities.Employee"%>
<% Employee empNavBar = (Employee) session.getAttribute("employee"); %>
<!--This is the only working solution at the moment-->
<style>
    ul {
        list-style-type: none;
        overflow: hidden;
        padding: 0;
        background-color: #004794;
    }

    li a {
        display: block;
        width: 120px;
        background-color: #004187;
        color: #fbfbfb;
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
        background-color: white;
        text-decoration: none;
    }
</style>

<ul>
    <li>
        <a href="index.jsp" style="height: 47px; width: 55px; padding: 0px">
            <img src="content/FOGLogo.jpg" alt="" style="height: 47px; margin: 0px"/>
        </a>
    </li>
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
<script>
    function setUserStatus() {
        sessionStorage.setItem("employee", "true");
    }
</script>

<%@include file="includeErrorBanner.jsp" %>
