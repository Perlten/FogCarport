<%-- 
    Document   : updateStaffPage
    Created on : May 9, 2018, 6:27:43 PM
    Author     : Perlt
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Staff</title>
        <%@include file="../bootstrap.jsp" %>
    </head>
    <body>
        <%
            List<Employee> empList = (List<Employee>) request.getAttribute("empList");
            Employee selectedEmployee = (Employee) request.getAttribute("selectedEmployee");
            String newEmployee = request.getParameter("newEmployee");
        %>
        <%@include file="../employeesNavBar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6">
                    <table class="table table-xstriped table-hover" style="table-layout: fixed">
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
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="UpdateStaff">
                        <input type="hidden" name="newEmployee" value="true">
                        <input type="submit" class="btn btn-success" value="Create New Employee">
                    </form>
                </div>
                <%if (newEmployee != null) {%>
                <div class="col-lg-6">
                    <form action="FrontController" method="post">
                        <h1>New Employee</h1>
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
                        <br>
                        <input type="submit" class="btn btn-primary" value="Create New Employee">
                    </form>
                </div>
                <%}%>
                <%if (selectedEmployee != null) {%>
                <div class="col-lg-6">
                    <div class="card">
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
                            <input type="submit" class="btn btn-primary" value="Edit"/>
                        </form>
                        <form>
                            <input type="hidden" name="command" value="FireEmployee"/>
                            <input type="hidden" name="employeeId" value="<%= selectedEmployee.getEmployeeId()%>"/> 
                            <input type="submit" class="btn btn-danger" value="FIRE!"/>
                        </form>
                        <%if (!selectedEmployee.isResetPassword()) {%>
                        <form>
                            <input type="hidden" name="command" value="ResetPassword"/>
                            <input type="hidden" name="employeeId" value="<%= selectedEmployee.getEmployeeId()%>"/> 
                            <input type="submit" class="btn btn-primary" value="Reset Password"/>
                        </form>
                        <%}
                            }%>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </body>
</html>
