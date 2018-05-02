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
    Order orderDraw = (Order) request.getSession().getAttribute("order");
    Customization drawCust = orderDraw.getCustomization();
    int drawLength = drawCust.getLength();
    int drawWidth = drawCust.getWidth();

    if (orderDraw == null) {
        orderDraw = (Order) request.getSession().getAttribute("order");
    }

    int padding = Customization.padding;
    int rem = Customization.beam;

%>

<SVG width="100%" height="100%" viewBox="-25 0 <%=drawWidth + 25%> <%=drawLength + 25%>">


<!--length and width-->
<!--lines-->
<line x1="-10" y1="0" x2="-10" y2="<%= drawLength%>" 
      style="stroke: black"/>
<line x1="0" y1="<%= drawLength + 10%>" x2="<%= drawWidth%>" y2="<%= drawLength + 10%>" 
      style="stroke: black"/>
<!--text-->
<text x="<%= drawLength / 2 - 10%>" y="20" transform="rotate(90,0,0)" font-size="8"><%= drawLength + " cm"%></text>
<text x="<%= drawWidth / 2 - 10%>" y="<%= drawLength + 20%>" font-size="8"><%= drawWidth + " cm"%></text>



<!--edge-->
<rect x="0" y="0" width="<%= drawWidth%>" height="<%= drawLength%>"
      style="fill: none; stroke: black "/>

<!--shed-->
<% if (drawCust.getShed() != null) {
        Shed drawShed = drawCust.getShed();
%>
<rect x="<%= padding%>" y="<%= padding%>" width="<%= drawShed.getWidth()%>" height="<%= drawShed.getLength()%>"
      style="fill: none; stroke: black;stroke-width: <%= rem%>; stroke-dasharray: 7, 2"/>
<%}%>

<!--rem-->
<line x1="<%= padding%>" y1="0" x2="<%= padding%>" y2="<%= drawLength%>"
      style="stroke: black; stroke-width: <%= rem%>"/>       
<line x1="<%= drawWidth - padding%>" y1="0" x2="<%= drawWidth - padding%>" y2="<%= drawLength%>"
      style="stroke: black; stroke-width: <%= rem%>"/>   

<!--rafter-->
<%
    int amountOfRafters = (int) (drawLength / 50);
    double rafterDistance = drawLength / amountOfRafters;
    for (int i = 0; i <= amountOfRafters; i++) {
%>
<line x1="0" y1="<%= i * rafterDistance%>" x2="<%= drawWidth%>" y2="<%= i * rafterDistance%>"
      style="stroke: black; stroke-width: <%= 2%>"/>
<%
    }
%>

<!--poles-->
<%
    int amountOfPoles = (int) (drawLength / 250) + 1;

    if (amountOfPoles < 2) {
        amountOfPoles = 2;
    }

    double poleDistance = ((drawLength - padding) / amountOfPoles);

    for (int i = 0; i < amountOfPoles; i++) {
%>
<rect x="<%= padding - (rem / 2 + 3)%>" y="<%= i * poleDistance + padding%>" width="10" height="10" 
      style="fill: white; stroke: black; stroke-drawWidth: 2"/>

<rect x="<%= drawWidth - padding - (rem / 2 + 3)%>" y="<%= i * poleDistance + padding%>" width="10" height="10" 
      style="fill: white; stroke: black; stroke-width: 2"/>
<%
    }
%>
</SVG>