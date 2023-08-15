<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ubicamovil.entidadesdenegocio.Empresa"%>
<% Empresa empresa = (Empresa) request.getAttribute("empresa");%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar empresa</title>
    </head>
    <body onload="initialize()">
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container">
            <br>
            <br>
            <br>
            <h4><strong>Editar empresa</strong></h4>
            <form action="Empresa" method="post" onsubmit="return  validarFormulario()">
                <input type="hidden" name="accion" value="<%=request.getAttribute("accion")%>"> 
                <input type="hidden" name="id" value="<%=empresa.getId()%>">  
                <div class="row">
                    <div class="input-field col l8 s12">
                        <input  id="txtNombre" type="text" name="nombre" value="<%=empresa.getNombre()%>" required class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>
                        <div class="input-field col l4 s12">   
                        <jsp:include page="/Views/Categoria/select.jsp">                           
                            <jsp:param name="id" value="<%=empresa.getIdCategoria() %>" />  
                        </jsp:include>  
                        <span id="slCategoria_error" style="color:red" class="helper-text"></span>
                    </div>
                    <div class="input-field col l8 s12">
                        <input  id="txtDireccion" type="text" name="direccion" value="<%=empresa.getDireccion()%>" required class="validate" maxlength="100">
                        <label for="txtDireccion">Direccion</label>
                    </div> 
                    <div class="input-field col l4 s12">
                        <input  id="txtTelefono" type="text" name="telefono" value="<%=empresa.getTelefono()%>" required class="validate" maxlength="8">
                        <label for="txtTelefono">Telefono</label>
                    </div> 
                    <div class="input-field col l3 s12">
                        <input  id="txtHorarioEntrada" type="time" name="horarioEntrada" value="<%=empresa.getHorarioEntrada()%>" required class="validate">
                        <label for="txtHorarioEntrada">Hora de apertura</label>
                    </div> 
                    <div class="input-field col l3 s12">
                        <input  id="txtHorarioSalida" type="time" name="horarioSalida" value="<%=empresa.getHorarioSalida()%>" required class="validate">
                        <label for="txtHorarioSalida">Hora de cierre</label>
                    </div>
                    <div class="input-field col l3 s12">
                        <input id="latitud" type="text" name="latitud" value="<%=empresa.getLatitud()%>">
                        <label for="latitud">Latitud</label>
                    </div> 
                    <div class="input-field col l3 s12">
                        <input id="longitud" type="text" name="longitud" value="<%=empresa.getLongitud()%>">
                        <label for="longitud">Longitud</label>
                    </div>
                        <div id="map" style="width: 100%; height: 480px;"></div>
                </div>
                        <div class="row center">
                    <div class="col l12 s12">
                        <button type="sutmit" class="waves-effect waves-light btn blue"><i class="material-icons right">save</i>Guardar</button>
                        <a href="Empresa" class="waves-effect waves-light btn blue"><i class="material-icons right">list</i>Cancelar</a>                          
                    </div>
                </div>
            </form>  
                        </main>
        
        <jsp:include page="/Views/Shared/footerBody.jsp" />   
        <script>
            function validarFormulario() {
                var result = true;
                var slCategoria = document.getElementById("slCategoria");
                var slCategoria_error = document.getElementById("slCategoria_error");
                if (slCategoria.value == 0) {
                    slCategoria_error.innerHTML = "La Categoria es obligatorio";
                    result = false;
                } else {
                    slCategoria_error.innerHTML = "";
                }

                return result;
            }
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCum27trvMaz6TpLQlZzF7521lkn-4EPt0&libraries=places&callback=inicializarMapa" async defer></script>
        <script src="<%= request.getContextPath() %>/wwwroot/js/emp1.js"></script>
    </body>
</html>