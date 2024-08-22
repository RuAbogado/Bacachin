<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Solicitudes Empleados</title>
    <link href="css/clientes.css" rel="stylesheet">
    <link href="css/productos.css" rel="stylesheet">
</head>
<body>
<h2 class="Categorias">Solicitudes</h2>

<!-- Contenedor para la tabla de solicitudes -->
<div id="clientesContainer">
    <!-- Incluye el contenido generado por el servlet ListarSolicitudesEmpleados -->
    <jsp:include page="/ListarSolicitudesEmpleados" />
</div>

<!-- Modal para mostrar detalles de la venta -->
<div id="detalleModal" style="display:none;">
    <div id="modalContent"></div>
    <button onclick="cerrarModal()">Cerrar</button>
</div>

<script>
    function Estatus(value, ID_Solicitud) {
        const estados = {
            'Cancelar': "cancelada",
            'Proceso': "en proceso",
            'Terminada': "terminada"
        };

        if (estados[value]) {
            console.log(`Solicitud ${ID_Solicitud} ${estados[value]}.`);
        } else {
            console.log(`Ningún estado seleccionado para la solicitud ${ID_Solicitud}.`);
        }

        // Enviar la actualización del estado al servidor
        fetch('/GIUP_war/ActualizarEstadoSolicitud', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'ID_Solicitud=' + ID_Solicitud + '&estado=' + encodeURIComponent(value)
        })
            .then(response => response.text())
            .then(data => {
                console.log('Estado actualizado: ' + data);
            })
            .catch(error => console.error('Error:', error));
    }

    function mostrarDetalleVenta(ID_Solicitud) {
        fetch('/GIUP_war/DetalleVenta?ID_Solicitud=' + ID_Solicitud)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.text();
            })
            .then(data => {
                // Mostrar detalles en un modal
                document.getElementById('modalContent').innerHTML = data;
                document.getElementById('detalleModal').style.display = 'block';
            })
            .catch(error => console.error('Error:', error));
    }

    function cerrarModal() {
        document.getElementById('detalleModal').style.display = 'none';
    }
</script>
</body>
</html>
