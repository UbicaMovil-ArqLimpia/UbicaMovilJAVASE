// Declaración de variables globales para el geocodificador y el mapa
var geocoder;
var map;

// Función de inicialización del mapa
function initialize() {
    // Obtenemos las latitud y longitud iniciales desde los elementos HTML
    var latitud = parseFloat(document.getElementById('latitud').value);
    var longitud = parseFloat(document.getElementById('longitud').value);

    // Creamos una instancia del geocodificador
    geocoder = new google.maps.Geocoder();
    // Creamos un objeto LatLng con las coordenadas iniciales
    var latlng = new google.maps.LatLng(latitud, longitud);

    // Opciones para la configuración del mapa
    var mapOptions = {
        zoom: 15,            // Nivel de zoom
        center: latlng       // Centro del mapa en las coordenadas iniciales
    };

    // Creamos una instancia del mapa y la asociamos al elemento con ID 'map'
    map = new google.maps.Map(document.getElementById('map'), mapOptions);

    // Creamos un marcador en las coordenadas iniciales
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: 'Ubicación Inicial'  // Título del marcador
    });
}

// Función para mostrar marcadores en varias ubicaciones
function codeAddresses() {
    // Matriz de objetos con información de ubicaciones
    var addresses = [
        { lat: 40.712776, lng: -74.005974, name: 'Nueva York' },
        { lat: 34.052235, lng: -118.243683, name: 'Los Ángeles' },
        { lat: 51.5074, lng: -0.1278, name: 'Londres' }
        // Agrega más objetos aquí con latitudes, longitudes y nombres
    ];

    // Recorremos la matriz de direcciones
    for (var i = 0; i < addresses.length; i++) {
        var addressData = addresses[i];
        // Creamos un objeto LatLng con las coordenadas de la dirección actual
        var latlng = new google.maps.LatLng(addressData.lat, addressData.lng);

        // Creamos un marcador en la ubicación actual
        var marker = new google.maps.Marker({
            position: latlng,
            map: map,
            title: addressData.name  // Título del marcador
        });

        // Creamos una ventana de información para el marcador actual
        var infoWindow = new google.maps.InfoWindow({
            content: '<strong>' + addressData.name + '</strong>'  // Contenido de la ventana
        });

        // Agregamos un listener de clic al marcador para mostrar la ventana de información
        marker.addListener('click', function () {
            infoWindow.open(map, marker);
        });
    }
}
