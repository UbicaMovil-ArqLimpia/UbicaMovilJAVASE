<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Importar las clases necesarias --%>
<%@page import="ubicamovil.entidadesdenegocio.Anuncio"%>
<% Anuncio anuncio = (Anuncio) request.getAttribute("anuncio"); %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Eliminar anuncio</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />  
    <main class="container">   
        <h5>Eliminar anuncio</h5>          
        <form action="AnuncioServlet" method="post"> <%-- Asegúrate de que la acción y el método sean correctos --%>
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
            <input type="hidden" name="id" value="<%=anuncio.getId()%>">   
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtNombre" type="text" value="<%=anuncio.getNombre()%>" disabled>
                    <label for="txtNombre">Nombre</label>
                </div>
                <div class="input-field col l4 s12">
                    <input disabled  id="txtDescripcion" type="text" value="<%=anuncio.getDescripcion()%>" disabled>
                    <label for="txtDescripcion">Descripción</label>
                </div>
                <div class="input-field col l4 s12">
                    <input disabled  id="txtFechaInicio" type="text" value="<%=anuncio.getFechaInicio()%>" disabled>
                    <label for="txtFechaInicio">Fecha de Inicio</label>
                </div>
                <div class="input-field col l4 s12">
                    <input disabled  id="txtFechaFin" type="text" value="<%=anuncio.getFechaFin()%>" disabled>
                    <label for="txtFechaFin">Fecha de Fin</label>
                </div>
                <div class="input-field col l4 s12">
                    <input disabled  id="txtIdEmpresa" type="text" value="<%=anuncio.getIdEmpresa()%>" disabled>
                    <label for="txtIdEmpresa">ID de Empresa</label>
                </div>                                        
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <button type="submit" class="waves-effect waves-light btn red"><i class="material-icons right">delete</i>Eliminar</button>
                    <a href="AnuncioServlet" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>
        </form>          
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />      
</body>
</html>
