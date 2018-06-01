<%-- 
    Document   : updateStaffPage
    Created on : May 9, 2018, 6:27:43 PM
    Author     : Perlt
--%>

<%@page import="FunctionLayer.entities.Event"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Staff</title>
        <%@include file="../include/includeBootstrap.jsp" %>
    </head>
    <body id="bodyMode">
        <%
            List<Employee> empList = (List<Employee>) request.getAttribute("empList");
            Employee selectedEmployee = (Employee) request.getAttribute("selectedEmployee");
            String newEmployee = request.getParameter("newEmployee");
            List<Event> eventList = (List<Event>) request.getAttribute("eventList");
        %>
        <script>
            function applyMode() {
                var darkMode = sessionStorage.getItem("darkMode");
                if (darkMode === "true") {
                    document.getElementById('h1Mode').style = "color: white; display: inline";
                    document.getElementById("bodyMode").style = "background-color: #2E3338";
                    document.getElementById("cardMode").className = "card bg-dark text-white";
                    document.getElementById("slider").checked = true;
                }
            }
            <%if (selectedEmployee != null) {%>
            function fireEmployee() {
                var message = prompt("Are you sure you whant to fire <%= selectedEmployee.getFirstname() + " " + selectedEmployee.getLastname()%>. If you are, please type in \"fire\" and press enter");
                if (message === "fire") {
                    window.location.replace("FrontController?command=FireEmployee&employeeId=<%=selectedEmployee.getEmployeeId()%>");
                }
            }
            <%}%>
        </script>

        <%@include file="../include/includeEmployeeNav.jsp"%>


        <div class="container-fluid">
            <div class="row">
                <div class="container-fluid">
                    <h1 id="h1Mode" style="display: inline">Update Staff</h1>
                    <div style="float: right">
                        <%@include file="../include/includeDarkModeSlider.jsp" %>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-6">
                    <div class="card" id="cardMode">
                        <div class="card-body" style="padding: 0px">
                            <table class="table table-xstriped table-hover" style="table-layout: fixed; margin: 0px">
                                <thead>
                                    <tr>
                                        <th>Full Name</th>
                                        <th>Username</th>
                                        <th style="width: 10%">Access</th>
                                        <th>Email</th>
                                        <th>Hire Date</th>
                                        <th style="width: 13%"></th>
                                    </tr>
                                </thead>

                                <% for (Employee emp : empList) {
                                %>
                                <tbody>
                                    <tr <%if (!emp.isEmployed()) { %>
                                        class="btn-danger"
                                        <%}%>>
                                        <td><%= emp.getLastname() + ", " + emp.getFirstname()%></td>
                                        <td><%= emp.getUsername()%></td>
                                        <td><%= emp.getAuthenticationLevel()%></td>
                                        <td data-toggle="tooltip" data-placement="bottom" title="<%= emp.getEmail()%>"><%= emp.getShortEmail()%></td>

                                        <td><%= emp.simpleDate()%></td>
                                        <td>
                                            <form action="FrontController" method="post">
                                                <input type="hidden" name="command" value="EditEmployee">
                                                <input type="hidden" name="employeeId" value="<%= emp.getEmployeeId()%>">
                                                <input type="submit" class="btn btn-primary" value="Edit">
                                            </form>
                                        </td>
                                    </tr>
                                </tbody>
                                <% }%>
                            </table>
                        </div>
                        <div class="card-footer" style="padding: 0px">
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="UpdateStaff">
                                <input type="hidden" name="newEmployee" value="true">
                                <input type="submit" class="btn btn-success" style="width: 100%" value="Create New Employee">
                            </form>
                        </div>
                    </div>
                    <br>


                </div>
                <%if (newEmployee != null) {%>
                <div class="col-lg-6">
                    <div class="card bg-white text-secondary">
                        <div class="card-header">
                            <h1>New Employee</h1>

                        </div>
                        <div class="card-body">
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="CreateNewEmployee">

                                <label class="control-label">First name</label>
                                <input type="text" class="form-control" name="firstName">

                                <label class="control-label">Last name</label>
                                <input type="text" class="form-control" name="lastName">

                                <label class="control-label">Username</label>
                                <input type="text" class="form-control" name="username">

                                <label class="control-label">Email</label>
                                <input type="text" class="form-control" name="email">

                                <label class="control-label">Access level</label>
                                <input type="number" class="form-control" name="accessLevel" min="1">

                                </div>
                                <div class="card-footer" style="padding: 0px">
                                    <input type="submit" style="width: 100%" class="btn btn-primary" value="Create New Employee">
                                    </form>
                                </div>
                        </div>
                    </div>
                    <%}%>
                    <%if (selectedEmployee != null) {%>
                    <div class="col-lg-6">
                        <div class="card bg-info text-white">
                            <form action="FrontController" method="post">
                                <table class="table table-xstriped">
                                    <thead>
                                        <tr>
                                            <th>First Name</th>
                                            <th>Last Name</th>
                                            <th>Username</th>
                                            <th>Access Level</th>
                                            <th>Email</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td> <input type="text" name="firstName" value="<%= selectedEmployee.getFirstname()%>" style="width: 85%"/> </td>
                                            <td> <input type="text" name="lastName" value="<%= selectedEmployee.getLastname()%>" style="width: 85%"/> </td>
                                            <td> <input type="text" name="username" value="<%= selectedEmployee.getUsername()%>" style="width: 85%"/> </td>
                                            <td> <input type="number" name="accessLevel" value="<%= selectedEmployee.getAuthenticationLevel()%>" style="width: 85%"/> </td>
                                            <td> <input type="text" name="email" value="<%= selectedEmployee.getEmail()%>" style="width: 100%"/> </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <%if (selectedEmployee.isEmployed()) {%>

                                <input type="hidden" name="command" value="UpdateEmployee"/>
                                <input type="hidden" name="employeeId" value="<%= selectedEmployee.getEmployeeId()%>"/> 
                                <input type="submit" style="width: 100%" class="btn btn-primary" value="Edit" style="float: left"/>
                            </form>
                            <input style="width: 100%" type="submit" class="btn btn-danger" onclick="fireEmployee()" value="FIRE!" style="width: 7%"/>
                            <%if (!selectedEmployee.isResetPassword()) {%>
                            <form>
                                <input type="hidden" name="command" value="ResetPassword"/>
                                <input type="hidden" name="employeeId" value="<%= selectedEmployee.getEmployeeId()%>"/> 
                                <input type="submit" style="width: 100%" class="btn btn-primary" value="Reset Password" />
                            </form>
                            <%}
                                }%>
                        </div>
                        <br>




                        <div class="card bg-secondary text-white">
                            <div class="card-header">               
                                <h3>Latest evnts for <%= selectedEmployee.getFirstname() + " " + selectedEmployee.getLastname()%></h3>
                            </div>
                            <div class="card-body">
                                <table class="table table-xstriped table-hover">
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
                        </div>

                    </div>
                    <%}%>
                </div>
            </div>
    </body>
</html>
