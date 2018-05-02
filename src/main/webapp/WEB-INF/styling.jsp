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
        <%@include file="../bootstrap.jsp" %>

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
        <script>
            function soren(id) {
                var x = document.getElementById(id);
                if (x.style.display === "none") {
                    x.style.display = "block";
                } else {
                    x.style.display = "none";

                }
            }
        </script>

        <h1>Styling</h1>

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Choose your styling!</h4>
                    </div>
                    <div class="panel-body">
                        <form action="FrontController" method="POST" >
                            <input type="hidden" name="command" value="GiveStyling">

                            <div>
                                <h4>Cladding</h4>
                                <% for (StyleOption option : claddings) {%>
                                <label>
                                    <div class="panel panel-default">
                                        <label>
                                            <blockquote>
                                                <p><%= option.getName()%></p>
                                            </blockquote>
                                            <div class="panel-body">
                                                <% String divid = "div" + option.hashCode();%>
                                                <a class="text-muted" onclick="soren('<%=divid%>')">Get Description</a>
                                                <div id="<%= divid%>" style="display: none"><%= option.getDescription()%></div>
                                            </div>

                                            <input type="radio" name="cladding" value="<%= option.getId()%>">
                                            <a class="text-success"><%= option.getPrice() + " DKK/m"%> </a>

                                        </label>

                                    </div>
                                </label>
                                <%}%>
                            </div>
                            <div>
                                <h4>Tiles</h4>
                                <% for (StyleOption option : tiles) {%>
                                <label>
                                    <div class="panel panel-default">
                                        <label>
                                            <blockquote>
                                                <p><%= option.getName()%></p>
                                            </blockquote>
                                            <div class="panel-body">
                                                <% String divid = "div" + option.hashCode();%>
                                                <a class="text-muted" onclick="soren('<%=divid%>')">Get Description</a>
                                                <div id="<%= divid%>" style="display: none"><%= option.getDescription()%></div>
                                            </div>

                                            <input type="radio" name="tile" value="<%= option.getId()%>">
                                            <a class="text-success"><%= option.getPrice() + " DKK/m"%> </a>
                                        </label>

                                    </div>
                                </label>
                                <%}%>
                            </div>
                            <input type="submit" class="btn btn-primary" value="Next">
                        </form>


                    </div>
                    <div class="panel-footer">
                        <form action="FrontController" method="POST">
                            <input type="hidden" name="command" value="GiveDimentionsPage">
                            <input type="submit" class="btn btn-default" value="Back">
                        </form>
                    </div>
                </div>
            </div>
            <% if (order != null) {%>
            <div class="col-lg-6">
                <%@include file="overview.jsp" %>

            </div>       
            <%}%>

        </div>


    </body>
</html>
