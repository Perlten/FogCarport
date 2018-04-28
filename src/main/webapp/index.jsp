<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page import="FunctionLayer.entities.Customization"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customize Carport - Enter Dimentions</title>
        <%@include file="bootstrap.jsp" %>
        <% Order order = (Order) session.getAttribute("order");
            Customization cust = null;
            if (order != null) {
                cust = order.getCustomization();
            }
        %>

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
                            <h4>Carport Options</h4>

                            <label>
                                Length<br>
                                <input type="number" class="form-control" name="length" min="0" placeholder="cm">
                            </label>
                            <br>

                            <label>
                                Width
                                <input type="number" class="form-control" name="width" min="0" placeholder="cm" value="">
                            </label>
                            <br>

                            <label>
                                Height
                                <input type="number" class="form-control" name="height" min="0" placeholder="cm">
                            </label>
                            </label>
                            <br>
                            <br>
                            <label><h4>Roof Options</h4>
                            <label class="form-control">
                                <input type="checkbox" name="roof" value="true" <%
                                    if (cust != null) {
                                        if (cust.getRoofangle() > 0) {
                                            out.print("checked");
                                        }
                                    }
                                       %>> Angled Roof
                            </label>
                            
                            <label>
                                Roof Angle
                                <input type="number" class="form-control" name="roofAngle" min="0" max="89" placeholder="°">
                            </label>
                            </label>
                            <br>
                            <br>
                            <label>
                                <h4>Shed Options</h4>
                            <label class="form-control">
                                <input type="checkbox" name="shed" value="true" <%
                                    if (cust != null) {
                                        if (cust.getShed() != null) {
                                            out.print("checked");
                                        }
                                    }
                                       %>> Shed
                            </label>
                            
                            <label>
                                Shed Length
                                <input type="number" class="form-control" name="shedLenght" min="0" placeholder="cm">
                            </label>
                            
                            <br>
                            
                            <label>
                                Shed Width
                                <input type="number" class="form-control" name="shedWidth" min="0" placeholder="cm">
                            </label>
                            </label>

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
