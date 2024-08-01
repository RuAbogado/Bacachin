<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesión</title>
    <link rel="icon" type="image/png" href="img/icons/icono.png"/>
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/index.css">


</head>
<body>
<div class="limiter">
    <div class="contenedor-login100">
        <div class="envoltura-login100">
            <form id="formularioInicioSesion" class="formulario-login100 validate-form" action="Login" method="post">
                <img src="img/portada.png" alt="Imagen" class="imagen-circular">
                <div class="envoltura-input100 validate-input" data-validate="Correo es requerido">
                    <label class="etiqueta-input100" for="correo">Correo</label>
                    <input class="input100" type="email" id="correo" name="correo" placeholder="Escriba su correo" required>
                </div>
                <div class="envoltura-input100 validate-input" data-validate="Contraseña es requerida">
                    <label class="etiqueta-input100" for="contraseña">Contraseña</label>
                    <input class="input100" type="password" id="contraseña" name="contraseña" placeholder="Escriba su contraseña" required minlength="5">
                </div>
                <div class="contenedor-boton-formulario-login100">
                    <button class="boton-formulario-login100" type="submit">Iniciar sesión</button>
                </div>
                <div id="enlace-registro" onclick="irARegistro()">Crear nueva cuenta</div>
            </form>
        </div>
    </div>
</div>


<script src="js/index.js"></script>
</body>
</html>