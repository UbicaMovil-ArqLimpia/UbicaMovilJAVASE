// JavaScript para manejar el mapa y la ubicación
let mapa;
let marcador;

function inicializarMapa() {
    mapa = new google.maps.Map(document.getElementById("mapa"), {
        center: { lat: 0, lng: 0 },
        zoom: 8,
    });

    // Obtener la ubicación del usuario
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (posicion) => {
                const ubicacionUsuario = {
                    lat: posicion.coords.latitude,
                    lng: posicion.coords.longitude,
                };
                mapa.setCenter(ubicacionUsuario);
                mapa.setZoom(14);

                // Colocar un marcador verde en la ubicación del usuario
                marcador = new google.maps.Marker({
                    position: ubicacionUsuario,
                    map: mapa,
                    title: "Tu ubicación",
                    draggable: true, // Permite arrastrar el marcador
                    icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png', // Icono verde
                });

                // Escuchar el evento de arrastrar el marcador
                marcador.addListener("dragend", () => {
                    actualizarCoordenadas(marcador.getPosition());
                });

                // Inicializar los campos de entrada con las coordenadas actuales del marcador
                actualizarCoordenadas(ubicacionUsuario);
            },
            () => {
                alert("No se pudo obtener la ubicación del usuario.");
            }
        );
    } else {
        alert("Tu navegador no soporta geolocalización.");
    }
}

// Actualiza los campos de entrada con las coordenadas dadas
function actualizarCoordenadas(posicion) {
    document.getElementById("latitud").value = posicion.lat();
    document.getElementById("longitud").value = posicion.lng();
}