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
                            <span>Nombres: </span><span id="Nombre" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Apellidos: </span><span id="Apellido" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Nombre usuario: </span><span id="Nombre_Usuario" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Teléfono: </span><span id="Telefono" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>E-mail: </span><span id="Correo" class="font-weight-bold"></span>
                        </div>
                        <p class="estado mt-3">Estado: <span id="Estado"></span></p>

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
