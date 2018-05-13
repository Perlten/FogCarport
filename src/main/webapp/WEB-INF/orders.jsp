<%@page import="java.text.SimpleDateFormat"%>
<%@page import="FunctionLayer.entities.Customer"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Orders</title>
        <%--<%@include file="../bootstrap.jsp" %>--%>
        <link href="OrdersStyle.css" rel="stylesheet" type="text/css"/>
        <%@include file="../bootstrap.jsp" %>
    </head>
    <body>
        <%
            Order selectedOrder = (Order) request.getAttribute("order");
            int loads = 0;
            if (request.getParameter("loads") != null) {
                loads = Integer.parseInt(request.getParameter("loads"));
            }

        %>
        <script>
            window.onload = function () {
                showCustomer(false);
            };

            var num = <%= loads%>;
            function showCustomer(add) {
                if (add || num == 0) {
                    num += 10;
                }
                var xhttp;
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("orders").innerHTML = this.responseText;
                    }
                };
                xhttp.open("get", "showordersJS.jsp?num=" + num, true);
                xhttp.send();
            }
        </script>

        <%@include file="../employeesNavBar.jsp"%>
        <div class="container-fluid">
            <h1>Customer Orders</h1>
            <div class="row">
                <div class="col-lg-6">
                    <div id="orders"></div>
                    <input type="submit" onclick="showCustomer(true)" class="btn btn-primary" value="Load">
                </div>
                <% if (selectedOrder != null) {

                        String card = "card bg-default";
                        if (selectedOrder.isConfirmed()) {
                            card = "bg-success text-light";
                        }
                %>
                <div class="col-lg-6">
                    <div class="card-header <%= card%>">
                        <div>
                            <h3>Contents of Order </h3>
                        </div>
                    </div>
                    <div class="card">
                        <table class="table table">
                            <thead>
                            <th>Id number</th>
                            <th>Length</th>
                            <th>Width</th>
                            <th>Height</th>
                            <th>Roof angle</th>
                            <th>Shed Length</th>
                            <th>Shed width</th>
                            </thead>
                            <%
                                int shedLength = 0;
                                int shedWidth = 0;

                                if (selectedOrder.getCustomization().getShed() != null) {
                                    shedLength = selectedOrder.getCustomization().getShed().getLength();
                                    shedWidth = selectedOrder.getCustomization().getShed().getWidth();
                                }
                            %>
                            <tbody>
                                <tr>
                                    <td> <%= selectedOrder.getOrderid()%> </td>
                                    <td> <%= selectedOrder.getCustomization().getLength()%> </td>
                                    <td> <%= selectedOrder.getCustomization().getWidth()%> </td>
                                    <td> <%= selectedOrder.getCustomization().getHeight()%> </td>
                                    <td> <%= selectedOrder.getCustomization().getRoofangle()%> </td>
                                    <td> <%= shedLength%> </td>
                                    <td> <%= shedWidth%> </td>
                                </tr>
                            </tbody>
                        </table>
                        <div style="height: 300px; width: 50%; margin: auto; ">
                            <%@include file="../SVGDraw.jsp" %>
                        </div>
                    </div>
                    <% if (!selectedOrder.isConfirmed()) {%>
                    <div style="float: left; padding: 5px">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="ConfirmOrder">
                            <input type="hidden" name="orderId" value="<%= selectedOrder.getOrderid()%>">
                            <input type="hidden" name="loads" value="<%= loads%>">
                            <input type="submit" class="btn btn-success" value="Confirm">
                        </form>
                    </div>
                    <div style="float: left; padding: 5px">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="EditOrderPage">
                            <input type="hidden" name="orderToEdit" value="<%= selectedOrder.getOrderid()%>">
                            <input type="submit" class="btn btn-primary" value="Edit">
                        </form>
                    </div>
                    <%} else {%>
                    <div style="float: left; padding: 5px">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="Unconfirm">
                            <input type="hidden" name="orderId" value="<%= selectedOrder.getOrderid()%>">
                            <input type="hidden" name="loads" value="<%= loads%>">
                            <input type="submit" class="btn btn-primary" value="Unconfirm">
                        </form>
                    </div>
                    <%}%>
                    <div style="float: left; padding: 5px">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="DeleteOrder">
                            <input type="hidden" name="orderToDelete" value="<%= selectedOrder.getOrderid()%>">
                            <input type="hidden" name="loads" value="<%= loads%>">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </form>
                    </div>
                    <div style="float: left; padding: 5px">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="PickingList">
                            <input type="hidden" name="orderPicking" value="<%= selectedOrder.getOrderid()%>">
                            <input type="submit" class="btn btn-light" value="Picking List">
                        </form>
                    </div>

                </div>
                <% }%>
            </div>

        </div>

    </body>
</html>
