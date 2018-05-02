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
        <style>
            select{
                color: darkblue;
                width: 125px;
                border-radius: 55px;
            }
        </style>
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
                            <label class="control-label">Length</label>
                            <input type="number" class="form-control" name="length" min="100" value="<%= order.getCustomization().getLength()%>">

                            <label class="control-label">Width</label>
                            <input type="number" class="form-control" name="width" min="100" value="<%= order.getCustomization().getWidth()%>">

                            <label class="control-label">Height</label>
                            <input type="number" class="form-control" name="height" min="100" value="<%= order.getCustomization().getHeight()%>">

                            <label class="control-label">Roof Angle</label>
                            <input type="number" class="form-control" name="roofAngle" min="0" value="<%= order.getCustomization().getRoofangle()%>">
                            <label class="control-label">Shed</label>
                            <input style="width: 45px" type="checkbox" class="form-control" name="shed" value="true" <%= checked%>>
                            <label class="control-label">Shed Length</label>
                            <input type="number" class="form-control" name="shedLength" min="0" max="<%= order.getCustomization().getLength()%>" value="<%= shedLength%>">
                            <label class="control-label">Shed Width</label>
                            <input type="number" class="form-control" name="shedWidth" min="0" max="<%= order.getCustomization().getWidth()- 60 %>" value="<%= shedWidth%>">
                            <input type="hidden" name="orderId" value="<%= order.getOrderid()%>">
                        </div>
                    </div>

                    <div class="col-lg-2">
                        <h1>Edit Style</h1>
                        <h2>Cladding</h2>
                        <div class="form-group">
                            <select name="cladding">
                                <%if (order.getCustomization().getCladding() == null) {%>
                                <option value="" selected/>
                                <%}%>
                                <%
                                    String selected = "";
                                    for (StyleOption style : claddingList) {
                                        if (order.getCustomization().getCladding() != null && style.getId() == order.getCustomization().getCladding().getId()) {
                                            selected = "selected";
                                        }
                                %>
                                <option value="<%= style.getId()%>" <%= selected%> > <%= style.getName()%> </option>
                                <%
                                        selected = "";
                                    }
                                %>
                            </select>
                        </div>
                        <h2>Tile</h2>
                        <div class="form-group">
                            <select name="tile">
                                <%if (order.getCustomization().getTile() == null) {%>
                                <option value="" selected/>
                                <%}%>
                                <%
                                    for (StyleOption style : tileList) {
                                        if (order.getCustomization().getTile() != null && style.getId() == order.getCustomization().getTile().getId()) {
                                            selected = "selected";
                                        }
                                %>
                                <option value="<%= style.getId()%>" <%= selected%> > <%= style.getName()%> </option> 
                                <%
                                        selected = "";
                                    }
                                %>
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
