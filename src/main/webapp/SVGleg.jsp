<%-- 
    Document   : SVGleg
    Created on : 01-05-2018, 11:26:03
    Author     : adamlass
--%>

<%@page import="FunctionLayer.entities.Shed"%>
<%@page import="FunctionLayer.entities.Customization"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%
            Order order = (Order) request.getSession().getAttribute("order");
            Customization cust = order.getCustomization();
            int length = cust.getLength();
            int width = cust.getWidth();
            int padding = Customization.padding;
            int rem = Customization.rem;

        %>

        <SVG width="800" height="800" viewBox="-25 0 <%=width + 25%> <%=length + 25%>">


        <!--length and width-->
        <!--lines-->
        <line x1="-10" y1="0" x2="-10" y2="<%= length%>" 
              style="stroke: black"/>
        <line x1="0" y1="<%= length + 10%>" x2="<%= width%>" y2="<%= length + 10%>" 
              style="stroke: black"/>
        <!--text-->
        <text x="<%= length / 2 - 10%>" y="20" transform="rotate(90,0,0)" font-size="8"><%= length + " cm"%></text>
        <text x="<%= width / 2 - 10%>" y="<%= length + 20%>" font-size="8"><%= width + " cm"%></text>



        <!--edge-->
        <rect x="0" y="0" width="<%= width%>" height="<%= length%>"
              style="fill: none; stroke: black "/>

        <!--shed-->
        <% if (cust.getShed() != null) {
                Shed shed = cust.getShed();
        %>
        <rect x="<%= padding%>" y="<%= padding%>" width="<%= shed.getWidth()%>" height="<%= shed.getLength()%>"
              style="fill: none; stroke: black;stroke-width: <%= rem%>; stroke-dasharray: 7, 2"/>
        <%}%>

        <!--rem-->
        <line x1="<%= padding%>" y1="0" x2="<%= padding%>" y2="<%= length%>"
              style="stroke: black; stroke-width: <%= rem%>"/>       
        <line x1="<%= width - padding%>" y1="0" x2="<%= width - padding%>" y2="<%= length%>"
              style="stroke: black; stroke-width: <%= rem%>"/>   

        <!--rafter-->
        <%
            int amountOfRafters = (int) (length / 50);
            double rafterDistance = length / amountOfRafters;
            for (int i = 0; i <= amountOfRafters; i++) {
        %>
        <line x1="0" y1="<%= i * rafterDistance%>" x2="<%= width%>" y2="<%= i * rafterDistance%>"
              style="stroke: black; stroke-width: <%= 2%>"/>
        <%
            }
        %>
        
        <!--poles-->
        <%
            int amountOfPoles = (int) (length / 250) + 1;
            
            if(amountOfPoles < 2){
                amountOfPoles = 2;
            }
            
            double poleDistance = ((length - padding) / amountOfPoles);

            for (int i = 0; i < amountOfPoles; i++) {
                    %>
                    <rect x="<%= padding - (rem/2 +3) %>" y="<%= i * poleDistance + padding%>" width="10" height="10" 
                          style="fill: white; stroke: black; stroke-width: 2"/>
                    
                    <rect x="<%= width - padding - (rem/2 +3) %>" y="<%= i * poleDistance + padding %>" width="10" height="10" 
                          style="fill: white; stroke: black; stroke-width: 2"/>
                    <%
                }
        %>
        </SVG>