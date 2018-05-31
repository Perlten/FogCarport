
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
    <body id="bodyMode">
        <script>
            function applyMode() {
                var darkMode = sessionStorage.getItem("darkMode");
                if (darkMode === "true") {
                    document.getElementById('h1Mode').style = "color: white; display: inline";
                    document.getElementById("bodyMode").style = "background-color: #2E3338";
                    document.getElementById("cardBody1").className = "card bg-dark text-white";
                    document.getElementById("cardBody2").className = "card bg-dark text-white";
                    document.getElementById("cardFooter1").className = "card bg-dark text-white";
                    document.getElementById("cardFooter2").className = "card bg-dark text-white";
                    document.getElementById("slider").checked = true;
                }
            }
        </script>
        <%
            Employee emp = (Employee) request.getSession().getAttribute("employee");
            int numberOfConfirmedOrder = (int) request.getAttribute("numberOfConfirmedOrder");
            List<Order> unConfirmedOrders = (List<Order>) request.getAttribute("10UnconfirmedOrders");
            List<Event> eventList = (List<Event>) request.getAttribute("eventList");

        %>  
        <%@include file="include/includeEmployeeNav.jsp"%>

        <div class="container-fluid">
            <div class="row">
                <div class="container-fluid">
                    <h1 id="h1Mode" style="display: inline">Welcome <%= emp.getFirstname() + " " + emp.getLastname() %></h1>
                    <div style="float: right">
                        <%@include file="include/includeDarkModeSlider.jsp" %>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="card bg-secondary">
                        <div class="card-header bg-dark text-white">
                            <h2>Latest unconfirmed orders</h2>
                        </div>
                        <div class="card-body bg-light text-dark" style="padding: 0px" id="cardBody1">
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
                        <div class="card-footer bg-light text-dark" id="cardFooter1">
                            <a> Total Unconfirmed Orders 
                                <span class="badge badge-pill badge-danger"><%= numberOfConfirmedOrder%></span></a>

                        </div>
                    </div>
                </div>


                <div class="col-lg-6">
                    <div class="card bg-secondary">
                        <div class="card-header bg-secondary text-white">
                            <h2>Your Latest Events</h2>

                        </div>
                        <div class="card-body bg-light text-dark" id="cardBody2" style="padding: 0px">
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
                        <div class="card-footer bg-light text-dark" id="cardFooter2">
                            <a>Logged in as <%= emp.getUsername()%>
                                <span class="badge badge-pill badge-secondary">Level <%= emp.getAuthenticationLevel()%></span>
                            </a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
