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
        <%@include file="../bootstrap.jsp" %>
        <link href="../css/EmployeeLogin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--//TODO: Use EmployeeLogin.css file-->
        <div style="   
             margin: auto;
             width: 25%;
             border: 3px solid black;
             padding: 10px;
             text-align: center;">
            <div class="container-fluid">
                
                <form action="FrontController" method="post">
                    <h1>Reset Password</h1>
                    <div class="form-group">
                        <input type="hidden" name="command" value="NewPassword">
                        <label class="control-label">New Password</label>
                        <input type="password" class="form-control" name="password" placeholder="New password">

                        <label class="control-label">Repeat new Password</label>
                        <input type="password" class="form-control" name="passwordRepeat" placeholder="Repeat new password">
                        <br>
                        <button type="submit" class="btn btn-primary">Reset</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>

