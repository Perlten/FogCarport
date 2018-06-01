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

    <body id="bodyMode">

        <script>
            function applyMode() {
                var darkMode = sessionStorage.getItem("darkMode");
                if (darkMode === "true") {
                    console.log(darkMode);
                    document.getElementById('h1Mode').style = "color: white; display: inline";
                    document.getElementById("bodyMode").style = "background-color: #2E3338";
                    document.getElementById("cardMode").className = "card bg-dark text-white";
                    document.getElementById("slider").checked = true;
                    document.getElementById("hrMode").style = "background-color: white";

                }
            }
            function toggleDarkMode() {
                var darkMode = sessionStorage.getItem("darkMode");
                console.log(darkMode);

                if (darkMode === "true") {
                    sessionStorage.setItem("darkMode", false);
                } else {
                    sessionStorage.setItem("darkMode", true);
                }
                location.reload();
            }
        </script>


        <%@include file="include/includeEmployeeNav.jsp"%>

        <div class="container-fluid">
            <div class="row">
                <div class="container-fluid">
                    <h1 id="h1Mode" style="display: inline">All Events</h1>
                    <div style="float: right">
                        <%@include file="include/includeDarkModeSlider.jsp" %>
                    </div>
                </div>
            </div>

            <div class="col-lg-3">
                <% if (events != null) { %>
                <form action="FrontController" method="post" >
                    <input type="hidden" name="command" value="AllEvents">
                    <input type="number" class="form-control" style="float: bottom" min="1" maxlength="7" name="limit" placeholder="Enter a Limit">
                </form>
                <%}%>
            </div>
            <div class="card" id="cardMode">
                <table class="table table-hover" style="margin: 0px">
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
                            <td data-toggle="tooltip" data-placement="bottom" 
                                title="<%= event.getDescription()%>"><%= event.getShortDescription()%></td>
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
            <br>
        </div>
    </body>
</html>
