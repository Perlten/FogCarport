<%@page import="FunctionLayer.entities.StyleOption"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="FunctionLayer.entities.Order"%>
<%
    Order orderOverview = (Order) request.getSession().getAttribute("order");
    Customization custSelected = orderOverview.getCustomization();
    StyleOption claddingSelected = custSelected.getCladding();
    StyleOption tileSelected = custSelected.getTile();
    boolean roof = custSelected.getRoofangle() > 0;
    boolean hasShed = custSelected.getShed() != null;

%>

<div class="panel panel-default">
    <div class="panel-heading">
        <h2>Overview</h2>
    </div>
    <div class="panel-default">

        <div>
            <div>
                <table class="table table">
                    <thead>
                    <th>Length</th>
                    <th>Width</th>
                    <th>Height</th>

                    <% if (roof) { %>
                    <th>Roof angle</th>

                    <%}%>
                    <% if (hasShed) { %>
                    <th>Shed Length</th>
                    <th>Shed width</th>
                        <%}%>
                    </thead>
                    <%
                        int selectedShedLength = 0;
                        int selectedShedWidth = 0;

                        if (custSelected.getShed() != null) {
                            selectedShedLength = custSelected.getShed().getLength();
                            selectedShedWidth = custSelected.getShed().getWidth();
                        }
                    %>
                    <tbody>
                        <tr>
                            <td> <%= custSelected.getLength()%> </td>
                            <td> <%= custSelected.getWidth()%> </td>
                            <td> <%= custSelected.getHeight()%> </td>

                            <% if (roof) {%>
                            <td> <%= custSelected.getRoofangle()%> </td>
                            <%}%>

                            <% if (hasShed) {%>
                            <td> <%= selectedShedLength%> </td>
                            <td> <%= selectedShedWidth%> </td>
                            <%}%>


                        </tr>
                    </tbody>
                </table>
            </div>

        </div>

            <div style="height: 500px; width: 70%; margin: auto">
                <%@include file="../SVGDraw.jsp" %>
            </div>
        </div>
    </div>

