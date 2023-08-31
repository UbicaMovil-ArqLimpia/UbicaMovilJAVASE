<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ubicamovil.entidadesdenegocio.Anuncio"%>
<%@page import="ubicamovil.accesoadatos.AnuncioDAL"%>
<%@page import="java.util.ArrayList"%>
<% 
ArrayList<Anuncio> anuncios = AnuncioDAL.getAll();
int id = (request.getParameter("id") != null) ? Integer.parseInt(request.getParameter("id")) : 0;
%>
<select id="slAnuncio" name="idAnuncio">
    <option <%=(id == 0) ? "selected" : ""%>  value="0">SELECCIONAR</option>
    <% for (Anuncio anuncio : anuncios) {%>
    <option <%=(id == anuncio.getId()) ? "selected" : ""%>  value="<%=anuncio.getId()%>"><%= anuncio.getNombre()%></option>
    <%}%>
</select>
<label for="idAnuncio">Anuncio</label>
