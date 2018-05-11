
<%@page import="java.text.DecimalFormat"%>
<%@page import="FunctionLayer.calculator.Product"%>
<%@page import="java.util.List"%>
<%
    List<Product> products = (List<Product>) request.getAttribute("pickingList");
%>
   

<script>
    $('#cmd').click(function () {
        window.print();
    });
</script>

<table id="content" class="table table-xstriped table-hover">
    <thead>
        <tr>
            <th>Title</th>
            <th>Length</th>
            <th>Amount</th>
            <th>Unit</th>
            <th>Description</th>
        </tr>

    </thead>
    <tbody>
        <% for (Product prod : products) {%>
        <tr>
            <td><%= prod.getTitle()%></td>
            <td><%= new DecimalFormat("#.00").format(prod.getLength()) %> cm</td>
            <td><%= prod.getAmount()%></td>
            <td><%= prod.getUnit()%></td>
            <td><%= prod.getDescription()%></td>
        </tr>
        <%}%>
    </tbody>
</table>
    <button class='btn btn-default' onclick="window.print()"><img src="content/print.png" alt="" width="20px"/></button>

    
    

