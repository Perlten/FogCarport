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
        %>
        <%@include file="../employeesNavBar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-5">
                    <table class="table table-xstriped table-hover">
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Username</th>
                                <th>Access Level</th>
                                <th>Email</th>
                                <th>Hire Date</th>
                                <th></th>
                            </tr>
                        </thead>

                        <% for (Employee emp : empList) {
                        %>
                        <tbody>
                            <tr <%if (!emp.isEmployed()) { %>
                                class="btn-danger"
                                <%}%>>
                                <td><%= emp.getFirstname()%></td>
                                <td><%= emp.getLastname()%></td>
                                <td><%= emp.getUsername()%></td>
                                <td><%= emp.getAuthenticationLevel()%></td>
                                <td><%= emp.getEmail()%></td>
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
                <%if (selectedEmployee != null) {%>
                <div class="col-lg-7">
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
