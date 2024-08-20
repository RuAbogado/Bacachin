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
    <style>
        @keyframes rise {
            0% {
                transform: translateX(0);
                opacity: 0.1;
            }
            50% {
                transform: translateX(10%);
                opacity: 0.5;
            }
            100% {
                bottom: 110%;
                transform: translateX(-10%);
                opacity: 0;
            }
        }
        .bubble {
            position: absolute;
            bottom: 0;
            width: 20px;
            height: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            border-radius: 50%;
            animation: rise 10s infinite ease-in-out;
            opacity: 0;
        }

        .bubble:nth-child(2) {
            width: 30px;
            height: 30px;
            left: 25%;
            animation-duration: 7s;
            animation-delay: 1s;
        }

        .bubble:nth-child(3) {
            width: 40px;
            height: 40px;
            left: 50%;
            animation-duration: 5s;
            animation-delay: 2s;
        }

        .bubble:nth-child(4) {
            width: 50px;
            height: 50px;
            left: 75%;
            animation-duration: 6s;
            animation-delay: 3s;
        }

        .bubble:nth-child(5) {
            width: 60px;
            height: 60px;
            left: 90%;
            animation-duration: 8s;
            animation-delay: 4s;
        }
    </style>

</head>
<body>
<div class="limiter">
    <div class="contenedor-login100">
        <div class="envoltura-login100">
            <form id="formularioInicioSesion" class="formulario-login100 validate-form" action="Login" method="post">
                <img src="img/GIUP.png" alt="Imagen" class="imagen-circular">
                <div class="envoltura-input100 validate-input" data-validate="Correo es requerido">
                    <label class="etiqueta-input100" for="correo">Correo</label>
                    <input class="input100" type="email" id="correo" name="correo" placeholder="Correo Electronico" required>
                </div>
                <div class="envoltura-input100 validate-input" data-validate="Contraseña es requerida">
                    <label class="etiqueta-input100" for="contraseña">Contraseña</label>
                    <input class="input100" type="password" id="contraseña" name="contraseña" placeholder="Contraseña" required minlength="5">
                </div>
                <div class="contenedor-boton-formulario-login100">
                    <button class="boton-formulario-login100" type="submit">Iniciar sesión</button>
                </div>
                <div id="enlace-registro" onclick="irARegistro()">Crear nueva cuenta</div>

            </form>
        </div>
    </div>
</div>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>

<script src="js/index.js"></script>
</body>
</html>