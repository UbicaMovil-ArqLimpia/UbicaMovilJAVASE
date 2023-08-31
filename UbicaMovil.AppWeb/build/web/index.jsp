<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>UbicaMovil</title>
        <link rel="stylesheet"  href="${pageContext.request.contextPath}/wwwroot/css/site.css">
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container"> 
            <div>
                <div class="center-align">
                    <br>
                    <br>
                    <br>
                    <br>
                    <img src="<%= request.getContextPath() %>/wwwroot/img/logo.png" style="width:80px; height:80px;" />
                </div>
                <h2 class="center-align"><Strong>UbicaMovil</Strong></h2>
                <p class="center-align">
                    Empieza a buscando tus empresas con nuestro buscador
                </p>

                <!-- Contenedor del buscador -->
                <div class="row" id="search-container">
                    <input class="input-field col l10 s9" id="search-input" type="text" placeholder="Buscar">
                    <button class="btn green col l2 s3" type="button" id="search-button">
                        <i class="material-icons right">search</i>Buscar
                    </button>
                </div>

                <label>Modos de transportes</label>
                <select class="browser-default" id="mode-select">
                    <!--<option value="" disabled selected>Choose your option</option>-->
                    <option value="DRIVING" selected>Automóvil</option>
                    <option value="WALKING">Caminando</option>
                </select>

                <div>
                    <br>
                    <div id="map"></div>
                </div>
                    <br>

                <!--<div class="card text-bg-dark mb-3">-->
                <div class="card-title">
                    <h5 class="center">
                        Indicaciones para llegar a su destino
                    </h5>
                </div>
                <div class="card-body" >
                    <div id="directions-panel" class="hidden"></div>
                </div>
            </div>
                    <br>
                    <br>
            <!--</div>-->
        </main>

        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCum27trvMaz6TpLQlZzF7521lkn-4EPt0&callback=initMap&libraries=places,directions"></script>       
        <script src="<%= request.getContextPath() %>/wwwroot/js/site.js"></script>

    </body>
</html>