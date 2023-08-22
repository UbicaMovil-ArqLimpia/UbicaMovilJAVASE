<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <form action="Anuncio" method="post">
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">   
            <input type="hidden" name="id" value="<%=anuncio.getId()%>">   
            <div class="row">
                <div class="input-field col l4 s12">
                    <input disabled  id="txtTitulo" type="text" value="<%=anuncio.getTitulo()%>" disabled>
                    <label for="txtTitulo">Título</label>
                </div>                                        
            </div>
            <div class="row">
                <div class="col l12 s12">
                    <button type="submit" class="waves-effect waves-light btn red"><i class="material-icons right">delete</i>Eliminar</button>
                    <a href="Anuncio" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                </div>
            </div>
        </form>          
    </main>
    <jsp:include page="/Views/Shared/footerBody.jsp" />      
</body>
</html>
