<%-- 
    Document   : customerNavBar
    Created on : 15-05-2018, 18:13:04
    Author     : adamlass
--%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%
    HashMap<String, Boolean> allowed = (HashMap<String, Boolean>) session.getAttribute("allowed");
    String active = request.getServletPath();

    if (allowed == null) {
        allowed = new HashMap<>();
        request.getSession().setAttribute("allowed", allowed);
    }
%>

<ul class="nav nav-tabs">

    <li style="margin-right: 5px; margin-left: 5px;">
        <a href="index.jsp">
            <img src="content/foglogok.png" alt="" style="height: 42px; margin: 0px"/>
        </a>
    </li>
    <li class="nav-item">
        <% if (allowed.getOrDefault("Dimentions", true)) {%>
        <a class="nav-link text-success <% if (active.equals("/index.jsp")) {
                out.print("active");
            } %>" href="FrontController?command=GiveDimentionsPage">Dimentions</a>
        <%} else {%>
        <a class="nav-link disabled" href="#">Dimentions</a>
        <%}%>
    </li>


    <li class="nav-item">
        <% if (allowed.getOrDefault("Styling", false)) {%>
        <a class="nav-link text-success <% if (active.equals("/WEB-INF/styling.jsp")) {
                out.print("active");
            } %>" href="FrontController?command=Styling">Styling</a>
        <%} else {%>
        <a class="nav-link disabled" href="#">Styling</a>
        <%}%>
    </li>

    <li class="nav-item">
        <% if (allowed.getOrDefault("Credentials", false)) {%>
        <a class="nav-link text-success <% if (active.equals("/WEB-INF/credentialsPage.jsp")) {
                out.print("active");
            } %>" href="FrontController?command=GiveCredentialsPage">Credentials</a>
        <%} else {%>
        <a class="nav-link disabled" href="#">Credentials</a>
        <%}%>
    </li>

    <li class="nav-item">
        <% if (allowed.getOrDefault("Confirm", false)) {%>
        <a class="nav-link text-success <% if (active.equals("/WEB-INF/confirm.jsp")) {
                out.print("active");
            } %>" href="FrontController?command=LoadOrder">Confirm</a>
        <%} else {%>
        <a class="nav-link disabled" href="#">Confirm</a>
        <%}%>
    </li>




</ul>
<br>
<%@include file="includeErrorBanner.jsp" %>



<div style="margin-left: 15px; margin-right: 15px">
    <% if (session.getAttribute("welcommed") == null) {%>
    <div class="alert alert-success alert-dismissible fade show">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        To log in as an Employee, please follow <a href="employeeLogin.jsp">this link!</a>
    </div>
    <%}%>
</div>

