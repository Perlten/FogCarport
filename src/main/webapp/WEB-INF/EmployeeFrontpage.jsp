
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
        %>
        <%@include file="../employeesNavBar.jsp" %>
        <div class="row">
            <div class="col-lg-3">
                <h1>Welcome <%= emp.getFirstname() + " " + emp.getLastname()%></h1>
                <br>
                <h2>Latest unconfirmed orders</h2>
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
            <div class="col-lg-3">
                
            </div>
        </div>
    </body>
</html>
