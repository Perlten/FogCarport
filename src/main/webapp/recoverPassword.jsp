<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../bootstrap.jsp" %>
        <link href="css/EmployeeLogin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="loginStyle">
            <form action="FrontController" method="post">
                <h1>Login</h1>
                <div class="form-group">
                    <input type="hidden" name="command" value="SendNewPassword">
                    <label class="control-label">Email</label>
                    <input type="text" class="form-control" name="email" placeholder="email">
                    <br>
                    <button type="submit" class="btn btn-primary">Recover password</button>
                    <h3>An email will be sent with your new password</h3>
                </div>
            </form>
        </div>
    </body>
</html>
