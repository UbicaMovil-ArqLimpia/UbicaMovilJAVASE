<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Importar las clases necesarias --%>
<%@page import="ubicamovil.entidadesdenegocio.Anuncio"%>
<% Anuncio anuncio = (Anuncio) request.getAttribute("anuncio"); %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Detalle de anuncio</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />  
    <main class="container">   
        <h5>Detalle de anuncio</h5>
        <div class="row">
            <div class="input-field col l4 s12">
                <input disabled id="txtNombre" type="text" value="<%=anuncio.getNombre()%>" disabled="disabled">
                <label for="txtNombre">Nombre</label>
            </div>  
            <div class="input-field col l4 s12">
                <input disabled id="txtDescripcion" type="text" value="<%=anuncio.getDescripcion()%>" disabled="disabled">
                <label for="txtDescripcion">Descripción</label>
            </div>  
            <div class="input-field col l4 s12">
                <input disabled id="txtFechaInicio" type="text" value="<%=anuncio.getFechaInicio()%>" disabled="disabled">
                <label for="txtFechaInicio">Fecha de Inicio</label>
            </div>
            <div class="input-field col l4 s12">
                <input disabled id="txtFechaFin" type="text" value="<%=anuncio.getFechaFin()%>" disabled="disabled">
                <label for="txtFechaFin">Fecha de Fin</label>
            </div>
            <div class="input-field col l4 s12">
                <input disabled id="txtIdEmpresa" type="text" value="<%=anuncio.getIdEmpresa()%>" disabled="disabled">
                <label for="txtIdEmpresa">ID de Empresa</label>
            </div>                                          
        </div>
        <div class="row">
            <div class="col l12 s12">
                <a href="AnuncioServlet?accion=edit&id=<%=anuncio.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                <a href="AnuncioServlet" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
            </div>
        </div>         
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />      
</body>
</html>
