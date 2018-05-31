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
        <style>
            /* The switch - the box around the slider */
            .switch {
                position: relative;
                display: inline-block;
                width: 60px;
                height: 34px;
            }

            /* Hide default HTML checkbox */
            .switch input {display:none;}

            /* The slider */
            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 26px;
                width: 26px;
                left: 4px;
                bottom: 4px;
                background-color: white;
                -webkit-transition: .4s;
                transition: .4s;
            }

            input:checked + .slider {
                background-color: #2196F3;
            }

            input:focus + .slider {
                box-shadow: 0 0 1px #2196F3;
            }

            input:checked + .slider:before {
                -webkit-transform: translateX(26px);
                -ms-transform: translateX(26px);
                transform: translateX(26px);
            }

            /* Rounded sliders */
            .slider.round {
                border-radius: 34px;
            }

            .slider.round:before {
                border-radius: 50%;
            }
        </style>
    </head>

    <body id="bodyMode">

        <script>
            function applyMode() {
                var darkMode = sessionStorage.getItem("darkMode");
                if (darkMode === "true") {
                    console.log(darkMode);
                    document.getElementById('h1Mode').style = "color: white";
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
                <div class="col-lg-8">
                    <h1 id='h1Mode'>All Events</h1>
                </div>

                <div class="col-lg-3">
                    <% if (events != null) { %>
                    <form action="FrontController" method="post" >
                        <input type="hidden" name="command" value="AllEvents">
                        <input type="number" class="form-control" style="float: bottom" min="1" maxlength="7" name="limit" placeholder="Enter a Limit">
                    </form>
                    <%}%>
                </div>
                <div class="col-lg-1">
                    <center>
                        <div class="card bg-dark text-white rounded-right" style="float: right; padding: 2px">
                            <a>DarkMode</a>
                            <label class="switch" style="float: right">
                                <input type="checkbox" onclick="toggleDarkMode()" id="slider">
                                <span class="slider round"></span>
                            </label>
                        </div>
                    </center>
                </div>
            </div>
        </div>
        <hr id="hrMode">
        <div class="container-fluid">
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
