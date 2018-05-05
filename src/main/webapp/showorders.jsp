<%@page import="DBAccess.OrderMapper"%>
<%@page import="FunctionLayer.entities.Customer"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%
    int num = Integer.parseInt(request.getParameter("num"));
    List<Order> orderList = LogicFacade.getCustomerList(num);
%>

<table class="table table-xstriped table-hover">
    <thead>
        <tr>
            <th>Order Number</th>
            <th>Email</th>
            <th>Name</th>
            <th>Phonenumber</th>
        </tr>
    </thead>

    <% for (int i = 0; i < orderList.size(); i++) {
            Customer cust = orderList.get(i).getCustomer();
    %>
    <tbody>
            <td>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="ShowOrder">
                    <input type="hidden" name="orderId" value="<%= orderList.get(i).getOrderid()%>">
                    <input type="submit" class="btn btn-default" value="Order <%= orderList.get(i).getOrderid()%>">
                </form>

            </td>
            <td><%= cust.getEmail()%></td>
            <td><%= cust.getLastname() + ", " + cust.getFirstname()%></td>
            <td><%= cust.getPhonenumber()%></td>
        </tr>
    </tbody>
    <% }%>
</table>