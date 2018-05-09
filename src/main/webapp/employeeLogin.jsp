<%-- 
    Document   : employeeLogin
    Created on : May 8, 2018, 12:31:40 PM
    Author     : Perlt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="bootstrap.jsp" %>
        <link href="css/EmployeeLogin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container-fluid">
        <div class="loginStyle">
            <form action="FrontController" method="post">
                <h1>Login</h1>
                <div class="form-group">
                    <input type="hidden" name="command" value="LoginVerification">
                    <label class="control-label">Username</label>
                    <input type="text" class="form-control" name="username" placeholder="Username">

                    <label class="control-label">Password</label>
                    <input type="password" class="form-control" name="password" placeholder="Password">
                    <br>
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
            <form action="recoverPassword.jsp" method="post">
                <label class="control-label">Forgot password</label>
                <br>
                <button type="submit" class="btn btn-primary">Next</button>
            </form>
        </div>
        </div>
    </body>
</html>
