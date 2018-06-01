
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
        <script>
            function setMaxShedWidth() {
                var width = document.getElementById("width").value;
                var newWidth = width - 60;
                if(newWidth < 0){
                    newWidth = 0;
                }
                console.log(newWidth);
                
                document.getElementById("sWidth").max = newWidth;
                setRequiredShed();
            }
            
             function setMaxShedLength() {
                var length = document.getElementById("length").value;
                var newLength = length - 30;
                if(newLength < 0){
                    newLength = 0;
                }
                console.log(newLength);
                
                document.getElementById("sLength").max = newLength;
                setRequiredShed();
            }


            function setRequiredShed() {
                var sToggle = 'false';

                if (document.getElementById("shed").checked) {
                    sToggle = 'true';
                }

                console.log(sToggle);
                var isTrue = (sToggle === 'true');
                document.getElementById("sLength").required = isTrue;
                document.getElementById("sWidth").required = isTrue;
            }

            function setRequiredRA() {
                var sToggle = 'false';

                if (document.getElementById("roof").checked) {
                    sToggle = 'true';
                }

                console.log(sToggle);
                var isTrue = (sToggle === 'true');
                document.getElementById("RA").required = isTrue;
            }
        </script>
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
                                        <input id="length" type="number" class="form-control" name="length"  min="100" placeholder="cm" value="<%= length%>" required>
                                    </label>
                                    <br>

                                    <label>
                                        Width
                                        <input type="number" id="width" onclick="setMaxShedWidth()" class="form-control" name="width" min="100" placeholder="cm" value="<%= width%>" required>
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
                                        <input type="checkbox" onclick="setRequiredRA()" id="roof" name="roof" value="true" <%
                                            if (cust != null) {
                                                if (cust.getRoofangle() > 0) {
                                                    out.print("checked");
                                                }
                                            }
                                               %>> Angled Roof
                                    </label>

                                    <label>
                                        Roof Angle
                                        <input type="number" id="RA" onclick="setRequiredRA()" class="form-control" name="roofAngle" min="0" max="89" placeholder="°" value="<%= roofAngle%>">
                                    </label>
                                </label>
                                <br>
                                <br>

                                <label>
                                    <h4>Shed Options</h4>
                                    <label class="form-control">
                                        <input type="checkbox" onclick="setRequiredShed()" id="shed" name="shed" value="true" <%
                                            if (cust != null) {
                                                if (cust.getShed() != null) {
                                                    out.print("checked");
                                                }
                                            }
                                               %>> Shed
                                    </label>

                                    <label>
                                        Shed Length
                                        <input type="number" onclick="setMaxShedLength()" id="sLength" class="form-control" name="shedLength" min="0" placeholder="cm" onclick="setMinMax(length)" value="<%= shedLength%>" >
                                    </label>

                                    <br>

                                    <label>
                                        Shed Width
                                        <input type="number" onclick="setMaxShedWidth()" id="sWidth" class="form-control" name="shedWidth" min="0" placeholder="cm" onclick="setMinMax(width)" value="<%= shedWidth%>">
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
    </body>
</html>
