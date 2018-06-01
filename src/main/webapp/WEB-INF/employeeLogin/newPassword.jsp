<%-- 
    Document   : newPasswordPage
    Created on : May 10, 2018, 12:10:04 PM
    Author     : Perlt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Password</title>
        <%@include file="/WEB-INF/include/includeBootstrap.jsp" %>
    </head>
    <body>

        <div class="loginStyle">
            <div class="container-fluid">
                <center>
                    <div class="card bg-secondary text-white" style="width: 500px">
                        <div class="card-header">
                            <h1>Reset Password</h1>
                        </div>
                        <div class="card-body">
                            <form action="FrontController" method="post">
                                <div class="form-group">
                                    <input type="hidden" name="command" value="NewPassword">
                                    <label class="control-label">New Password</label>
                                    <input type="password" class="form-control" name="password" placeholder="New password">

                                    <label class="control-label">Repeat new Password</label>
                                    <input type="password" class="form-control" name="passwordRepeat" placeholder="Repeat new password">
                                    <br>
                                </div>
                        </div>
                        <div class="card-footer">
                            <button type="submit" class="btn btn-primary">Reset</button>
                            </form>
                        </div>
                    </div>
                </center>
            </div>
        </div>
    </body>
</html>

