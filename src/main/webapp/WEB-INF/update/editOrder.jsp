<%@page import="FunctionLayer.LogicFacade"%>
<%@page import="FunctionLayer.calculator.Calculator"%>
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
        <%@include file="../include/includeBootstrap.jsp" %>
        <style>
            select{
                color: darkblue;
                width: 125px;
                border-radius: 55px;
            }
        </style>
    </head>
    <body>
        <%@include file="../include/includeEmployeeNav.jsp"%>
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
        <div class="container-fluid">
            <div class="row">
                <form action="FrontController" method="post">
                        <div class="col-lg-3">
                            <h3>Edit Dimensions</h3>
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
                                <input type="number" class="form-control" name="shedWidth" min="0" max="<%= order.getCustomization().getWidth() - 60%>" value="<%= shedWidth%>">
                                <input type="hidden" name="orderId" value="<%= order.getOrderid()%>">
                            </div>
                        </div>

                        <div class="col-lg-3">
                            <h3>Edit Style</h3>
                            <h4>Cladding</h4>
                            <div class="form-group">
                                <select name="cladding" class="form-control">
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
                            <h4>Tile</h4>
                            <div class="form-group">
                                <select name="tile" class="form-control">
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
                            <h3>Edit User</h3>
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

                        </div>
                        <div class="col-lg-3">
                            <h3>Price</h3>
                            <p class="text-warning">Price Of Products: <%
                                Calculator calc = LogicFacade.getCalculator(order);
                                calc.calculate();
                                out.print(calc.totalPrice());
                                %> DKK</p>
                            <label class="control-label">Total Price</label>
                            <input type="number" class="form-control" min="0" name="price" value="<%= order.getPrice()%>" placeholder="DKK">
                        </div>

                    <div class="col-lg-12">
                        <div class="form-group"> 
                            <button type="submit" class="btn btn-primary">Edit Order</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
