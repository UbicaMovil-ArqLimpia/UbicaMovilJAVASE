<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <form action="Anuncio" method="post">
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
            <input type="hidden" name="id" value="<%=anuncio.getId()%>">   
            <div class="row">
                <div class="input-field col l4 s12">
                    <input  id="txtTitulo" type="text" name="titulo" value="<%=anuncio.getTitulo()%>" required class="validate" maxlength="100">
                    <label for="txtTitulo">Título</label>
                </div>                                       
            </div>
            <!-- Otros campos de edición específicos del anuncio pueden agregarse aquí -->
            <div class="row">
                <div class="col l12 s12">
                    <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                    <a href="Anuncio" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>
        </form>          
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />      
</body>
</html>
