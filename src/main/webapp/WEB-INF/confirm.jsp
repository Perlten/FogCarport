<%-- 
    Document   : confirm
    Created on : 03-05-2018, 15:34:39
    Author     : adamlass
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.Event"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Request</title>
        <%@include file="../bootstrap.jsp" %>
        <% Order orderConfirm = (Order) session.getAttribute("order");
            if (orderConfirm == null) {
                orderConfirm = (Order) session.getAttribute("confirmedOrder");
            }

            List<Event> orderEvents = (List<Event>) session.getAttribute("orderEvents");
        %>

    </head>
    <body>
        <%@include file="../customerNavBar.jsp" %>
        <div class="container-fluid">
            <h1><%= orderConfirm.getCustomer().getFirstname()%>'s Carport Request</h1>
            <div class="row">
                <% if (!orderConfirm.isOrdered()) { %>
                <div class="col-lg-6">

                    <div class="card">
                        <div class="card-header">
                            <center><h4>Do you want to submit your request?</h4></center>
                            <div>
                                <form action="FrontController" method="post" style="float: right">
                                    <input type="hidden" name="command" value="SubmitOrder">
                                    <input type="submit"  class="btn btn-success" value="Submit Request">
                                </form>
                            </div>
                            <div>
                                <form action="FrontController" method="post">
                                    <input type="hidden" name="command" value="GiveCredentialsPage">
                                    <input type="submit"  class="btn btn-default" value="Back">
                                </form>

                            </div>

                        </div>
                    </div>

                </div>
                <%} else if(orderEvents != null) {%>
                <div class="col-lg-6">
                    <% for (Event event : orderEvents) {%>

                    <div class="card <%= event.getStatusColor()%>">
                        <div class="card-body" style="padding-bottom: 7px">
                            <p style="float: right; font-size: 13px"><%= event.simpleDate()%></p>
                            <h4><%= event.getTitle()%></h4>
                            <p><%= event.getDescription()%></p>
                        </div>
                    </div>
                    <br>
                    <%}%>

                </div>

                <%} else {%>
                <div class="col-lg-6"></div>
                <%}%>

                <div class="col-lg-6">
                    <%@include file="overview.jsp" %>
                </div>

            </div>
        </div>
    </body>
</html>
