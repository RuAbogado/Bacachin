<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de persona</title>
    <link rel="icon" type="image/png" href="images/icons/agregar.png"/>
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link href="css/formulario.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: url('img/cafetería.webp') no-repeat center center fixed;
            background-size: cover;
        }
    </style>
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">

            <form id="registroForm" method="post" action="Register">
                <img src="" alt="Imagen" class="circle-img">
                <div class="wrap-input100">
                    <label class="label-input100" for="nombre">Nombre:</label>
                    <input class="input100" type="text" id="nombre" name="nombre" placeholder="Escriba su nombre" required>
                </div>

                <div class="wrap-input100">
                    <label class="label-input100" for="apellido">Apellidos:</label>
                    <input class="input100" type="text" id="apellido" name="apellido" placeholder="Escriba su apellido" required>
                </div>

                <div class="wrap-input100">
                    <label class="label-input100" for="usuario">Nombre de usuario:</label>
                    <input class="input100" type="text" id="usuario" name="usuario" placeholder="Escriba su nombre de usuario" required>
                </div>

                <div class="wrap-input100">
                    <label class="label-input100" for="telefono">Teléfono:</label>
                    <input class="input100" type="tel" id="telefono" name="telefono" placeholder="1234567890" required>
                </div>

                <div class="wrap-input100">
                    <label class="label-input100" for="correo">Correo:</label>
                    <input class="input100" type="email" id="correo" name="correo" placeholder="correo@ejemplo.com" required>
                </div>

                <div class="wrap-input100">
                    <label class="label-input100" for="contraseña">Contraseña:</label>
                    <input class="input100" type="password" id="contraseña" name="contraseña" placeholder="Escriba su contraseña" required minlength="5">
                </div>

                <div class="wrap-input100">
                    <label class="label-input100" for="confirmarContraseña">Confirmar Contraseña:</label>
                    <input class="input100" type="password" id="confirmarContraseña" name="confirmarContraseña" placeholder="Repita su contraseña" required minlength="5">
                </div>

                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit">Registrar usuario</button>
                    <button class="login100-form-btn" type="button" onclick="borrarRegistro()">Borrar registro</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function validarYEnviar() {
        var nombre = document.getElementById("nombre").value.trim();
        var apellido = document.getElementById("apellido").value.trim();
        var usuario = document.getElementById("usuario").value.trim();
        var telefono = document.getElementById("telefono").value.trim();
        var correo = document.getElementById("correo").value.trim();
        var contraseña = document.getElementById("contraseña").value;
        var confirmarContraseña = document.getElementById("confirmarContraseña").value;

        // Validar si algún campo está vacío
        if (nombre === "" || apellido === "" || usuario === "" || telefono === "" || correo === "" || contraseña === "" || confirmarContraseña === "") {
            alert("Por favor, rellene todos los campos.");
            return false; // Detener el envío del formulario si algún campo está vacío
        }

        // Validar si hay espacios en blanco en cualquier campo
        if (nombre.includes(" ") || telefono.includes(" ") || correo.includes(" ")) {
            alert("No se permiten espacios en blanco en ningún campo.");
            return false; // Detener el envío del formulario si se encuentra un espacio en blanco
        }

        // Validar si las contraseñas coinciden
        if (contraseña !== confirmarContraseña) {
            alert("Las contraseñas no coinciden");
            return false; // Detener el envío del formulario si las contraseñas no coinciden
        }

        // Si pasa todas las validaciones, enviar el formulario
        localStorage.setItem("nombre", nombre);
        localStorage.setItem("apellido", apellido);
        localStorage.setItem("usuario", usuario);
        localStorage.setItem("telefono", telefono);
        localStorage.setItem("correo", correo);
        localStorage.setItem("contraseña", contraseña);

        return true;
    }

    function borrarRegistro() {
        var confirmacion = confirm("¿Estás seguro de que deseas vaciar todos los registros? Esta acción no se puede deshacer.");

        if (confirmacion) {
            localStorage.clear();
            location.reload();
        }
    }


</script>

</body>
</html>
