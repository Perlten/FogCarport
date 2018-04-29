<%-- 
    Document   : styling
    Created on : 28-04-2018, 21:45:21
    Author     : adamlass
--%>

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
        %>
    </head>
    <body>
        <h1>Styling</h1>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Choose your styling!</h4>
                    </div>

                    <form action="LogicFacade" method="post">
                        <input type="hidden" name="command" value="Style">

                        <div>
                            <h4>Cladding</h4>
                            <% for (StyleOption option : claddings) {%>
                            <label>
                                <div class="panel panel-default">
                                    <label>
                                        <img src="../content/cladding.jpeg" alt="cladding"/>
                                        <div class="panel-body"><%= option.getName()%></div>


                                        <input type="radio" name="cladding" value="true">
                                        <a class="text-success"><%= option.getPrice() + " DKK/m"%> </a>

                                    </label>

                                </div>
                            </label>
                            <%}%>
                        </div>
                        <input type="submit" value="Next">
                    </form>

                </div>
            </div>


        </div>


    </body>
</html>
