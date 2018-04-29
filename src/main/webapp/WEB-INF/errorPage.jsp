<%-- 
    Document   : errorPage
    Created on : Apr 28, 2018, 11:33:23 AM
    Author     : Perlt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../bootstrap.jsp" %>
        <%@include file="../css/cssErrorPage.jsp" %>
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("error");
        %>
        <div id="clouds">
            <div class="cloud x1"></div>
            <div class="cloud x1_5"></div>
            <div class="cloud x2"></div>
            <div class="cloud x3"></div>
            <div class="cloud x4"></div>
            <div class="cloud x5"></div>
        </div>
        <div class='c'>
            <div style="color: darkblue" class='_1'>Unfortunately we have an error. Our monkeys are working hard to resolve it. we apologize </div>
            <br>
            <br>
            <br>
            <div style="color: darkblue" class='_1'><%= error%></div>
            <br>
            <br>
            <button class="btn btn-primary" onclick="goBack()">Go Back</button>
            <script>
                function goBack() {
                    window.history.back();
                }
            </script>
        </div>
    </body>
</html>
