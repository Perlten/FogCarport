<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>

<%@page import="FunctionLayer.entities.Shed"%>
<%@page import="FunctionLayer.entities.Customization"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customize Carport - Enter Dimentions</title>
        <%@include file="/WEB-INF/include/includeBootstrap.jsp" %>

        <% Order order = (Order) session.getAttribute("order");

            Customization cust = null;
            Shed shed = null;

            String length = "", width = "", height = "";
            String roofAngle = "";
            String shedLength = "", shedWidth = "";

            if (order != null) {
                cust = order.getCustomization();
                if (cust != null) {
                    length = "" + cust.getLength();
                    width = "" + cust.getWidth();
                    height = "" + cust.getHeight();
                    if (cust.getRoofangle() > 0) {
                        roofAngle = "" + cust.getRoofangle();

                    }
                    shed = cust.getShed();
                    if (shed != null) {
                        shedLength = "" + shed.getLength();
                        shedWidth = "" + shed.getWidth();
                    }
                }

            }
        %>
    </head>
    <body>
        <%@include file="WEB-INF/include/includeCustomerNav.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6">

                    <div class="card">
                        <form name="configure" action="FrontController" method="POST" >

                            <div class="card-header">
                                <h4>Enter Dimentions</h4>
                            </div>
                            <div class="card-body">

                                <input type="hidden" name="command" value="GiveDimentions">
                                <label>
                                    <h4>Carport Options</h4>

                                    <label>
                                        Length<br>
                                        <input type="number" class="form-control" name="length"  min="100" placeholder="cm" value="<%= length%>" required>
                                    </label>
                                    <br>

                                    <label>
                                        Width
                                        <input type="number" class="form-control" name="width" min="100" placeholder="cm" value="<%= width%>" required>
                                    </label>
                                    <br>

                                    <label>
                                        Height
                                        <!--max: 480 (max length of cladding) + 20 (air from floor)-->
                                        <input type="number" class="form-control" name="height" min="100" max="500" placeholder="cm" value="<%= height%>" required> 
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
                                        <input type="number" class="form-control" name="roofAngle" min="0" max="89" placeholder="°" value="<%= roofAngle%>">
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
                                        <input type="number" class="form-control" name="shedLength" min="0" placeholder="cm" value="<%= shedLength%>" >
                                    </label>

                                    <br>

                                    <label>
                                        Shed Width
                                        <input type="number" class="form-control" name="shedWidth" min="0" placeholder="cm" value="<%= shedWidth%>">
                                    </label>
                                </label>

                                <br><br>
                            </div>
                            <div class="card-footer">
                                <input type="submit" name="submit" class="btn btn-primary" value="Next" style="float: right">
                                <input type="submit" name="submit" class="btn btn-secondary" value="Update" style="float: right; margin: 10px; margin-top: 0px">
                            </div>
                        </form>
                    </div>
                </div>
                <% if (order != null) {%>
                <div class="col-lg-6">
                    <%@include file="WEB-INF/include/includeOverview.jsp" %>
                </div>       
                <%}%>
            </div>
        </div>
    </div>
</body>
</html>
