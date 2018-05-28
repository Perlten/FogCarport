<%-- 
    Document   : styling
    Created on : 28-04-2018, 21:45:21
    Author     : adamlass
--%>

<%@page import="FunctionLayer.entities.Customization"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.StyleOption"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Styling</title>
        <%@include file="/WEB-INF/include/includeBootstrap.jsp" %>

        <% List<StyleOption> claddings = (List<StyleOption>) request.getAttribute("claddings");
            List<StyleOption> tiles = (List<StyleOption>) request.getAttribute("tiles");
            Order order = (Order) session.getAttribute("order");
            StyleOption cladding = null;
            StyleOption tile = null;
            if (order != null) {
                Customization cust = order.getCustomization();
                if (cust != null) {
                    cladding = cust.getCladding();
                    tile = cust.getTile();
                }
            }
        %>
    </head>
    <body>
        <%@include file="../include/includeCustomerNav.jsp" %>
        <script>
            $(document).ready(function () {
                $('[data-toggle="popover"]').popover();
            });
        </script>
        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-6">
                    <div class="card">
                        <form action="FrontController" method="POST" >

                            <div class="card-header">
                                <h4>Choose your styling!</h4>
                            </div>
                            <div class="card-body">
                                <input type="hidden" name="command" value="GiveStyling">

                                <% if(order.getCustomization().getShed() != null) { %>
                                <h4>Cladding</h4>
                                <% for (StyleOption option : claddings) {%>
                                <label>
                                    <div class="card">
                                        <div class="card-body">
                                            <div>
                                                <a class="text-body" href="#" data-toggle="popover" title="<%= option.getName()%>" data-content="<%= option.getDescription()%>" style="text-decoration: none"><%= option.getName() + " &#9432"%> </a>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <input type="radio" name="cladding" value="<%= option.getId()%>" <%
                                                if (option.equals(cladding)) {
                                                    out.print("checked");
                                                }
                                                   %>>
                                            <a class="text-body"><%= option.getPrice() + " DKK/m"%> </a>
                                        </div>
                                    </div>
                                </label>
                                <%}%>
                                <br>
                                <%}%>
                                <h4>Tiles</h4>
                                <% for (StyleOption option : tiles) {%>
                                <label>
                                    <div class="card">
                                        <div class="card-body">
                                            <div>
                                                <a class="text-body" href="#" data-toggle="popover" title="<%= option.getName()%>" data-content="<%= option.getDescription()%>" style="text-decoration: none"><%= option.getName() + " &#9432"%> </a>
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <input type="radio" name="tile" value="<%= option.getId()%>" <%
                                                if (option.equals(tile)) {
                                                    out.print("checked");
                                                }
                                                   %>>
                                            <a class="text-body"><%= option.getPrice() + " DKK/tile"%> </a>
                                        </div>
                                    </div>
                                </label>
                                <%}%>
                            </div>


                    </div>
                    <div class="card-footer">
                        <input type="submit" name="submit" class="btn btn-primary" value="Next" style="float: right">
                        <input type="submit" name="submit" class="btn btn-white" value="Back">
                        <input type="submit" name="submit" class="btn btn-secondary" value="Update" style="float: right; margin-right: 10px;">
                    </div>
                    </form>
                </div>
                <% if (order != null) {%>
                <div class="col-lg-6">
                    <%@include file="../include/includeOverview.jsp" %>
                </div>       
                <%}%>
            </div>
        </div>
    </body>
</html>
