<%-- 
    Document   : confirm
    Created on : 03-05-2018, 15:34:39
    Author     : adamlass
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Request</title>
        <%@include file="../bootstrap.jsp" %>
        <% Order orderConfirm = (Order) session.getAttribute("order");
            if (orderConfirm == null) {
                orderConfirm = (Order) session.getAttribute("confirmedOrder");
            }


        %>

    </head>
    <body>
        <h1><%= orderConfirm.getCustomer().getFirstname()%>'s Carport Request</h1>
        <div class="row">
            <% if (!orderConfirm.isOrdered()) { %>
            <div class="col-lg-6">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Do you want to confirm your request?</h4>
                        <div>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="SubmitOrder">
                                <input type="submit"  class="btn btn-success" value="Submit Request">
                            </form>
                        </div>
                        <div>
                            <form action="FrontController" method="post">
                                <input type="hidden" name="command" value="GiveCredentialsPage">
                                <input type="submit"  class="btn btn-default" value="Back">
                            </form>

                        </div>

                    </div>

                </div>
            </div>
            <%} else {%>
            <div class="col-lg-6">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4>Request sent!</h4>
                        <p class="text text-muted">You have received an email with the details of your order. We will contact you once we have processed 
                            your request.</p>
                    </div>

                </div>

                <% if (orderConfirm.isConfirmed()) { %>
                <div class="panel-heading">
                    <h4>Order Confirmed!</h4>
                    <p class="text text-muted">Your order has been confirmed by one
                        of our employees, please proceed by entering more detailed 
                        contact information.</p>
                </div>

                <%}%>

            </div>

            <%}%>

            <div class="col-lg-6">
                <%@include file="WEB-INF/overview.jsp" %>
            </div>

        </div>
    </body>
</html>
