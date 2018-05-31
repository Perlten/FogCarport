

<script>
    function applyMode() {
        console.log("apply");
        if (true) {
            document.getElementById('h1Mode').style = "color: white";
            document.getElementById("bodyMode").style = "background-color: #2E3338";
        }
    }



    var x = document.cookie;
    window.onload = function () {
        applyMode();

        //We need to put this here becuase there can only be one onload function
        if (sessionStorage.getItem("load") !== "false") {
            document.getElementById('censorBanner').style.display = "block";
        }
        //for error banner
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

<div >
    <%
        if (session.getAttribute("error") != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show"
         style="margin-left: 15px; margin-right: 15px">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <%= session.getAttribute("error")%>
    </div>
    <hr>
    <%
            session.setAttribute("error", null);
        }
    %>

</div>

