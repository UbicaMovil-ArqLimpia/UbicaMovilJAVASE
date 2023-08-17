/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


/* global google, locations */

var map;
var myLocationMarker;
var searchMarker;
var searchBox;
var directionsService;
var directionsRenderer;
var infoWindow;


function initMap() {
    map = new google.maps.Map(document.getElementById('map'), {
        center: { lat: 0, lng: 0 }, 
        zoom: 15 
    });
    for (var i = 0; i < locations.length; i++) {
        var marker = new google.maps.Marker({
            position: locations[i].location,
            map: map
        });

        var infoWindow = new google.maps.InfoWindow({
            content: "<h3>" + locations[i].name + "</h3><p>" + locations[i].hours + "</p>"
        });

        marker.addListener('click', function () {
            infoWindow.open(map, this);
        });
    }
    // Inicializar el marcador para la ubicación actual
    myLocationMarker = new google.maps.Marker({
        map: map,
        draggable: false, // Desactivar el arrastre del marcador
        animation: google.maps.Animation.DROP,
        position: { lat: 0, lng: 0 } // Ubicación inicial del marcador
    });

  
    // Obtener la ubicación actual del usuario
    if (navigator.geolocation) {
        updateMyLocation(); // Actualizar la ubicación inicial


    } else {
        // El navegador no soporta la geolocalización
        handleLocationError(false, map.getCenter());
    }

    // Obtener el campo de búsqueda
    var searchInput = document.getElementById('search-input');

    // Inicializar el autocompletado del campo de búsqueda
    searchBox = new google.maps.places.SearchBox(searchInput);

    // Obtener el combo box para seleccionar el modo de transporte
    var modeSelect = document.getElementById('mode-select');

    // Inicializar el servicio de direcciones
    directionsService = new google.maps.DirectionsService();
    directionsRenderer = new google.maps.DirectionsRenderer({
        map: map,
        panel: document.getElementById('directions-panel') // Panel para mostrar las indicaciones
    });

    // Escuchar el evento de cambio en el campo de búsqueda
    searchBox.addListener('places_changed', function () {
        search();
    });

    // Escuchar el evento de clic en el botón de búsqueda
    var searchButton = document.getElementById('search-button');
    searchButton.addEventListener('click', function () {
        search();
    });

    // Escuchar el evento de presionar Enter en el campo de búsqueda
    searchInput.addEventListener('keydown', function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            search();
        }
    });

    // Escuchar el evento de cambio en el combo box del modo de transporte
    modeSelect.addEventListener('change', function () {
        calculateRoute();
    });

    // Inicializar la ventana informativa
    infoWindow = new google.maps.InfoWindow();
}

// Función para actualizar la ubicación actual
function updateMyLocation() {
    navigator.geolocation.getCurrentPosition(function (position) {
        var pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
        };

        // Actualizar la posición del marcador de ubicación actual
        myLocationMarker.setPosition(pos);

        // Centrar el mapa en la ubicación actual
        map.setCenter(pos);
    }, function () {
        // Manejar errores al obtener la ubicación
        handleLocationError(true, map.getCenter());
    });
}

// Función para realizar una búsqueda
function search() {
    var places = searchBox.getPlaces();

    if (places.length === 0) {
        return;
    }

    // Reiniciar los marcadores y la ruta trazada
    if (searchMarker) {
        searchMarker.setMap(null);
    }
    directionsRenderer.setDirections({ routes: [] });

    // Obtener la ubicación seleccionada del buscador
    var selectedPlace = places[0].geometry.location;

    // Crear un marcador para la ubicación seleccionada
    searchMarker = new google.maps.Marker({
        map: map,
        draggable: true,
        animation: google.maps.Animation.DROP,
        position: selectedPlace
    });

    // Escuchar el evento de clic en el marcador de destino
    searchMarker.addListener('click', function () {
        openInfoWindow();
    });

    // Abrir la ventana informativa al seleccionar una ubicación
    openInfoWindow();
}

// Función para abrir una ventana informativa al hacer clic en el marcador de destino
function openInfoWindow() {
    // Obtener los campos de la tabla y el botón "Como llegar"
    var infoContent = '<table>' +
        '<tr><td>Campo 1:</td><td>Valor 1</td></tr>' +
        '<tr><td>Campo 2:</td><td>Valor 2</td></tr>' +
        '<tr><td>Campo 3:</td><td>Valor 3</td></tr>' +
        '</table>' +
        '<button onclick="showDirections()">Como llegar</button>';

    // Establecer el contenido de la ventana informativa
    infoWindow.setContent(infoContent);

    // Mostrar la ventana informativa en el mapa, en la posición del marcador de destino
    infoWindow.open(map, searchMarker);
}

// Función para mostrar las indicaciones y la ruta trazada
function showDirections() {
    // Mostrar las indicaciones y el tiempo estimado de llegada
    var directionsPanel = document.getElementById('directions-panel');
    directionsPanel.classList.remove('hidden');

    calculateRoute();
}

// Función para calcular la ruta desde la ubicación actual hasta la ubicación seleccionada
function calculateRoute() {
    // Obtener la ubicación actual del marcador de ubicación actual
    var origin = myLocationMarker.getPosition();

    // Obtener la ubicación del marcador de destino
    var destination = searchMarker.getPosition();

    // Obtener el modo de transporte seleccionado del combo box
    var modeSelect = document.getElementById('mode-select');
    var selectedMode = modeSelect.value;

    // Configurar los parámetros de la solicitud de ruta
    var request = {
        origin: origin,
        destination: destination,
        travelMode: selectedMode // Modo de transporte seleccionado
    };

    // Enviar la solicitud de ruta al servicio de direcciones
    directionsService.route(request, function (result, status) {
        if (status === 'OK') {
            // Mostrar la ruta en el mapa
            directionsRenderer.setDirections(result);

            // Obtener el tiempo estimado de llegada
            var duration = result.routes[0].legs[0].duration.text;

            // Mostrar las indicaciones y el tiempo estimado de llegada
            var directionsPanel = document.getElementById('directions-panel');
            directionsPanel.innerHTML = '<strong>Tiempo estimado de llegada:</strong> ' + duration;
        } else {
            // Mostrar un mensaje de error si no se puede calcular la ruta
            window.alert('No se pudo calcular la ruta: ' + status);
        }
    });
}

function handleLocationError(browserHasGeolocation, pos) {
    var infoWindow = new google.maps.InfoWindow({
        content: browserHasGeolocation ?
            'Error: El servicio de geolocalización falló.' :
            'Error: Tu navegador no soporta la geolocalización.'
    });

    infoWindow.setPosition(pos);
    infoWindow.open(map);
}