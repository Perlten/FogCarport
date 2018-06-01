<%@page import="FunctionLayer.Calculator"%>
<%@page import="FunctionLayer.LogicFacade"%>
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
    <body id="bodyMode">
        <script>
            function applyMode() {
                var darkMode = sessionStorage.getItem("darkMode");
                if (darkMode === "true") {
                    document.getElementById('h1Mode').style = "color: white; display: inline";
                    document.getElementById("bodyMode").style = "background-color: #2E3338";
                    document.getElementById("h3Mode1").style = "color: white";
                    document.getElementById("h3Mode2").style = "color: white";
                    document.getElementById("h3Mode3").style = "color: white";
                    document.getElementById("h3Mode4").style = "color: white";
                    document.getElementById("h4Mode1").style = "color: white";
                    document.getElementById("h4Mode2").style = "color: white";
                    document.getElementById("form1").style = "color: white";
                    document.getElementById("form2").style = "color: white";
                    document.getElementById("form3").style = "color: white";
                    document.getElementById("form4").style = "color: white";
                }
            }
        </script>
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
            <h1 id="h1Mode">Edit Order <span class="badge badge-danger"><%= order.getOrderid()%></span></h1>
        </div>
        <hr>
        <div class="container-fluid">
            <form action="FrontController" method="post">
                <div class="row">
                    <div class="col-lg-3">
                        <h3 id="h3Mode1">Edit Dimensions</h3>
                        <div class="form-group" id="form1">
                            <input type="hidden" name="command" value="EditOrder">
                            <label class="control-label">Length</label>
                            <input type="number" class="form-control" name="length" min="100" value="<%= order.getCustomization().getLength()%>" required>

                            <label class="control-label">Width</label>
                            <input type="number" class="form-control" name="width" min="100" value="<%= order.getCustomization().getWidth()%>" required>

                            <label class="control-label">Height</label>
                            <input type="number" class="form-control" name="height" min="100" value="<%= order.getCustomization().getHeight()%>" required>

                            <label class="control-label">Roof Angle</label>
                            <input type="number" class="form-control" name="roofAngle" min="0" value="<%= order.getCustomization().getRoofangle()%>" required>
                            <label class="control-label">Shed</label>
                            <input style="width: 30px; height: 30px" type="checkbox" class="form-control" name="shed" value="true" <%= checked%>>
                            <label class="control-label">Shed Length</label>
                            <input type="number" class="form-control" name="shedLength" min="0" max="<%= order.getCustomization().getLength()%>" value="<%= shedLength%>" required>
                            <label class="control-label">Shed Width</label>
                            <input type="number" class="form-control" name="shedWidth" min="0" max="<%= order.getCustomization().getWidth() - 60%>" value="<%= shedWidth%>" required>
                            <input type="hidden" name="orderId" value="<%= order.getOrderid()%>">
                        </div>
                    </div>

                    <div class="col-lg-3">
                        <h3 id="h3Mode2">Edit Style</h3>
                        <h4 id="h4Mode1">Cladding</h4>
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
                        <h4 id="h4Mode2">Tile</h4>
                        <div class="form-group" id="form2">
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
                        <h3 id="h3Mode3">Edit User</h3>
                        <div class="form-group" id="form3"> 
                            <label class="control-label">First Name</label>
                            <input type="text" class="form-control" name="firstName" value="<%= customer.getFirstname()%>" required>

                            <label class="control-label">Last Name</label>
                            <input type="text" class="form-control" name="lastName" value="<%= customer.getLastname()%>" required>

                            <label class="control-label">Email</label>
                            <input type="email" class="form-control" name="email" value="<%= customer.getEmail()%>" required>

                            <label class="control-label">Phone Number</label>
                            <input type="number" class="form-control" name="phoneNumber" value="<%= customer.getPhonenumber()%>" required>
                        </div>	
                    </div>
                    <div class="col-lg-3">
                        <h3 id="h3Mode4">Price</h3>
                        <p class="text-warning">Price Of Products: <%
                            Calculator calc = LogicFacade.getCalculator(order);
                            calc.calculate();
                            out.print(calc.totalPrice());
                            %> DKK</p>
                        <label id="form4" class="control-label">Total Price</label>
                        <input type="number" class="form-control" min="0" name="price" value="<%= order.getPrice()%>" placeholder="DKK"/ required>
                    </div>

                    <div class="col-lg-12">
                        <div class="form-group"> 
                            <button type="submit" class="btn btn-primary">Edit Order</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
