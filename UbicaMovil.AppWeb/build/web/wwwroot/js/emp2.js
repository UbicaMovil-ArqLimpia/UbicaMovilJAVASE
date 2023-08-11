var geocoder;
var map;
var marker;

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
    
    marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: 'Arrastra este marcador',
        draggable: false
    });

    // Evento para obtener las coordenadas cuando se mueve el marcador
    google.maps.event.addListener(marker, 'dragend', function() {
        var newPosition = marker.getPosition();
        document.getElementById('latitud').value = newPosition.lat();
        document.getElementById('longitud').value = newPosition.lng();
    });
}

function codeAddress() {
    var address = document.getElementById('address').value;
    
    geocoder.geocode({ 'address': address }, function(results, status) {
        if (status == 'OK') {
            var newPosition = results[0].geometry.location;
            
            // Mueve el marcador a la nueva posición
            marker.setPosition(newPosition);
            
            // Centra el mapa en la nueva posición
            map.setCenter(newPosition);

            // Actualiza las coordenadas
            document.getElementById('latitud').value = newPosition.lat();
            document.getElementById('longitud').value = newPosition.lng();
        } else {
            alert('La geocodificación no tuvo éxito por la siguiente razón: ' + status);
        }
    });
}