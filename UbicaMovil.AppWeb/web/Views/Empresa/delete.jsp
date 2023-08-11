<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ubicamovil.entidadesdenegocio.Empresa"%>
<% Empresa empresa = (Empresa) request.getAttribute("empresa");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar empresa</title>
    </head>
    <body onload="initialize()">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">   
            <h5>Eliminar empresa</h5>
            <form action="Empresa" method="post">  
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=empresa.getId()%>">  
                <div class="row">
                    <div class="input-field col l8 s12">
                        <input  id="txtNombre" type="text" value="<%=empresa.getNombre()%>" disabled>
                        <label for="txtNombre">Nombre</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input id="txtCategoria" type="text" value="<%=empresa.getCategoria().getNombre()%>" disabled>
                        <label for="txtCategoria">Categoria</label>
                    </div> 
                    <div class="input-field col l8 s12">
                        <input  id="txtDireccion" type="text" value="<%=empresa.getDireccion()%>" disabled>
                        <label for="txtDireccion">Direccion</label>
                    </div>
                    <div class="input-field col l4 s12">
                        <input  id="txtTelefono" type="text" value="<%=empresa.getTelefono()%>" disabled>
                        <label for="txtTelefono">Telefono</label>
                    </div>
                    <div class="input-field col l3 s12">
                        <input  id="txtHorarioEntrada" type="time" value="<%=empresa.getHorarioEntrada()%>" disabled>
                        <label for="txtHorarioEntrada">Hora de apertura</label>
                    </div>
                    <div class="input-field col l3 s12">
                        <input  id="txtHorarioSalida" type="time" value="<%=empresa.getHorarioSalida()%>" disabled>
                        <label for="txtHorarioSalida">Hora de cierre</label>
                    </div>
                    <div class="input-field col l3 s12">
                        <input  id="latitud" type="text" value="<%=empresa.getLatitud()%>" disabled>
                        <label for="txtLatitud">Latitud</label>
                    </div>
                    <div class="input-field col l3 s12">
                        <input  id="longitud" type="text" value="<%=empresa.getLongitud()%>" disabled>
                        <label for="txtLongitud">Longitud</label>
                    </div>
                        <div id="map" style="width: 100%; height: 480px;"></div>
                </div>
                <div class="row">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">delete</i>Eliminar</button>
                        <a href="Empresa" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>          
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />      
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCum27trvMaz6TpLQlZzF7521lkn-4EPt0&libraries=places&callback=inicializarMapa" async defer></script>
        <script src="<%= request.getContextPath() %>/wwwroot/js/emp2.js"></script>   
    </body>
</html>