<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% int numPage = Integer.parseInt(request.getParameter("numPage")); %>
<% if (numPage > 1) { %>
<ul class="pagination paginationjs" data-numpage="<%=numPage%>">
    <li class="waves-effect" data-typepage="Previous">
        <a><i class="material-icons">chevron_left</i></a>
    </li>
    <li class="active" data-typepage="Item" data-page="1"><a>1</a></li>
        <% for (int i = 1; i < numPage; i++) {%>
    <li class="waves-effect" data-typepage="Item" data-page="<%=i+1%>">
        <a ><%=i+1%></a>
    </li>
    <%}%>   
    <li class="waves-effect" data-typepage="Next"><a><i class="material-icons">chevron_right</i></a></li>
</ul>
<script>
    (function () {
        var domPaginacion = document.querySelectorAll('.paginationjs');
        if (domPaginacion.length > 0) {
            var mostrarPaginacion = function (pNumPage) {
                $("table.paginationjs tbody tr[data-page]").hide();
                $("table.paginationjs tbody tr[data-page='" + pNumPage + "']").show();
                $("ul.paginationjs").attr("data-pageactive", pNumPage);
                $("ul.paginationjs li[data-typepage='Item']").removeClass("active");
                $("ul.paginationjs li[data-typepage='Item'][data-page='" + pNumPage + "']").addClass("active");
            }
            mostrarPaginacion(1);
            $("ul.paginationjs li").click(function () {
                if ($(this).attr("data-typepage") === "Item") {
                    var page = parseInt($(this).attr("data-page"));
                    if (isNaN(page)) {
                        page = 1;
                    }
                    mostrarPaginacion(page);
                } else {
                    var pageActivo = parseInt($("ul.paginationjs").attr("data-pageactive"));
                    if (isNaN(pageActivo)) {
                        pageActivo = 1;
                    }
                    var numPage = parseInt($("ul.paginationjs").attr("data-numpage"));
                    if (isNaN(numPage)) {
                        numPage = 1;
                    }
                    if ($(this).attr("data-typepage") === "Previous") {
                        if (pageActivo > 1) {
                            var page = pageActivo - 1;
                            mostrarPaginacion(page);
                        }
                    } else if ($(this).attr("data-typepage") === "Next") {
                        if (pageActivo < numPage) {
                            var page = pageActivo + 1;
                            mostrarPaginacion(page);
                        }
                    }
                }
            });
        }
    })();
</script>
<%}%>