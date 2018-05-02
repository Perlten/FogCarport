<%-- 
    Document   : credentialsPage
    Created on : May 1, 2018, 10:08:25 PM
    Author     : Perlt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Credentials page</title>
        <%@include file="../bootstrap.jsp" %>
    <img src="" alt=""/>
</head>
<body>
    <div class="row">
        <div class="panel panel-default">
            <div class="col-lg-4">
                <div style="padding: 20px">
                     <form action="FrontController" method="post">
                        <h1>Enter Credentials</h1>
                        <div class="form-group">
                            <input type="hidden" name="command" value="GiveCredentials">
                            <label class="control-label">First Name</label>
                            <input type="text" class="form-control" name="firstName" placeholder="Enter first name"/>

                            <label class="control-label">Last Name</label>
                            <input type="text" class="form-control" name="lastName" placeholder="Enter last name"/>

                            <label class="control-label">Email</label>
                            <input type="text" class="form-control" name="email" placeholder="Enter email"/>

                            <label class="control-label">Phone Number</label>
                            <input type="number" class="form-control" name="phoneNumber" placeholder="Enter phone number"/>
                            <br>
                            <button type="submit" class="btn btn-primary">Next</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-lg-3">
                <%@include file="../SVGDraw.jsp" %>
            </div>
        </div>
    </div>
</body>
</html>
