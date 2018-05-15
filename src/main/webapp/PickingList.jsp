
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
            <th><img src="content/check.png" alt="" style="width: 30px"/></th>
            <th>Title</th>
            <th>Length Used</th>
            <th>Length</th>
            <th>Amount</th>
            <th>Unit</th>
            <th>Description</th>
        </tr>

    </thead>
    <tbody>
        <% for (Product prod : products) {%>
        <tr>

            <td>
                <form>
                    <input type="checkbox" style="width: 30px; height: 30px">
                </form>
            </td>
            <td><%= prod.getTitle()%></td>
            <td><%= new DecimalFormat("#.00").format(prod.getLengthUsed())%> cm</td>
            <td><%= new DecimalFormat("#.00").format(prod.getLength())%> cm</td>
            <td><%= prod.getAmount()%></td>
            <td><%= prod.getUnit()%></td>
            <td><%= prod.getDescription()%></td>
        </tr>
        <%}%>
    </tbody>
</table>
<button class="btn btn-light" onclick="window.history.back()">Back</button>
<button class='btn btn-default' onclick="window.print()"><img src="content/print.png" alt="" width="20px"/></button>




