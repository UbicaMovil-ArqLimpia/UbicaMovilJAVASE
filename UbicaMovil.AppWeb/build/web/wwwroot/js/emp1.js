var geocoder;
var map;
var marker;

function initialize() {
    geocoder = new google.maps.Geocoder();

    var latitud = parseFloat(document.getElementById('latitud').value);
    var longitud = parseFloat(document.getElementById('longitud').value);

    if (isNaN(latitud) || isNaN(longitud)) {
        // Si no se proporcionaron latitud y longitud, intenta obtener la ubicación del usuario
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
                latitud = position.coords.latitude;
                longitud = position.coords.longitude;
                initializeMap(latitud, longitud);
            }, function() {
                // Manejo de errores si la geolocalización falla
                initializeMap(0, 0);
            });
        } else {
            // Manejo si la geolocalización no es compatible
            initializeMap(0, 0);
        }
    } else {
        initializeMap(latitud, longitud);
    }
}

function initializeMap(latitud, longitud) {
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
        draggable: true
    });

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
            marker.setPosition(newPosition);
            map.setCenter(newPosition);
            document.getElementById('latitud').value = newPosition.lat();
            document.getElementById('longitud').value = newPosition.lng();
        } else {
            alert('La geocodificación no tuvo éxito por la siguiente razón: ' + status);
        }
    });
}
