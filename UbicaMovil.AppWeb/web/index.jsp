<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/Views/Shared/title.jsp" />
          <link href="mapa.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <title>UbicaMovil</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/wwwroot/materialize/css/mapa.css">
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />  
        <main class="container center-align"> 
            
            
             <div class="container">
    <div class="text-center pt-5 mt-2">
         <img src="<%= request.getContextPath() %>/wwwroot/img/logo.png" style="width:60px; height:60px;" />
    </div>
    <h2 class="text-center mt-2">
        <Strong>UbicaMovil</Strong>
    </h2>
    <p class="text-center fw-lighter fs-6">
        Empieza a buscando tus empresas con nuestro buscador
    </p>

    <!-- Contenedor del buscador -->
    <div class="input-group my-3" id="search-container">
        <input class="form-control" id="search-input" type="text" placeholder="Buscar">
        <button class="btn" style="background-color: #A0C334;" type="button" id="search-button">
            <i class="bi bi-search text-white"></i>
        </button>
    </div>

    <div class="row g-2 py-2">
        <!-- Combo box para seleccionar el modo de transporte -->
        <div class="col-md">
            <div class="form-floating">
                <select class="form-select" id="mode-select">
                    <option value="DRIVING">Automóvil</option>
                    <option value="WALKING">Caminando</option>
                </select>
                <label for="floatingSelectGrid">Modos de transportes</label>
            </div>
        </div>

        <!-- Combo box para seleccionar el modo de transporte -->
        <div class="col-md">
            <div class="form-floating">
                <select class="form-select" id="">
                    <option value="">Comunicaciones</option>
                    <option value="">Tecnologia</option>
                </select>
                <label for="floatingSelectGrid">Categoria de empresas</label>
            </div>
        </div>
    </div>

    <!-- Elemento contenedor del mapa -->
    <div class="py-3">
        <div id="map"></div>
    </div>

    <div class="card text-bg-dark mb-3">
        <div class="m-2 mt-4">
            <h5 class="text-center fw-lighter fs-5">
                Indicaciones para llegar a su destino
            </h5>
        </div>
        <div class="card-body" >
            <!-- Elemento contenedor de las indiaciones -->
            <div id="directions-panel" class="hidden"></div>
        </div>
    </div>
</div>
        
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCum27trvMaz6TpLQlZzF7521lkn-4EPt0&callback=initMap&libraries=places,directions"></script>       
 <script src="<%= request.getContextPath() %>/wwwroot/js/site.js"></script>
           
    </body>
</html>

