<%-- 
    Document   : editOrder
    Created on : Apr 26, 2018, 6:16:42 PM
    Author     : Perlt
--%>

<%@page import="FunctionLayer.entities.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <a href="../bootstrap.jsp"></a>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
    <% Order order = (Order) request.getAttribute("order"); %>

    <div class="row">
        <div class="col-lg-6">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3>Contents of Order</h3>
                    </div>
                    <div>
                        <table class="table table">
                            <thead>
                            <th>Length</th>
                            <th>Height</th>
                            <th>Width</th>
                            <th>Roof angle</th>
                            <th>Shed Length</th>
                            <th>Shed width</th>
                            </thead>
                            <%
                                int shedLength = 0;
                                int shedWidth = 0;

                                if (order.getCustomization().getShed() != null) {
                                    shedLength = order.getCustomization().getShed().getLength();
                                    shedWidth = order.getCustomization().getShed().getWidth();
                                }
                            %>
                            <tbody>
                                <tr>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="EditOrder">
                                <td> <input type="number"  name="length" value="<%= order.getCustomization().getLength()%>">  </td>
                                <td> <input type="number" name="height" value="<%= order.getCustomization().getHeight()%>" > </td>
                                <td> <input type="number" name="width" value="<%= order.getCustomization().getWidth()%>" > </td>
                                <td> <input type="number" name="roofAngle" value="<%= order.getCustomization().getRoofangle()%>" > </td>
                                <td> <input type="number" name="shedLength" value="<%= shedLength%>" > </td>
                                <td> <input type="number" name="shedWidth" value="<%= shedWidth%>" > </td>
                                <input type="hidden" name="orderId" value="<%= order.getOrderid()%>" >
                                <td> <input type="submit" go> </td>
                            </form>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
