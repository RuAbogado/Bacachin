<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleados</title>
    <link href="css/clientes.css" rel="stylesheet">
    <style>
    table {
    width: 100%;
    border-collapse: collapse;
    }
    th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
    }
    th {
    background-color: #f2f2f2;
    }
    tr:nth-child(even) {
    background-color: #ffffff;
    }
    button {
    padding: 10px 20px;
    font-size: 16px;
    cursor: pointer;
    border: none;
    background-color: #60410d;
    color: white;
    border-radius: 5px;
    }
    button:hover {
    background-color: #60410d;
    }
    .empleados-tituloprincipal {
    text-align: center;
    font-size: 24px;
    font-weight: bold;
    color: #30303F;
    margin-top: 60px;
    margin-bottom: 70px;
    }

    #clientesContainer table {
    background-color: #ffffff;
    }
    </style>
</head>
<body>
<h2 class="empleados-tituloprincipal">Empleados</h2>
<!-- Formulario para agregar empleados -->
<form id="registroForm" method="post" action="RegisterEmpleados">
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

    <div class="wrap-input100">
        <label class="label-input100" for="Salario">Salario:</label>
        <input class="input100" type="number" id="Salario" name="Salario" placeholder="Salario" required minlength="5">
    </div>

    <div class="container-login100-form-btn">
        <button class="login100-form-btn" type="submit">Registrar Empleado</button>
        <button class="login100-form-btn" type="button" onclick="borrarRegistro()">Borrar registro</button>
    </div>

</form>
<!-- Contenedor para la tabla de clientes -->
<div id="clientesContainer">
    <jsp:include page="/ListarEmpleados" />
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
<script>
    //Funcion para eliminar un empleado
    function deleteUser(userId) {
        if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
            fetch(`/delete_user/${userId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.ok) {
                        alert('Empleado eliminado con éxito');
                        // Aquí puedes agregar código para actualizar la UI
                    } else {
                        alert('Hubo un problema al eliminar al Empleado');
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    }
</script>
</body>
</html>