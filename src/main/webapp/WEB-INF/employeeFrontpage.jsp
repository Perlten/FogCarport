
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
        <%@include file="/WEB-INF/include/includeBootstrap.jsp" %>
    </head>
    <body>
        <%
            Employee emp = (Employee) request.getSession().getAttribute("employee");
            int numberOfConfirmedOrder = (int) request.getAttribute("numberOfConfirmedOrder");
            List<Order> unConfirmedOrders = (List<Order>) request.getAttribute("10UnconfirmedOrders");
            List<Event> eventList = (List<Event>) request.getAttribute("eventList");

        %>  
        <%@include file="include/includeEmployeeNav.jsp"%>
        <div class="container-fluid">
             <h1>Welcome <%= emp.getFirstname() + " " + emp.getLastname()%></h1>
        </div>
        <hr>
        <div class="container-fluid">
           
            <div class="row">

                <div class="col-lg-6">
                    <div class="card bg-secondary">
                        <div class="card-header bg-dark text-white">
                            <h2>Latest unconfirmed orders</h2>
                        </div>
                        <div class="card-body bg-light text-dark" style="padding: 0px">
                            <table class="table table-xstriped" style="marin: px">
                                <thead>
                                    <tr>
                                        <th style="padding-left: 30px">Order</th>
                                        <th>Email</th>
                                        <th>Date</th>
                                        <th>Name</th>
                                        <th>Phonenumber</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <% for (Order order : unConfirmedOrders) {
                                            Customer cust = order.getCustomer();
                                    %>
                                    <tr>
                                        <td style="padding-left: 30px"><%= order.getOrderid()%></td>
                                        <td><%= cust.getEmail()%></td>
                                        <td><%= order.simpleDate()%></td>
                                        <td><%= cust.getLastname() + ", " + cust.getFirstname()%></td>
                                        <td><%= cust.getPhonenumber()%></td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>
                        </div>
                        <div class="card-footer bg-light text-dark">
                            <a class="text-muted"> Total Unconfirmed Orders 
                                <span class="badge badge-pill badge-danger"><%= numberOfConfirmedOrder%></span></a>

                        </div>
                    </div>
                </div>


                <div class="col-lg-6">
                    <div class="card bg-secondary">
                        <div class="card-header bg-secondary text-white">
                            <h2>Your Latest Events</h2>

                        </div>
                        <div class="card-body bg-light text-dark" style="padding: 0px">
                            <table class="table table-xstriped">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Date</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <% for (Event event : eventList) {
                                    %>
                                    <tr>
                                        <td><%= event.getEventName()%></td>
                                        <td data-toggle="tooltip" data-placement="bottom" title="<%= event.getDescription()%>"><%= event.getShortDescription()%></td>
                                        <td><%= event.simpleDate()%></td>
                                    </tr>
                                    <% }%>
                                </tbody>
                            </table>

                        </div>
                        <div class="card-footer bg-light text-dark">
                            <a class="text-muted">Logged in as <%= emp.getUsername() %>
                            <span class="badge badge-pill badge-secondary">Level <%= emp.getAuthenticationLevel() %></span>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
