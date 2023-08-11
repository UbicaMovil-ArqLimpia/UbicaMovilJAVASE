var geocoder;
var map;
var markers = []; // Array para almacenar los marcadores

function initialize() {
    var latitud = parseFloat(document.getElementById('latitud').value);
    var longitud = parseFloat(document.getElementById('longitud').value);
    
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(latitud, longitud);
    
    var mapOptions = {
        zoom: 15,
        center: latlng
    };
    
    map = new google.maps.Map(document.getElementById('map'), mapOptions);
    
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: 'Ubicación'
    });
    markers.push(marker); // Agregar el marcador al array
}

function codeAddresses() {
    var addresses = ['Dirección 1', 'Dirección 2', 'Dirección 3']; // Array de direcciones

    for (var i = 0; i < addresses.length; i++) {
        geocodeAddress(addresses[i]);
    }
}

function geocodeAddress(address) {
    geocoder.geocode({ 'address': address }, function(results, status) {
        if (status === 'OK') {
            var marker = new google.maps.Marker({
                position: results[0].geometry.location,
                map: map,
                title: address
            });
            markers.push(marker); // Agregar el marcador al array
        } else {
            console.log('La geocodificación no tuvo éxito para ' + address + ' por la siguiente razón: ' + status);
        }
    });
}