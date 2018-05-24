

<script>
    var x = document.cookie;
    window.onload = function () {
        if (getCookie("refresh") === "true") {
            document.cookie = "refresh = false";
            location.reload();
        }
    };

    function getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
</script>

<div style="margin-left: 15px; margin-right: 15px">
    <%
        if (session.getAttribute("error") != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <%= session.getAttribute("error")%>
    </div>
    <%
            session.setAttribute("error", null);
        }
    %>

</div>
