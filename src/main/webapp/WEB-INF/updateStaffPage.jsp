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
        <title>JSP Page</title>
        <%@include file="../bootstrap.jsp" %>
    </head>
    <body>
        <%
            List<Employee> empList = (List<Employee>) request.getAttribute("empList");
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
                                <th>Access Level</th>
                                <th>Email</th>
                                <th>Hire Date</th>
                            </tr>
                        </thead>

                        <% for (Employee emp : empList) {
                        %>
                        <tbody>
                            <tr <%if (!emp.isEmployed()) { %>
                                class="btn-danger"
                                <%}%>>
                                <!--<td>-->
<!--                                    <form action="FrontController" method="post">
                                        <input type="hidden" name="command" value="ShowOrder">
                                        <input type="hidden" name="orderId" value="<//%= order.getOrderid()%>">
                                        <input type="hidden" name="loads" value="<//%= orderList.size()%>">
                                        <input type="submit" class="btn btn-primary" value="Order <//%= order.getOrderid()%>">
                                    </form>-->
                                <!--</td>-->
                                <td><%= emp.getFirstname() %></td>
                                <td><%= emp.getLastname() %></td>
                                <td><%= emp.getAuthenticationLevel() %></td>
                                <td><%= emp.getEmail() %></td>
                                <td><%= emp.simpleDate() %></td>
                            </tr>
                        </tbody>
                        <% }%>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
