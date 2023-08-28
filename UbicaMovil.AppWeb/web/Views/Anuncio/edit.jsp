<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- Importar las clases necesarias --%>
<%@page import="ubicamovil.entidadesdenegocio.Anuncio"%>
<% Anuncio anuncio = (Anuncio) request.getAttribute("anuncio"); %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Editar anuncio</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />  
    <main class="container">   
        <h5>Editar anuncio</h5>
        <form action="AnuncioServlet" method="post">
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
            <input type="hidden" name="id" value="<%=anuncio.getId()%>">   
            <div class="row">
                <div class="input-field col l4 s12">
                    <input id="txtNombre" type="text" name="nombre" value="<%=anuncio.getNombre()%>" required class="validate" maxlength="100">
                    <label for="txtNombre">Nombre</label>
                </div>  
                <div class="input-field col l4 s12">
                    <input id="txtDescripcion" type="text" name="descripcion" value="<%=anuncio.getDescripcion()%>" required class="validate" maxlength="100">
                    <label for="txtDescripcion">Descripción</label>
                </div>
                <div class="input-field col l4 s12">
                    <input id="txtFechaInicio" type="text" name="fechaInicio" value="<%=anuncio.getFechaInicio()%>" required class="validate" maxlength="8">
                    <label for="txtFechaInicio">Fecha de Inicio (YYYYMMDD)</label>
                </div>
                <div class="input-field col l4 s12">
                    <input id="txtFechaFin" type="text" name="fechaFin" value="<%=anuncio.getFechaFin()%>" required class="validate" maxlength="8">
                    <label for="txtFechaFin">Fecha de Fin (YYYYMMDD)</label>
                </div>
                <div class="input-field col l4 s12">
                    <input id="txtIdEmpresa" type="text" name="idEmpresa" value="<%=anuncio.getIdEmpresa()%>" required class="validate">
                    <label for="txtIdEmpresa">ID de Empresa</label>
                </div>                                        
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <button type="submit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                    <a href="AnuncioServlet" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>
        </form>          
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />      
</body>
</html>
