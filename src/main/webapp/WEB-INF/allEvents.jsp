<%-- 
    Document   : allEvents
    Created on : 27-05-2018, 20:43:33
    Author     : adamlass
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.Event"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Events</title>
        <%@include file="/WEB-INF/include/includeBootstrap.jsp" %>

        <% List<Event> events = (List<Event>) request.getAttribute("allEvents");
        %>
    </head>
    <body>
        <%@include file="include/includeEmployeeNav.jsp"%>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-8">
                    <h1>All Events</h1>

                </div>
                <div class="col-lg-4">

                    <% if (events != null) { %>
                    <form action="FrontController" method="post" style="float: right">
                        <input type="hidden" name="command" value="AllEvents">
                        <input type="number" class="form-control" min="1" maxlength="7" name="limit" placeholder="Enter a Limit">
                    </form>
                    <%}%>
                </div>
            </div>


            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Date</th>
                        <th>Employee ID</th>
                        <th>Order ID</th>
                        <th>Access Level</th>
                        <th>Title</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                    <%if (events != null) {%>
                    <% for (Event event : events) {%>
                    <tr>
                        <td><%= event.getEventId()%></td>
                        <td><%= event.simpleDate()%></td>
                        <td><%= event.getEmployee()%></td>
                        <td><%= event.getOrderid()%></td>
                        <td><%= event.getAccessLevel()%></td>
                        <td><%= event.getTitle()%></td>
                        <td><%= event.getDescription()%></td>
                    </tr>
                    <%}
                    } else {%>
                    <tr>
                        <th colspan="99">
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="AllEvents">
                                <input type="number" class="form-control" min="1" maxlength="7" name="limit" placeholder="Enter a Limit">
                            </form>
                        </th>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </body>
</html>
