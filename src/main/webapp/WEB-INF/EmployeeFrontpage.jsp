
<%@page import="FunctionLayer.entities.Event"%>
<%@page import="FunctionLayer.entities.Customer"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="FunctionLayer.entities.Employee"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overview</title>
        <%@include file="../bootstrap.jsp" %>
    </head>
    <body>
        <%
            Employee emp = (Employee) request.getSession().getAttribute("employee");
            int numberOfConfirmedOrder = (int) request.getAttribute("numberOfConfirmedOrder");
            List<Order> unConfirmedOrders = (List<Order>) request.getAttribute("10UnconfirmedOrders");
            List<Event> eventList = (List<Event>) request.getAttribute("eventList");

        %>  
        <%@include file="../employeesNavBar.jsp" %>
        <div class="container-fluid">
            <h1>Welcome <%= emp.getFirstname() + " " + emp.getLastname()%></h1>

            <div class="row">

                <div class="col-lg-6">

                    <h2>Latest unconfirmed orders</h2>
                    <table class="table table-xstriped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>Order</th>
                                <th>Email</th>
                                <th>Date</th>
                                <th>Name</th>
                                <th>Phonenumber</th>
                            </tr>
                        </thead>

                        <% for (Order order : unConfirmedOrders) {
                                Customer cust = order.getCustomer();
                        %>
                        <tbody>
                        <td><%= order.getOrderid()%></td>
                        <td><%= cust.getEmail()%></td>
                        <td><%= order.simpleDate()%></td>
                        <td><%= cust.getLastname() + ", " + cust.getFirstname()%></td>
                        <td><%= cust.getPhonenumber()%></td>
                        </tbody>
                        <% }%>
                    </table>
                    <p> Total unconfirmed orders <%= numberOfConfirmedOrder%></p>
                </div>


                <div class="col-lg-6">
                    <h2>Your Latest Event</h2>
                    <table class="table table-xstriped table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>name</th>
                                <th>Description</th>
                                <th>Date</th>
                            </tr>
                        </thead>

                        <% for (Event event : eventList) {
                        %>
                        <tbody>
                        <td><%= event.getEventName()%></td>
                        <td data-toggle="tooltip" data-placement="bottom" title="<%= event.getDescription() %>"><%= event.getShortDescription()%></td>
                        <td><%= event.simpleDate()%></td>
                        </tbody>
                        <% }%>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
