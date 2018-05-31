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
        <title>Login</title>
        <%@include file="/WEB-INF/include/includeBootstrap.jsp" %>

    </head>
    <body>
    <center>
        <%@include file="WEB-INF/include/includeErrorBanner.jsp" %>
        <div class="container-fluid">
            <div class="card bg-secondary text-white" style="width: 500px; margin: auto">
                <div class="card-header">
                    <h1>Login</h1>

                </div>
                <div class="card-body">

                    <form action="FrontController" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="LoginVerification">
                            <label class="control-label">Username</label>
                            <input type="text" class="form-control" name="username" placeholder="Username">

                            <label class="control-label">Password</label>
                            <input type="password" class="form-control" name="password" placeholder="Password">
                            <br>
                        </div>
                </div>
                <div class="card-footer">
                    <button type="submit" class="btn btn-primary" style="margin-bottom: 10px">Login</button>
                    </form>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="ForgotPassword">
                        <button type="submit" class="btn btn-dark">Forgot password</button>
                    </form>
                </div>

            </div>
            <br>
            <div>
                <div class="card" style="width: 400px; background-color: lightgrey; font-family: initial; color: darkslategrey">
                    <div class="card-header">
                        <h4>Exam access</h4>
                    </div>
                    <div class="card-body">
                        <p>login: <b>admin</b></p>
                        <p>password: <b>1234</b></p>

                        <br>

                        <h6>Gmail for password reset:</h6>
                        <p>login: <b>FogCarportAdm@gmail.com</b></p>
                        <p>password: <b>Fortado#1234</b></p>
                        <p><i>Please always reset the password to <b>1234</b>!</i></p>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="ResetAdmin">
                            <button type="submit" class="btn btn-warning">Reset Admin User</button>
                        </form>
                    </div>
                    <div class="card-footer">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="GiveDimentionsPage">
                            <button type="submit" class="btn btn-dark">Back to Customer-page</button>
                        </form>
                    </div>
                </div>

            </div>
    </center>
</body>
</html>
