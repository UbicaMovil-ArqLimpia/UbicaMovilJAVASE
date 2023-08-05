<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if (strTop_aux != null && strTop_aux.trim().length() > 0) {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<select id="sltop_aux" name="top_aux">
    <option <%=(top_aux == 10) ? "selected" : ""%>  value="10">10</option>
    <option <%=(top_aux == 20) ? "selected" : ""%>  value="20">20</option>
    <option <%=(top_aux == 50) ? "selected" : ""%>  value="50">50</option>
    <option <%=(top_aux == 100) ? "selected" : ""%>  value="100">100</option>
    <option <%=(top_aux == 500) ? "selected" : ""%>  value="500">500</option>
    <option <%=(top_aux == 1000) ? "selected" : ""%>  value="1000">1000</option>
    <option <%=(top_aux == 10000) ? "selected" : ""%>  value="10000">10000</option>
    <option <%=(top_aux == 0) ? "selected" : ""%>  value="0">Todos</option>
</select>
<label for="sltop_aux">Top</label>