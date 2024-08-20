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

<script>
    function Estatus(value, ID_Solicitud) {
        switch(value) {
            case 'Cancelar':
                console.log("Solicitud " + ID_Solicitud + " cancelada.");
                break;
            case 'Proceso':
                console.log("Solicitud " + ID_Solicitud + " en proceso.");
                break;
            case 'Terminada':
                console.log("Solicitud " + ID_Solicitud + " terminada.");
                break;
            default:
                console.log("Ningún estado seleccionado para la solicitud " + ID_Solicitud + ".");
                break;
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
        // Realiza una petición AJAX para obtener los detalles de la venta
        fetch('/GIUP_war/DetalleVenta?ID_Solicitud=' + ID_Solicitud)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.text();
            })
            .then(data => {
                // Aquí puedes mostrar los detalles en un modal o en un div
                alert('Detalles de la venta:\n' + data);
            })
            .catch(error => console.error('Error:', error));
    }
</script>
</body>
</html>