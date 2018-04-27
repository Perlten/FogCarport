<%-- 
    Document   : orders
    Created on : 26-04-2018, 08:48:00
    Author     : adamlass
--%>

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
        <%@include file="../bootstrap.jsp" %>
        <link href="OrdersStyle.css" rel="stylesheet" type="text/css"/>
        <%
            List<Order> orders = (List<Order>) request.getAttribute("orders");
            Order selectedOrder = (Order) request.getAttribute("selectedOrder");
        %>
    </head>
    <body>
        <h1>Customer Orders</h1>

        <div class="row">
            <div class="col-lg-6">
                <table class="table table-xstriped table-hover">
                    <thead>
                        <tr>
                            <th>Order Number</th>
                            <th>Email</th>
                            <th>Date</th>
                            <th>Name</th>
                            <th>Phonenumber</th>
                        </tr>
                    </thead>

                    <% for (Order order : orders) {
                            Customer cust = order.getCustomer();
                    %>
                    <tbody>
                        <tr <% if (order.isConfirmed()) { %>
                            class="success"
                            <%}%>>
                            <td>
                                <form action="FrontController" method="post">
                                    <input type="hidden" name="command" value="ShowOrder">
                                    <input type="hidden" name="orderToShow" value="<%= order.getOrderid()%>">
                                    <input type="submit" class="btn btn-default" value="Order <%= order.getOrderid()%>">
                                </form>

                            </td>
                            <td><%= cust.getEmail()%></td>
                            <% SimpleDateFormat sp = new SimpleDateFormat("dd/MM/YYYY HH:mm");%>
                            <td><%= sp.format(order.getDate().getTime())%></td>
                            <td><%= cust.getLastname() + ", " + cust.getFirstname()%></td>
                            <td><%= cust.getPhonenumber()%></td>
                        </tr>
                    </tbody>
                    <% }%>

                </table>
            </div>
            <% if (selectedOrder != null) {%>
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>Contents of Order</h3>
                    </div>
                    <div>
                        <table class="table table">
                            <thead>
                            <th>Id number</th>
                            <th>Length</th>
                            <th>Height</th>
                            <th>Width</th>
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
                                    <td> <%= selectedOrder.getCustomization().getHeight()%> </td>
                                    <td> <%= selectedOrder.getCustomization().getWidth()%> </td>
                                    <td> <%= selectedOrder.getCustomization().getRoofangle()%> </td>
                                    <td> <%= shedLength%> </td>
                                    <td> <%= shedWidth%> </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <% if (!selectedOrder.isConfirmed()) {%>
                    <div style="float: left; padding: 5px">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="ConfirmOrder">
                            <input type="hidden" name="orderToConfirm" value="<%= selectedOrder.getOrderid()%>">
                            <input type="submit" class="btn btn-success" value="Confirm">
                        </form>
                    </div>
                    <%}%>
                    <div style="float: left; padding: 5px">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="DeleteOrder">
                            <input type="hidden" name="orderToDelete" value="<%= selectedOrder.getOrderid()%>">
                            <input type="submit" class="btn btn-danger" value="Delete">
                        </form>
                    </div>
                    <div style="float: left; padding: 5px">
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="EditOrderPage">
                            <input type="hidden" name="orderToEdit" value="<%= selectedOrder.getOrderid()%>">
                            <input type="submit" class="btn btn-primary" value="Edit">
                        </form>
                    </div>
                </div>
            </div>
            <% }%>
        </div>
    </body>
</html>
