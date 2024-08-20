<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleados</title>
    <link href="css/productos.css" rel="stylesheet">

    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .empleados-tituloprincipal {
            text-align: center;
            font-size: 32px;
            font-weight: bold;
            color: #30303F;
            margin-top: 60px;
            margin-bottom: 20px;
        }

        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 30px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-size: 16px;
            margin-bottom: 8px;
            color: #30303F;
            font-weight: bold;
        }

        input[type="text"],
        input[type="tel"],
        input[type="email"],
        input[type="password"],
        input[type="number"],
        input[type="date"] {
            padding: 10px;
            font-size: 16px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
            box-sizing: border-box;
        }

        .container-login100-form-btn {
            display: flex;
            justify-content: space-between;
        }

        .container-login100-form-btn button {
            width: 48%;
        }

    </style>
</head>
<body>
<h2 class="empleados-tituloprincipal">Empleados</h2>

<!-- Botón para mostrar el formulario -->
<div class="button-container">
    <button class="login100-form-btn" type="button" id="agregar-empleado">Nuevo empleado</button>
</div>

<!-- Contenedor para el formulario -->
<div id="formularioContainer" class="modal">
    <div class="modal-content">
        <span class="close" id="closeForm">&times;</span>
        <!-- Formulario para agregar empleados -->
        <form id="registroForm" method="post" action="RegisterEmpleados" onsubmit="return validarYEnviar()">
            <h2>Agregar Nuevo Empleado</h2>
            <label class="label-input100" for="nombre">Nombre:</label>
            <input class="input100" type="text" id="nombre" name="nombre" placeholder="Escriba su nombre" required>

            <label class="label-input100" for="apellido">Apellidos:</label>
            <input class="input100" type="text" id="apellido" name="apellido" placeholder="Escriba su apellido" required>

            <label class="label-input100" for="usuario">Nombre de usuario:</label>
            <input class="input100" type="text" id="usuario" name="usuario" placeholder="Escriba su nombre de usuario" required>

            <label class="label-input100" for="telefono">Teléfono:</label>
            <input class="input100" type="tel" id="telefono" name="telefono" placeholder="1234567890" required>

            <label class="label-input100" for="correo">Correo:</label>
            <input class="input100" type="email" id="correo" name="correo" placeholder="correo@ejemplo.com" required>

            <label class="label-input100" for="contraseña">Contraseña:</label>
            <input class="input100" type="password" id="contraseña" name="contraseña" placeholder="Escriba su contraseña" required minlength="5">

            <label class="label-input100" for="confirmarContraseña">Confirmar Contraseña:</label>
            <input class="input100" type="password" id="confirmarContraseña" name="confirmarContraseña" placeholder="Repita su contraseña" required minlength="5">

            <label class="label-input100" for="salario">Salario:</label>
            <input class="input100" type="number" id="salario" name="salario" placeholder="Salario" required>

            <label class="label-input100" for="fecha">Fecha de Contratación:</label>
            <input class="input100" type="date" id="fecha" name="fecha" required>

            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit">Registrar Empleado</button>
                <button class="login100-form-btn" type="button" onclick="borrarRegistro()">Borrar registro</button>
            </div>
        </form>
    </div>
</div>

<!-- Contenedor para la tabla de clientes -->
<div id="clientesContainer">
    <jsp:include page="/ListarEmpleados" />
</div>
<script src="js/empleados.js"></script>
</body>
</html>
