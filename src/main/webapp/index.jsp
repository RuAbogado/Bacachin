<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de sesión</title>
    <link rel="icon" type="image/png" href="img/icons/icono.png"/>
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
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

        .contenedor-login100 {
            width: 100%;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 15px;
        }

        .contenedor-login100::before {
            content: "";
            display: block;
            position: absolute;
            z-index: -1;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background-color: rgba(255,255,255,0.5); /* Ajusta el valor de la opacidad aquí */
        }

        .envoltura-login100 {
            width: 100%;
            max-width: 400px;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            overflow: hidden;
            padding: 55px 55px 37px 55px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .imagen-circular {
            width: 200px;
            height: 200px;
            margin-bottom: 20px;
            border-radius: 50%;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        .envoltura-input100 {
            width: 100%;
            position: relative;
            margin-bottom: 20px;
        }

        .etiqueta-input100 {
            font-family: 'Poppins-Regular', sans-serif;
            font-size: 14px;
            color: #333;
            line-height: 1.5;
            text-align: left;
            display: block;
            margin-bottom: 5px;
        }

        .input100 {
            font-family: 'Poppins-Medium', sans-serif;
            font-size: 15px;
            color: #333;
            line-height: 1.2;
            display: block;
            width: 100%;
            height: 45px;
            background: #f7f7f7;
            padding: 0 10px;
            border: 1px solid #e6e6e6;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .input100::placeholder {
            color: #666;
        }

        .boton-formulario-login100 {
            font-family: 'Poppins-Medium', sans-serif;
            font-size: 16px;
            color: #fff;
            line-height: 1.2;
            text-transform: uppercase;
            width: 100%;
            height: 45px;
            border-radius: 5px;
            background: #8B4513; /* Color café */
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 0 25px;
            transition: all 0.4s;
            border: none;
            margin-top: 20px;
        }

        .boton-formulario-login100:hover {
            background: #5D3A00; /* Color café más oscuro */
            color: #fff;
        }

        .contenedor-boton-formulario-login100 {
            margin-top: 20px;
        }

        #enlace-registro {
            margin-top: 20px;
            text-align: center;
            color: #C49E66;
            font-size: 14px;
            text-decoration: underline;
            cursor: pointer;
        }
    </style>
    <script>
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('error')) {
                alert('Correo o contraseña incorrectos');
            }
        };

        function irARegistro() {
            window.location.href = "registro.jsp";
        }
    </script>
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
</body>
</html>