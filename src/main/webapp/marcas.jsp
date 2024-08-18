<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Marcas</title>
    <link href="css/clientes.css" rel="stylesheet">
    <link href="css/productos.css" rel="stylesheet">
    <style>
        .deshabilitada {
            position: relative;
            opacity: 0.5; /* Hacerla más tenue */
        }

        .deshabilitada::after {
            content: 'Deshabilitada';
            color: red;
            font-weight: bold;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            text-align: center;
            background-color: rgba(255, 255, 255, 0.7);
            display: flex;
            align-items: center;
            justify-content: center;
            pointer-events: none; /* Para que no sea clickeable */
        }
    </style>
</head>
<body>

<h2 class="Categorias">Marcas</h2>

<!-- Botón para mostrar el formulario -->
<div class="button-group">
    <button type="button" id="add-marca-btn">Agregar Marcas</button>
</div>

<!-- Contenedor para el formulario -->
<div id="marcaModal" class="modal">
    <div class="modal-content">
        <span class="close-marca">&times;</span>
        <h2>Agregar nueva marca</h2>
        <form id="marca-form">
            <div>
                <label for="new-marca">Nombre de la nueva marca:</label>
                <input type="text" id="new-marca" required>
            </div>
            <div>
                <label for="marca-description">Descripcion de la marca:</label>
                <textarea id="marca-description" rows="4" required></textarea>
            </div>
            <button type="submit">Agregar</button>
        </form>
    </div>
</div>

<!-- Contenedor para la tabla de Marcas -->
<div id="clientesContainer">
    <jsp:include page="/ListarMarcas" />
</div>

<!-- Contenedor para mostrar los resultados -->
<div id="resultContainer"></div>

<script src="js/marcas.js"></script>
</body>
</html>
