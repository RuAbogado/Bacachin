<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categorías</title>
    <link href="css/clientes.css" rel="stylesheet">
    <link href="css/productos.css" rel="stylesheet">

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .Categorias {
            text-align: center;
            font-size: 32px;
            font-weight: bold;
            color: #30303F;
            margin-top: 60px;
            margin-bottom: 20px;
        }

        .button-container {
            text-align: center;
            margin-bottom: 30px;
        }

        button {
            padding: 12px 24px;
            font-size: 18px;
            cursor: pointer;
            border: none;
            background-color: #60410d;
            color: white;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        button:hover {
            background-color: #40280a;
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
<h2 class="Categorias">Categorías</h2>

<!-- Botón para mostrar el formulario -->
<div class="button-container">
    <button class="login100-form-btn" type="button" id="agregar-empleado">Nueva categoría</button>
</div>

<!-- Contenedor para el formulario -->
<div id="formularioContainer" class="modal">
    <div class="modal-content">
        <span class="close" id="closeForm">&times;</span>
        <!-- Formulario para agregar categorías -->
        <form id="registroForm" method="post" action="RegisterCategorias" onsubmit="return validarYEnviar()">
            <h2>Agregar nueva categoría</h2>
            <label class="label-input100" for="nombre">Nombre de categoría:</label>
            <input class="input100" type="text" id="nombre" name="nombre" placeholder="Escriba el nombre de la categoría" required>

            <label class="label-input100" for="descripcion">Descripción:</label>
            <input class="input100" type="text" id="descripcion" name="descripcion" placeholder="Escriba la descripción de la categoría" required>

            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit">Registrar Categoría</button>
                <button class="login100-form-btn" type="button" onclick="borrarRegistro()">Borrar Categoría</button>
            </div>
        </form>
    </div>
</div>

<!-- Contenedor para la tabla de categorías -->
<div id="categoriasContainer">
    <jsp:include page="/ListarCategorias" />
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
        var descripcion = document.getElementById("descripcion").value.trim();

        // Validar si algún campo está vacío
        if (nombre === "" || descripcion === "") {
            alert("Por favor, rellene todos los campos.");
            return false;
        }

        // Validar si hay espacios en blanco en el nombre de la categoría
        if (nombre.includes(" ")) {
            alert("No se permiten espacios en blanco en el nombre de la categoría.");
            return false;
        }

        return true;
    }

    function borrarRegistro() {
        var confirmacion = confirm("¿Estás seguro de que deseas vaciar todos los registros? Esta acción no se puede deshacer.");

        if (confirmacion) {
            localStorage.clear();
            location.reload();
        }
    }

    function deleteCategory(categoryId) {
        if (confirm('¿Estás seguro de que deseas eliminar esta categoría?')) {
            fetch(`/delete_category/${categoryId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.ok) {
                        alert('Categoría eliminada correctamente.');
                        location.reload(); // Recargar la página para actualizar la lista de categorías
                    } else {
                        alert('Hubo un problema al eliminar la categoría.');
                    }
                })
                .catch(error => {
                    alert('Error en la solicitud: ' + error);
                });
        }
    }
</script>
</body>
</html>
