<%-- 
    Document   : customerNavBar
    Created on : 15-05-2018, 18:13:04
    Author     : adamlass
--%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%
    HashMap<String, Boolean> allowed = (HashMap<String, Boolean>) session.getAttribute("allowed");

    if (allowed == null) {
        allowed = new HashMap<>();
        request.getSession().setAttribute("allowed", allowed);
    }
%>

<ul class="nav nav-tabs">

    <li class="nav-item">
        <% if (allowed.getOrDefault("Dimentions",true)) { %>
        <a class="nav-link active" href="FrontController?command=GiveDimentionsPage">Dimentions</a>
        <%} else {%>
        <a class="nav-link disabled" href="">Dimentions</a>
        <%}%>
    </li>


    <li class="nav-item">
        <% if (allowed.getOrDefault("Styling",false)) { %>
        <a class="nav-link" href="FrontController?command=Styling">Styling</a>
        <%} else {%>
        <a class="nav-link disabled" href="">Styling</a>
        <%}%>
    </li>

    <li class="nav-item">
        <% if (allowed.getOrDefault("Credentials",false)) { %>
        <a class="nav-link" href="FrontController?command=GiveCredentialsPage">Credentials</a>
        <%} else {%>
        <a class="nav-link disabled" href="">Credentials</a>
        <%}%>
    </li>

    <li class="nav-item">
        <% if (allowed.getOrDefault("Confirm",false)) { %>
        <a class="nav-link disabled" href="FrontController?command=LoadOrder">Confirm</a>
        <%} else {%>
        <a class="nav-link disabled" href="">Confirm</a>
        <%}%>
    </li>


</ul>
<br>