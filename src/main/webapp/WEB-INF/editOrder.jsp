<%-- 
    Document   : editOrder
    Created on : Apr 26, 2018, 6:16:42 PM
    Author     : Perlt
--%>

<%@page import="FunctionLayer.entities.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit order</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <% Order order = (Order) request.getAttribute("order"); %>
        <div class="row">
            <div class="col-lg-4">
                <h1>Edit Order</h1>
                    <div class="panel panel-default">
                        <%
                            int shedLength = 0;
                            int shedWidth = 0;
                            String checked = "";

                            if (order.getCustomization().getShed() != null) {
                                shedLength = order.getCustomization().getShed().getLength();
                                shedWidth = order.getCustomization().getShed().getWidth();
                                checked = "checked";
                            }
                        %>
                        <form action="FrontController" method="post">
                            <div class="form-group">
                                <input type="hidden" name="command" value="EditOrder">
                            </div>	
                            <div class="form-group"> 
                                <label for="full_name_id" class="control-label">Length</label>
                                <input type="number" class="form-control" id="full_name_id" name="length" value="<%= order.getCustomization().getLength()%>">
                            </div>	

                            <div class="form-group"> 
                                <label for="street1_id" class="control-label">Height</label>
                                <input type="number" class="form-control" id="street1_id" name="height" value="<%= order.getCustomization().getHeight()%>">
                            </div>					

                            <div class="form-group"> 
                                <label for="street2_id" class="control-label">Width</label>
                                <input type="number" class="form-control" id="street2_id" name="width" value="<%= order.getCustomization().getWidth()%>">
                            </div>	

                            <div class="form-group"> 
                                <label for="city_id" class="control-label">Roof Angle</label>
                                <input type="number" class="form-control" id="city_id" name="roofAngle" value="<%= order.getCustomization().getRoofangle()%>">
                            </div>									
                            <div class="form-group"> 
                                <label for="zip_id" class="control-label">Shed</label>
                                <input style="width: 45px" type="checkbox" class="form-control" id="zip_id" name="shed" value="true" <%= checked%>>
                            </div>		
                            <div class="form-group"> 
                                <label for="city_id" class="control-label">Shed Length</label>
                                <input type="number" class="form-control" id="city_id" name="shedLength" value="<%= shedLength %>">
                            </div>
                            <div class="form-group">
                                <label for="city_id" class="control-label">Shed Width</label>
                                <input type="number" class="form-control" id="city_id" name="shedWidth" value="<%= shedWidth %>">
                            </div>
                            <div class="form-group">
                               <input type="hidden" name="orderId" value="<%= order.getOrderid()%>" 
                            </div>
                            <div class="form-group"> 
                                <button type="submit" class="btn btn-primary">Edit</button>
                            </div>     
                        </form>							
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
