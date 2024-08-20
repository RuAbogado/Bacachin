<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil</title>
    <link rel="icon" type="image/png" href="img/icons/icono.png" />
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="container contenedor-perfil">
                <div class="close-icon" onclick="window.location.href='homeadmin.jsp';">✖️</div>
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div>
                            <p><strong>Nombres:</strong> <span id="nombre"></span></p>
                            <p><strong>Apellidos:</strong> <span id="apellido"></span></p>
                            <p><strong>Nombre usuario:</strong> <span id="nombre_usuario"></span></p>
                            <p><strong>Teléfono:</strong> <span id="telefono"></span></p>
                            <p><strong>E-mail:</strong> <span id="email-usuario"></span></p>
                            <p><strong>Estado:</strong> <span id="estado-usuario"></span></p>
                        </div>

                    </div>
            </div>
        </div>
    </div>
</div>

<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script>
    const cargarDatosUsuario = () => {
        function cargarDatosUsuario(usuarioId) {
            fetch(`GIUP_war/getUsuario?ID_Usuario=${usuarioId}`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById("Nombre").textContent = data.Nombre;
                    document.getElementById("Apellido").textContent = data.Apellido;
                    document.getElementById("Nombre_Usuario").textContent = data.Nombre_Usuario;
                    document.getElementById("Telefono").textContent = data.Telefono;
                    document.getElementById("Correo").textContent = data.Correo;
                    document.getElementById("Estado").textContent = data.Estado;
                })
                .catch(error => console.error('Error:', error));
        }
        ;

</script>
</body>

</html>
