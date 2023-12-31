<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ubicamovil.entidadesdenegocio.Anuncio"%>
<%@page import="java.util.ArrayList"%>
<% 
ArrayList<Anuncio> anuncios = (ArrayList<Anuncio>) request.getAttribute("anuncios");
int numPage = 1;
int numReg = 10;
int countReg = 0;
if (anuncios == null) {
    anuncios = new ArrayList();
} else if (anuncios.size() > numReg) {
    double divNumPage = (double) anuncios.size() / (double) numReg;
    numPage = (int) Math.ceil(divNumPage);
}
String strTop_aux = request.getParameter("top_aux");
int top_aux = 5;
if (strTop_aux != null && strTop_aux.trim().length() > 0) {
    top_aux = Integer.parseInt(strTop_aux);
}
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Buscar anuncio</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />  
    <main class="container">   
        <br>
        <br>
        <br>
        <br>
        <h4 class="center-align deep-purple-text text-darken-4"><strong>ANUNCIOS</strong></h4>
        <form action="AnuncioServlet" method="post"> <%-- Asegúrate de que la acción y el método sean correctos --%>
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
            <div class="row">
                <div class="input-field col l8 s12">
                    <input  id="txtTitulo" type="text" name="titulo">
                    <label for="txtTitulo">Título</label>
                </div>
                <div class="input-field col l4 s12">   
                    <jsp:include page="/Views/Shared/selectTop.jsp">
                        <jsp:param name="top_aux" value="<%=top_aux%>" />                        
                    </jsp:include>                        
                </div>
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <button type="submit" class="waves-effect waves-light btn purple"><i class="material-icons right">search</i>Buscar</button>
                    <a href="AnuncioServlet?accion=create" class="waves-effect waves-light btn green"><i class="material-icons right">add</i>Crear</a>                          
                </div>
            </div>
        </form>

        <div class="row">
            <div class="col l12 s12">
                <div style="overflow: auto">
                    <table class="paginationjs centered responsive-table striped purple lighten-4">
                        <thead>
                            <tr>
                                <th>Título</th>                                          
                                <th>Acciones</th>
                            </tr>
                        </thead>                       
                        <tbody>                           
                            <% for (Anuncio anuncio : anuncios) {
                                    int tempNumPage = numPage;
                                    if (numPage > 1) {
                                        countReg++;
                                        double divTempNumPage = (double) countReg / (double) numReg;
                                        tempNumPage = (int) Math.ceil(divTempNumPage);
                                    }
                            %>
                            <tr data-page="<%= tempNumPage%>">
                                <td><%=anuncio.getTitulo()%></td>                                       
                                <td>
                                    <div style="display:flex">
                                        <a href="AnuncioServlet?accion=edit&id=<%=anuncio.getId()%>" title="Modificar" class="waves-effect waves-light btn green">
                                            <i class="material-icons">edit</i>
                                        </a>
                                        <a href="AnuncioServlet?accion=details&id=<%=anuncio.getId()%>" title="Ver" class="waves-effect waves-light btn blue">
                                            <i class="material-icons">description</i>
                                        </a>
                                        <a href="AnuncioServlet?accion=delete&id=<%=anuncio.getId()%>" title="Eliminar" class="waves-effect waves-light btn red">
                                            <i class="material-icons">delete</i>
                                        </a>     
                                    </div>
                                </td>                                   
                            </tr>
                            <%}%>                                                       
                        </tbody>
                    </table>
                </div>                  
            </div>
        </div>
        <div class="row">
            <div class="col l12 s12">
                <jsp:include page="/Views/Shared/paginacion.jsp">
                    <jsp:param name="numPage" value="<%= numPage%>" />                        
                </jsp:include>
            </div>
        </div>
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />        
</body>
</html>
