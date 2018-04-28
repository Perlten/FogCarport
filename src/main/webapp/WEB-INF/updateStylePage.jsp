
<%@page import="FunctionLayer.entities.StyleOption"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="FunctionLayer.entities.Customer"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.entities.Order"%>
<%@page import="FunctionLayer.LogicFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Orders</title>
        <%@include file="../bootstrap.jsp" %>
        <link href="OrdersStyle.css" rel="stylesheet" type="text/css"/>
        <%
            List<StyleOption> claddingList = (List<StyleOption>) request.getAttribute("claddingList");
            List<StyleOption> tileList = (List<StyleOption>) request.getAttribute("tileList");

        %>
    </head>
    <body>
        <h1>Update Styleing</h1>

        <div class="row">
            <div class="col-lg-4">
                <table class="table table-xstriped table-hover">
                    <h2>Cladding</h2>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                        </tr>
                    </thead>

                    <% for (StyleOption style : claddingList) {%>
                    <tbody>
                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="ShowOrder">
                            <input type="hidden" name="orderId" value="<%= style.getId()%>">
                            <input type="submit" class="btn btn-default" value="Cladding <%= style.getId()%>">
                        </form>

                    </td>
                    <td><%= style.getName()%></td>
                    <td><%= style.getDescription()%></td>
                    <td><%= style.getPrice()%></td>
                    </tr>
                    </tbody>
                    <% }%>
                </table>
            </div>
                
            <div class="col-lg-4">
                <table class="table table-xstriped table-hover">
                    <h2>Tile</h2>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                        </tr>
                    </thead>

                    <% for (StyleOption style : tileList) {%>
                    <tbody>
                    <td>
                        <form action="FrontController" method="post">
                            <input type="hidden" name="command" value="ShowOrder">
                            <input type="hidden" name="orderId" value="<%= style.getId()%>">
                            <input type="submit" class="btn btn-default" value="Tile <%= style.getId()%>">
                        </form>

                    </td>
                    <td><%= style.getName()%></td>
                    <td><%= style.getDescription()%></td>
                    <td><%= style.getPrice()%></td>
                    </tr>
                    </tbody>
                    <% }%>
                </table>

            </div>
        </div>
    </body>
</html>
