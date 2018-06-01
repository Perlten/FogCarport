<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../include/includeBootstrap.jsp" %>

    </head>
    <body>
    <center>
        <div class="card bg-secondary text-white" style="width: 500px">
            <div class="card-header">
                <h1>Forgot Password</h1>
            </div>
            <div class="card-body">
                <form action="FrontController" method="post">
                    <div class="form-group">
                        <input type="hidden" name="command" value="SendNewPassword">
                        <label class="control-label">Email</label>
                        <input type="text" class="form-control" name="email" placeholder="email" required>

                    </div>
            </div>
            <div class="card-footer">
                <button type="submit" class="btn btn-primary">Recover password</button>
                </form>
                <br>
                <a>An email will be sent with your new password</a>
            </div>

        </div>
    </center>
</body>
</html>
