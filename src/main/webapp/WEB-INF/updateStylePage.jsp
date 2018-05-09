    
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
            StyleOption selectedStyle = (StyleOption) request.getAttribute("selectedStyle");
            String type = (String) request.getAttribute("type");
        %>
    </head>
    <body>
        <%@include file="../employeesNavBar.jsp"%>
        <div class="container-fluid">
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
                        <%if (selectedStyle != null && selectedStyle.equals(style)) {%>
                        <tr class="btn-success">
                            <%}%>
                            <td>
                                <form action="FrontController" method="post">
                                    <input type="hidden" name="command" value="GetStyle">
                                    <input type="hidden" name="styleId" value="<%= style.getId()%>">
                                    <input type="hidden" name="type" value="cladding">
                                    <input type="submit" class="btn btn-primary" value="Cladding <%= style.getId()%>">
                                </form>

                            </td>
                            <td><%= style.getName()%></td>
                            <td><%= style.getShortDescription() %></td>
                            <td><%= style.getPrice()%></td>
                        </tr>
                    </tbody>
                    <% }%>
                </table>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="CreateStylePage">
                    <input type="hidden" name="type" value="cladding">
                    <button type="submit" class="btn btn-success">Create new Cladding</button>
                </form>
                <br>
                 <!--TODO: make better-->
                <form action="index.jsp" method="post">
                    <input type="submit" class="btn btn-primary" value="Back">
                </form>
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
                        <%if (selectedStyle != null && selectedStyle.equals(style)) {%>
                        <tr class="btn-success">
                            <%}%>
                            <td>
                                <form action="FrontController" method="post">
                                    <input type="hidden" name="command" value="GetStyle">
                                    <input type="hidden" name="styleId" value="<%= style.getId()%>">
                                    <input type="hidden" name="type" value="tile">
                                    <input type="submit" class="btn btn-primary" value="Tile <%= style.getId()%>">
                                </form>
                            </td>
                            <td><%= style.getName()%></td>
                            <td><%= style.getShortDescription()%></td>
                            <td><%= style.getPrice()%></td>
                        </tr>
                    </tbody>
                    <% }%>
                </table>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="CreateStylePage">
                    <input type="hidden" name="type" value="tile">
                    <button type="submit" class="btn btn-success">Create new Tile</button>
                </form>
            </div>
            <% if (selectedStyle != null) {%>
            <div class="col-lg-2">
                <div class="card-header">
                    <h2><%= selectedStyle.getName()%></h2>
                </div>
                    <form action="FrontController" method="post">
                        <div class="form-group">
                            <input type="hidden" name="command" value="UpdateStyle">
                            <input type="hidden" name="type" value="<%=type%>">
                            <label class="control-label">Name</label>
                            <input type="text" class="form-control" name="name" value="<%= selectedStyle.getName()%>">

                            <label class="control-label">Description</label>
                            <textarea class="form-control" rows="5" name="description"><%=selectedStyle.getDescription()%></textarea>

                            <label class="control-label">Price</label>
                            <input type="number" class="form-control" name="price" min="0" value="<%= selectedStyle.getPrice()%>">

                            <input type="hidden" name="styleId" value="<%= selectedStyle.getId()%>">
                            <br>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </form>
                    <form action="FrontController" method="post">
                        <input type="hidden" name="command" value="DeleteStyle">
                        <input type="hidden" name="styleId" value="<%= selectedStyle.getId() %>">
                        <input type="hidden" name="type" value="<%= type %>">
                        <input type="submit" class="btn btn-danger" value="Delete">
                    </form>
                </div>
            </div>
        <%}%>
        </div>>
    </body>
</html>
