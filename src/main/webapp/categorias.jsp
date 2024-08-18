<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Categorias</title>
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

<h2 class="Categorias">Categorías</h2>

<!-- Botón para mostrar el formulario -->
<div class="button-group">
    <button type="button" id="add-category-btn">Agregar Categoria</button>
</div>

<!-- Contenedor para el formulario -->
<div id="categoryModal" class="modal">
    <div class="modal-content">
        <span class="close-category">&times;</span>
        <h2>Agregar nueva categoria</h2>
        <form id="category-form">
            <div>
                <label for="new-category">Nombre de la nueva categoria:</label>
                <input type="text" id="new-category" required>
            </div>
            <div>
                <label for="category-description">Descripcion de la categoria:</label>
                <textarea id="category-description" rows="4" required></textarea>
            </div>
            <button type="submit">Agregar</button>
        </form>
    </div>
</div>

<!-- Contenedor para la tabla de Categorías -->
<div id="clientesContainer">
    <jsp:include page="/ListarCategorias" />
</div>

<!-- Contenedor para mostrar los resultados -->
<div id="resultContainer"></div>

<script src="js/categorias.js"></script>
</body>
</html>
