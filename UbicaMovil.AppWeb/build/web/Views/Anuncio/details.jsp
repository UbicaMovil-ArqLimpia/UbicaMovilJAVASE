<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <input disabled  id="txtTitulo" type="text" value="<%=anuncio.getTitulo()%>" disabled="disabled">
                <label for="txtTitulo">Título</label>
            </div>                                         
        </div>
        <div class="row">
            <div class="col l12 s12">
                <a href="Anuncio?accion=edit&id=<%=anuncio.getId()%>" class="waves-effect waves-light btn blue"><i class="material-icons right">edit</i>Ir modificar</a>                        
                <a href="Anuncio" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
            </div>
        </div>         
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />      
</body>
</html>
