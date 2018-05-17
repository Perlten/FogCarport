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

<script>
    var x = document.cookie;
    window.onload = function () {
        if(getCookie("refresh") === "true"){
           document.cookie = "refresh = false";
          location.reload();
        }
    };
    
    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
</script>

<%
    if (session.getAttribute("error") != null) {
%>
<div class="alert alert-danger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <%= session.getAttribute("error")%>
</div>
<%
        session.setAttribute("error", null);
    }
%>