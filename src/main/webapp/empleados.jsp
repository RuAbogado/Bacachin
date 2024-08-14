<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empleados</title>
    <link href="css/clientes.css" rel="stylesheet">
    <link href="css/productos.css" rel="stylesheet">

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
            background-color: #503208;
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
        .modal {
            display: none; /* Ocultar el formulario por defecto */
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
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 600px;
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
    </style>
</head>
<body>
<h2 class="empleados-tituloprincipal">Empleados</h2>

<!-- Botón para mostrar el formulario -->
<button class="login100-form-btn" type="button" id="agregar-empleado">Nuevo empleado</button>

<!-- Contenedor para el formulario -->
<div id="formularioContainer" class="modal">
    <div class="modal-content">
        <span class="close" id="closeForm">&times;</span>
        <!-- Formulario para agregar empleados -->
        <form id="registroForm" method="post" action="RegisterEmpleados" onsubmit="return validarYEnviar()">
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

<!-- Contenedor para la tabla de empleados -->
<div id="clientesContainer">
    <table>
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Nombre de Usuario</th>
            <th>Teléfono</th>
            <th>Correo</th>
            <th>Salario</th>
            <th>Fecha de Contratación</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
            // Supongamos que tienes una lista de empleados en una variable llamada "empleados"
            List<Empleado> empleados = (List<Empleado>) request.getAttribute("empleados");
            for (Empleado empleado : empleados) {
        %>
        <tr>
            <td><%= empleado.getNombre() %></td>
            <td><%= empleado.getApellido() %></td>
            <td><%= empleado.getUsuario() %></td>
            <td><%= empleado.getTelefono() %></td>
            <td><%= empleado.getCorreo() %></td>
            <td><%= empleado.getSalario() %></td>
            <td><%= empleado.getFechaContratacion() %></td>
            <td>
                <button onclick="deleteUser('<%= empleado.getId() %>')">Eliminar Empleado</button>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<script>
    // Mostrar el formulario
    document.getElementById("agregar-empleado").addEventListener("click", function() {
        document.getElementById("formularioContainer").style.display = "flex";
    });

    // Cerrar el formulario
    document.getElementById("closeForm").addEventListener("click", function() {
        document.getElementById("formularioContainer").style.display = "none";
    });

    // Cerrar el formulario si se hace clic fuera del formulario
    window.addEventListener("click", function(event) {
        if (event.target === document.getElementById("formularioContainer")) {
            document.getElementById("formularioContainer").style.display = "none";
        }
    });

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
        return true;
    }

    function borrarRegistro() {
        var confirmacion = confirm("¿Estás seguro de que deseas vaciar todos los registros? Esta acción no se puede deshacer.");

        if (confirmacion) {
            localStorage.clear();
            location.reload();
        }
    }

    // Función para eliminar un empleado
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
                        location.reload(); // Recargar la página para actualizar la lista
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
