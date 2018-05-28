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
        <%@include file="css/employeeLogin.jsp" %>

    </head>
    <body>
        <div>
            <%@include file="WEB-INF/include/includeErrorBanner.jsp" %>
        </div>
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
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="ForgotPassword">
                    <button type="submit" class="btn btn-primary">Forgot password</button>
                </form>
            </div>
            <br>
            <center>
                <div>
                    <div class="card" style="width: 400px">
                        <div class="card-body">
                            <h4>Exam access</h4>
                            <p>login: <b>admin</b></p>
                            <p>password: <b>1234</b></p>
                            <br>
                            <h5>Gmail for password reset:</h5>
                            <p>login: <b>FogCarportAdm@gmail.com</b></p>
                            <p>password: <b>Fortado#1234</b></p>
                            <p><i>Please always reset the password to <b>1234</b>!</i></p>
                            <br>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="GiveDimentionsPage">
                                <button type="submit" class="btn btn-light">Back to Customer-page</button>
                            </form>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="ResetAdmin">
                                <button type="submit" class="btn btn-warning">Reset Admin User</button>
                            </form>
                        </div>
                    </div>

                </div>
            </center>
        </div>
    </body>
</html>
