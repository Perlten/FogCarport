<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../bootstrap.jsp" %>
    </head>
    <body>
        <%
            String type = request.getParameter("type");
        %>
        <div class="row">
            <div class="col-lg-1"></div>
            <form action="FrontController" method="post">
                <div class="col-lg-3">
                    <h1>New <%= type%></h1>
                    <div class="form-group">
                        <input type="hidden" name="command" value="CreateStyle">
                        <input type="hidden" name="type" value="<%= type %>">

                        <label class="control-label">Name</label>
                        <input type="Text" class="form-control" name="name" min="0">

                        <label class="control-label">Description</label>
                        <textarea class="form-control" rows="5" name="description"> </textarea>

                        <label class="control-label">Price</label>
                        <input type="number" class="form-control" name="price" min="0">
                        <br>
                        <button type="submit" class="btn btn-primary">Create new <%= type%></button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
