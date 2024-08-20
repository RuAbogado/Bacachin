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
                            <span>Nombres: </span><span id="nombre" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Apellidos: </span><span id="apellido" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Nombre usuario: </span><span id="nombre_usuario" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Teléfono: </span><span id="telefono" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>E-mail: </span><span id="email-usuario" class="font-weight-bold"></span>
                        </div>
                        <p class="estado mt-3">Estado: <span id="estado-usuario"></span></p>
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
        fetch(`GIUP_war/getUsuario?ID_usuario=${usuarioId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al cargar los datos del usuario');
                }
                return response.json();
            })
            .then(data => {
                document.getElementById("nombre").textContent = data.nombre;
                document.getElementById("apellido").textContent = data.apellido;
                document.getElementById("nombre_usuario").textContent = data.nombre_usuario;
                document.getElementById("telefono").textContent = data.telefono;
                document.getElementById("email-usuario").textContent = data.correo;
                document.getElementById("estado-usuario").textContent = data.estado; // Estado del usuario
            })
            .catch(error => {
                console.error('Error al cargar los datos del usuario:', error);
                alert('Error al cargar los datos del usuario. Por favor, inténtalo de nuevo más tarde.');
            });
    };

</script>
</body>

</html>
