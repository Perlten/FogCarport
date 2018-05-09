<%-- 
    Document   : credentialsPage
    Created on : May 1, 2018, 10:08:25 PM
    Author     : Perlt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Info</title>
        <%@include file="../bootstrap.jsp" %>
        <%

            Order orderSelected = (Order) session.getAttribute("order");
            Customer customerCredentials = orderSelected.getCustomer();

            String firstname = "";
            String lastname = "";
            String email = "";
            int phonenumber = 0;

            if (customerCredentials != null) {
                firstname = customerCredentials.getFirstname();
                lastname = customerCredentials.getLastname();
                email = customerCredentials.getEmail();
                phonenumber = customerCredentials.getPhonenumber();
            }


        %>
    </head>
    <body>
        <div class="container-fluid">
        <h1>Contact Info</h1>
        <div class="row">
            <div class="col-lg-6">
                <div class="card">
                    <div class="card-head">
                        <h2>Enter Your Contact Information</h2>
                    </div>

                    <div class="card-body">

                        <div style="padding: 20px">
                            <form action="FrontController" method="post">
                                <div class="form-group">
                                    <input type="hidden" name="command" value="GiveCredentials">
                                    <label class="control-label">First Name</label>
                                    <input type="text" class="form-control" name="firstName" placeholder="Enter first name" value="<%= firstname%>"/>

                                    <label class="control-label">Last Name</label>
                                    <input type="text" class="form-control" name="lastName" placeholder="Enter last name" value="<%= lastname%>"/>

                                    <label class="control-label">Email</label>
                                    <input type="text" class="form-control" name="email" placeholder="Enter email" value="<%= email%>"/>

                                    <label class="control-label">Phone Number</label>
                                    <input type="number" class="form-control" name="phoneNumber" placeholder="Enter phone number" value="<%
                                        if (phonenumber != 0) {
                                            out.print(phonenumber);
                                        }
                                           %>"/>
                                    <br>
                                    <button type="submit" class="btn btn-primary">Next</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card-footer">
                        <form action="FrontController" method="POST">
                            <input type="hidden" name="command" value="Styling">
                            <input type="submit" class="btn btn-default" value="Back">
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <%@include file="overview.jsp" %>
            </div>
        </div>
        </div>
    </body>
</html>
