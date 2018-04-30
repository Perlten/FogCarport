<%-- 
    Document   : testSVG
    Created on : Apr 30, 2018, 8:15:47 PM
    Author     : Perlt
--%>

<%@page import="FunctionLayer.entities.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Order order = (Order) request.getAttribute("order");%>

        <svg width="600" height="620">
        <rect x="70" y="50" width="470" height="530"
              style="stroke: #000000; fill: none"/>
        <line x1="50" y1="50" x2="50" y2="580" 
              style="stroke: #000000"/>
        <line x1="70" y1="600" x2="540" y2="600" 
              style="stroke: #000000;"/>
        <text x="300" y="10" transform="rotate(90,10,20)"><%= order.getCustomization().getLength()%>m</text>
        <text x="280" y="620"><%= order.getCustomization().getWidth()%>m</text>
        
        <% if(order.getCustomization().getShed() != null){ 
           double sizeXDiff = 470 / order.getCustomization().getWidth();
           double sizeYDiff = 530 / order.getCustomization().getLength();
           
           double shedPixelX = order.getCustomization().getShed().getWidth()* sizeXDiff;
           double shedPixelY = order.getCustomization().getShed().getLength()* sizeYDiff;
        %>
         <rect x="<%=(540 - shedPixelX)%>" y="50" width="<%= shedPixelX %>" height="<%= shedPixelY %>"
              style="stroke: #000000; fill: none"/>
        <%}%>
        
        </svg>
    </body>
</html>
