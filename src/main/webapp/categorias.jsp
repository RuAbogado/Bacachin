<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categorias</title>
    <link href="css/clientes.css" rel="stylesheet">
    <link href="css/categorias.css" rel="stylesheet">

</head>
<body>

<h2 class="Categorias">Categorías</h2>

<!-- Botón para mostrar el formulario -->
<div class="button-container">
    <button class="login100-form-btn" type="button" id="agregar-categoria">Nueva categoría</button>
</div>

<!-- Contenedor para el formulario -->
<div id="formularioContainer" class="modal">
    <div class="modal-content">
        <span class="close" id="closeForm">&times;</span>
        <!-- Formulario para agregar categorías -->
        <form id="registroForm" method="post" action="AgregarCategoria" onsubmit="return validarYEnviar()">
            <h2>Agregar nueva categoría</h2>
            <label class="label-input100" for="nombre">Nombre de categoría:</label>
            <input class="input100" type="text" id="nombre" name="nombre" placeholder="Escriba el nombre de la categoría" required>

            <label class="label-input100" for="descripcion">Descripción:</label>
            <input class="input100" type="text" id="descripcion" name="descripcion" placeholder="Escriba la descripción de la categoría" required>

            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit">Registrar Categoría</button>
                <button class="login100-form-btn" type="button" onclick="borrarRegistro()">Borrar Registro</button>
            </div>
        </form>
    </div>
</div>

<!-- Contenedor para la tabla de Categorías -->
<div id="clientesContainer">
    <jsp:include page="/ListarCategorias" />
</div>

<script src="js/categorias.js"></script>
</body>
</html>
