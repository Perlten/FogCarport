
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="FunctionLayer.entities.Order"%>
<%
    Order order2 = (Order) request.getSession().getAttribute("order");
%>
<div class="col-lg-6">
    <div>
        <p><%= order2.getOrderid()%></p>
        <p><%= order2.getCustomer()%></p>
        <p><%= order2.getCustomization()%></p>
        <p><%= order2.simpleDate()%></p>
    </div>
    <div>
        <%@include file="../SVGDraw.jsp" %>
    </div>
</div>