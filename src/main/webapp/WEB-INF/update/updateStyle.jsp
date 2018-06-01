    
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
        <%@include file="../include/includeBootstrap.jsp" %>
        <%
            List<StyleOption> claddingList = (List<StyleOption>) request.getAttribute("claddingList");
            List<StyleOption> tileList = (List<StyleOption>) request.getAttribute("tileList");
            StyleOption selectedStyle = (StyleOption) request.getAttribute("selectedStyle");
            String type = (String) request.getAttribute("type");
        %>
    </head>
    <body id="bodyMode">
        <script>

            function applyMode() {
                var darkMode = sessionStorage.getItem("darkMode");
                if (darkMode === "true") {
                    document.getElementById('h1Mode').style = "color: white; display: inline";
                    document.getElementById("bodyMode").style = "background-color: #2E3338";
                    document.getElementById("cardMode").className = "card bg-dark text-white";
                    document.getElementById("cardMode2").className = "card bg-dark text-white";
                    document.getElementById("slider").checked = true;
                }
            }
        </script>
        <%@include file="../include/includeEmployeeNav.jsp"%>
        <div class="container-fluid">
            <div class="row">
                <div class="container-fluid">
                    <h1 id="h1Mode" style="display: inline">Update Stylings</h1>
                    <div style="float: right">
                        <%@include file="../include/includeDarkModeSlider.jsp" %>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-5">
                    <div class="card" id="cardMode">
                        <div class="card-header">
                            <h2>Cladding</h2>
                        </div>
                        <div class="card-body" style="padding: 0px">
                            <table class="table table-xstriped table-hover">
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
                                        <td data-toggle="tooltip" data-placement="bottom" title="<%= style.getDescription()%>"><%= style.getShortDescription()%></td>
                                        <td><%= style.getPrice()%></td>
                                    </tr>
                                </tbody>
                                <% }%>
                            </table>
                        </div>
                        <div class="card-footer" style="padding: 0px">
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="CreateStylePage">
                                <input type="hidden" name="type" value="cladding">
                                <button type="submit" class="btn btn-success" style="width: 100%">Create new Cladding</button>
                            </form>  
                        </div>
                    </div>

                </div>

                <div class="col-lg-5">
                    <div class="card" id="cardMode2">
                        <div class="card-header">
                            <h2>Tile</h2>
                        </div>
                        <div class="card-body" style="padding: 0px">
                            <table class="table table-xstriped table-hover">
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
                                        <td data-toggle="tooltip" data-placement="bottom" title="<%= style.getDescription()%>"><%= style.getShortDescription()%></td>
                                        <td><%= style.getPrice()%></td>
                                    </tr>
                                </tbody>
                                <% }%>
                            </table>
                        </div>
                        <div class="card-footer" style="padding: 0px">
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="CreateStylePage">
                                <input type="hidden" name="type" value="tile">
                                <button type="submit" class="btn btn-success" style="width: 100%">Create new Tile</button>
                            </form>
                        </div>
                    </div>


                </div>
                <% if (selectedStyle != null) {%>
                <div class="col-lg-2">
                    <div class="card bg-info text-white">
                        <div class="card-header">
                            <h2><%= selectedStyle.getName()%></h2>
                        </div>
                        <div class="card-body">
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
                                    <button type="submit" class="btn btn-primary" style="width:100%">Update</button>
                                </div>
                            </form>
                        </div>

                        <div class="card-footer" style="padding: 0px">
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="DeleteStyle">
                                <input type="hidden" name="styleId" value="<%= selectedStyle.getId()%>">
                                <input type="hidden" name="type" value="<%= type%>">
                                <input type="submit" class="btn btn-danger" style="width: 100%" value="Delete">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%}%>
        </div>
    </body>
</html>


