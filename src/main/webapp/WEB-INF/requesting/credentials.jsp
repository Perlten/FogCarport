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
        <%@include file="/WEB-INF/include/includeBootstrap.jsp" %>
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
        <script>
            function removeRequired(){
                document.getElementById("firstName").required = false;
                document.getElementById("lastName").required = false;
                document.getElementById("email").required = false;
                document.getElementById("phoneNumber").required = false;
            }
        </script>
        <%@include file="../include/includeCustomerNav.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6">
                    <div class="card">
                        <form action="FrontController" method="post">

                            <div class="card-header">
                                <h2>Enter Your Contact Information</h2>
                            </div>

                            <div class="card-body">

                                <div class="form-group">
                                    <input type="hidden" name="command" value="GiveCredentials">
                                    <label class="control-label">First Name</label>
                                    <input id="firstName" type="text" class="form-control" name="firstName" placeholder="Enter first name" value="<%= firstname%>" required/>

                                    <label class="control-label">Last Name</label>
                                    <input id="lastName" type="text" class="form-control" name="lastName" placeholder="Enter last name" value="<%= lastname%>" required/>

                                    <label class="control-label">Email</label>
                                    <input id="email"  type="email" class="form-control" name="email" placeholder="Enter email" value="<%= email%>" required/>

                                    <label class="control-label">Phone Number</label>
                                    <input id="phoneNumber" type="number" class="form-control" name="phoneNumber" placeholder="Enter phone number" value="<%
                                        if (phonenumber != 0) {
                                            out.print(phonenumber);
                                        }
                                        %>" required/>
                                    <br>
                                </div>
                            </div>
                            <div class="card-footer">
                                <input type="submit" name="submit" class="btn btn-primary" value="Next" style="float: right">
                                <input type="submit" onclick="removeRequired()" name="submit" class="btn btn-white" value="Back">
                                <input type="submit" name="submit" class="btn btn-secondary" value="Update" style="float: right; margin-right: 10px;">
                            </div>
                        </form>

                    </div>
                </div>
                <div class="col-lg-6">
                    <%@include file="../include/includeOverview.jsp" %>
                </div>
            </div>
        </div>
    </body>
</html>
