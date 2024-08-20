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

        .container-login100 {
            margin-top: 240px;
            width: 100%;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 15px;
            position: relative;
        }

        .container-login100::before {
            margin-top: -240px;
            content: "";
            display: block;
            position: absolute;
            z-index: -1;
            width: 100%;
            height: 1500px;
            top: 0;
            left: 0;
            background-color: rgba(255, 255, 255, 0.5);
        }

        .container {
            padding: 20px;
            position: relative;
        }

        .contenedor-perfil {
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            padding: 30px;
            position: relative;
        }

        .wrap-input100 {
            width: 100%;
            position: relative;
            margin-bottom: 20px;
        }

        .label-input100 {
            font-family: 'Poppins-Regular', sans-serif;
            font-size: 14px;
            color: #333;
            line-height: 1.5;
            text-align: left;
            display: block;
            margin-bottom: 5px;
        }

        .input100,
        .input-select {
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

        .input100::placeholder,
        .input-select::placeholder {
            color: #666;
        }

        .close-icon {
            position: absolute;
            top: 10px;
            right: 10px;
            font-size: 24px;
            cursor: pointer;
            color: #333;
        }
    </style>
</head>

<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="container contenedor-perfil">
                <div class="close-icon" onclick="window.location.href='homeempleados.jsp';">✖️</div>
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <div>
                            <span>Nombres: </span><span id="nombre" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Apellidos: </span><span id="apellido" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Nombre usuario: </span><span id="username" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Teléfono: </span><span id="telefono" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>E-mail: </span><span id="correo" class="font-weight-bold"></span>
                        </div>
                        <p class="estado mt-3">Estado: <span id="estado"></span></p>
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
    function cargarDatosUsuario() {
        fetch('getUsuarioEmpleados') // No se pasa ID porque siempre es 4
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                document.getElementById("nombre").textContent = data.nombre;
                document.getElementById("apellido").textContent = data.apellido;
                document.getElementById("username").textContent = data.username;
                document.getElementById("telefono").textContent = data.telefono;
                document.getElementById("correo").textContent = data.correo;
                document.getElementById("estado").textContent = data.estado;
            })
            .catch(error => console.error('Error:', error));
    }

    document.addEventListener("DOMContentLoaded", function() {
        cargarDatosUsuario();  // Llamada a la función después de que el DOM esté listo
    });
</script>
</body>

</html>
