<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ubicamovil.entidadesdenegocio.Anuncio"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/Views/Shared/title.jsp" />
    <title>Crear anuncio</title>
</head>
<body>
    <jsp:include page="/Views/Shared/headerBody.jsp" />
    <main class="container">
        <h5>Crear anuncio</h5>
        <form class="center-align" action="AnuncioServlet" method="post">
            <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>">
            <div class="row">
                <div class="input-field col l4 s12">
                    <input id="txtTitulo" type="text" name="titulo" required class="validate" maxlength="100">
                    <label for="txtTitulo">Título</label>
                </div>
            </div>
            <!-- Agrega aquí los campos adicionales del formulario -->
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
