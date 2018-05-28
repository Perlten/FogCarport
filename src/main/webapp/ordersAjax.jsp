<%@page import="DBAccess.OrderMapper"%>
<%@page import="FunctionLayer.entities.Customer"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    List<Order> orderList = LogicFacade.getCustomerList(num);
%>

<table class="table table-xstriped">
    <thead>
        <tr>
            <th>Order Number</th>
            <th>Email</th>
            <th>Date</th>
            <th>Name</th>
            <th>Phonenumber</th>
        </tr>
    </thead>

    <% for (Order order : orderList) {
            Customer cust = order.getCustomer();
    %>
    <tbody>
        <tr <%if (order.isConfirmed()) { %>
            class="btn-success"
            <%}%>>
            <td>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="ShowOrder">
                    <input type="hidden" name="orderId" value="<%= order.getOrderid()%>">
                    <input type="hidden" name="loads" value="<%= orderList.size()%>">
                    <input type="submit" class="btn btn-primary" value="Order <%= order.getOrderid()%>">
                </form>

            </td>
            <td><%= cust.getEmail()%></td>
            <td><%= order.simpleDate()%></td>
            <td><%= cust.getLastname() + ", " + cust.getFirstname()%></td>
            <td><%= cust.getPhonenumber()%></td>
        </tr>
    </tbody>
    <% }%>
</table>