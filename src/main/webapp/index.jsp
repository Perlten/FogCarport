<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customize Carport - Enter Dimentions</title>
        <%@include file="bootstrap.jsp" %>

    </head>
    <body>
        <h1>Customize Carport</h1>

        <div>
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">

                        <h4>Enter Dimentions</h4>
                    </div>
                    <div class="panel-body">

                        <form name="configure" action="FrontController" method="POST" >


                            <input type="hidden" name="command" value="configure">

                            <label>
                                Length<br>
                                <input type="number" class="form-control" name="length" placeholder="min. X">
                            </label>
                            <br>

                            <label>
                                Width
                                <input type="number" class="form-control" name="width" placeholder="min. X">
                            </label>
                            <br>

                            <label>
                                Height
                                <input type="number" class="form-control" name="height" placeholder="min. X">
                            </label>
                            <br>

                            <br><br>
                            <input type="submit" class="btn btn-primary" value="Style">
                        </form>
                    </div>
                    <div class="panel-footer">
                        <a>Now go to styling</a>
                    </div>


                </div>
            </div>
        </div>







        <form action="FrontController" method="post">
            <input type="hidden" name="command" value="GetOrders">
            <input type="submit" value="GetOrders">
        </form>

        <% String error = (String) request.getAttribute("error");
            if (error != null) {%>
        <H2>Error!!</h2>
        <p><%= error%>
            <% }
            %>
    </body>
</html>
