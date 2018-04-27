<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.StyleOption"%>
<%@page import="FunctionLayer.entities.Customer"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit order</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            Order order = (Order) request.getAttribute("order");
            List<StyleOption> claddingList = (List<StyleOption>) request.getAttribute("claddingList");
            List<StyleOption> tileList = (List<StyleOption>) request.getAttribute("tileList");

            int shedLength = 0;
            int shedWidth = 0;
            String checked = "";

            Customer customer = order.getCustomer();

            if (order.getCustomization().getShed() != null) {
                shedLength = order.getCustomization().getShed().getLength();
                shedWidth = order.getCustomization().getShed().getWidth();
                checked = "checked";
            }
        %>
        <div class="row">
            <div class="panel panel-default">
                <form action="FrontController" method="post">
                    <div class="col-lg-3">
                        <h1>Edit Dimensions</h1>
                        <div class="form-group">
                            <input type="hidden" name="command" value="EditOrder">
                        </div>	
                        <div class="form-group"> 
                            <label class="control-label">Length</label>
                            <input type="number" class="form-control" name="length" value="<%= order.getCustomization().getLength()%>">
                        </div>	

                        <div class="form-group"> 
                            <label class="control-label">Height</label>
                            <input type="number" class="form-control" name="height" value="<%= order.getCustomization().getHeight()%>">
                        </div>					

                        <div class="form-group"> 
                            <label class="control-label">Width</label>
                            <input type="number" class="form-control" name="width" value="<%= order.getCustomization().getWidth()%>">
                        </div>	

                        <div class="form-group"> 
                            <label class="control-label">Roof Angle</label>
                            <input type="number" class="form-control" name="roofAngle" value="<%= order.getCustomization().getRoofangle()%>">
                        </div>									
                        <div class="form-group"> 
                            <label class="control-label">Shed</label>
                            <input style="width: 45px" type="checkbox" class="form-control" name="shed" value="true" <%= checked%>>
                        </div>		
                        <div class="form-group"> 
                            <label class="control-label">Shed Length</label>
                            <input type="number" class="form-control" name="shedLength" value="<%= shedLength%>">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Shed Width</label>
                            <input type="number" class="form-control" name="shedWidth" value="<%= shedWidth%>">
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="orderId" value="<%= order.getOrderid()%>">
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <h1>Edit Style</h1>
                        <h2>Cladding</h2>
                        <div class="form-group">
                            <select name="cladding">
                                <%for (StyleOption style : claddingList) {%>
                                <option value="<%= style.getId()%>" > <%= style.getName()%> </option>
                                <%}%>
                            </select>
                        </div>
                        <h2>Tile</h2>
                        <div class="form-group">
                            <select name="tile">
                                <%for (StyleOption style : tileList) {%>
                                <option value="<%= style.getId()%>" > <%= style.getName()%> </option> 
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <h1>Edit User</h1>
                        <div class="form-group"> 
                            <label class="control-label">First Name</label>
                            <input type="text" class="form-control" name="firstName" value="<%= customer.getFirstname()%>">
                        </div>	

                        <div class="form-group"> 
                            <label class="control-label">Last Name</label>
                            <input type="text" class="form-control" name="lastName" value="<%= customer.getLastname()%>">
                        </div>					

                        <div class="form-group"> 
                            <label class="control-label">Email</label>
                            <input type="text" class="form-control" name="email" value="<%= customer.getEmail()%>">
                        </div>	

                        <div class="form-group"> 
                            <label class="control-label">Phone Number</label>
                            <input type="number" class="form-control" name="phoneNumber" value="<%= customer.getPhonenumber()%>">
                        </div>	
                        <div class="form-group"> 
                            <button type="submit" class="btn btn-primary">Edit</button>
                        </div>     
                    </div>
                </form>							
            </div>
        </div>
    </body>
</html>
