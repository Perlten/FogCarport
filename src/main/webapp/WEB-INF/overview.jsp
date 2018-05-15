<%@page import="FunctionLayer.entities.Customer"%>
<%@page import="FunctionLayer.entities.StyleOption"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="FunctionLayer.entities.Order"%>
<%
    Order orderOverview = (Order) request.getSession().getAttribute("order");

    if (orderOverview == null) {
        orderOverview = (Order) request.getSession().getAttribute("confirmedOrder");
    }
    

    Customization custSelected = orderOverview.getCustomization();
    Customer customerSelected = orderOverview.getCustomer();
    StyleOption claddingSelected = custSelected.getCladding();
    StyleOption tileSelected = custSelected.getTile();
    boolean roof = custSelected.getRoofangle() > 0;
    boolean hasShed = custSelected.getShed() != null;


%>

<div class="card">
    <div class="card-header">
        <h2>Overview</h2>

        <div class="container-fluid">
            <div class="progress">
                <%                    String status = "progress-bar";
                    if (orderOverview.isOrdered()) {
                        status += " bg-success";
                    }
                %>
                <div class="<%= status%>" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:<%= orderOverview.percentage()%>%"> Requesting <%= orderOverview.percentage()%>%
                </div>
            </div>
        </div>

    </div>
    <div class="card-body">
        <div style="height: 350px; width: 95%; margin: auto">
            <%@include file="../SVGDraw.jsp" %>
        </div>

        <div>
            <div>
                <h5 class="text-muted">Dimentions</h5>
                <table class="table">
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

        <% if (claddingSelected != null && tileSelected != null) {%> 
        <div>
            <h5 class="text-muted">Styling</h5>
            <table class="table table-xstriped">
                <thead>
                <th>Cladding</th>
                <th>Tile</th>
                </thead>
                <tbody>
                    <tr>
                        <td><%= claddingSelected.getName()%></td>
                        <td><%= tileSelected.getName()%></td>
                    </tr>

                </tbody>
            </table>

        </div>
        <%}%>

        <% if (customerSelected != null) {%>
        <div>
            <h5 class="text-muted">Contact Information</h5>
            <table class="table table-xstriped">
                <thead>
                    <tr>
                        <th>Firstname</th>
                        <th>Lastname</th>
                        <th>Email</th>
                        <th>Phonenumber</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><%= customerSelected.getFirstname()%></td>
                        <td><%= customerSelected.getLastname()%></td>
                        <td><%= customerSelected.getEmail()%></td>
                        <td><%= customerSelected.getPhonenumber()%></td>
                    </tr>

                </tbody>
            </table>
        </div>

        <%}%>
        <div>
            <a class="text-muted">Est. Price:</a> <span class="badge badge-success"><%= orderOverview.getPrice() %> DKK</span>
        </div>
    </div>
</div>

