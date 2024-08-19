<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos</title>
    <link rel="stylesheet" href="css/productos.css">
    <style>

    </style>
</head>
<body>
<h2 class="Categorias">Productos</h2>
<div class="button-group">
    <button type="button" id="add-product-btn">Agregar Producto</button>
</div>

<div id="productModal" class="modal">
    <div class="modal-content">
        <span class="close-product">&times;</span>
        <h2>Agregar Nuevo Producto</h2>
        <form method="post" action="AgregarProducto" enctype="multipart/form-data" id="form-agregar-producto">
            <div>
                <label for="categoria-producto">Categoría:</label>
                <select id="categoria-producto" name="ID_Categoria" required>
                    <!-- Aquí se cargan las opciones de categorías -->
                    <jsp:include page="/ObtenerCategorias" />
                </select>
            </div>
            <div>
                <label for="marca-producto">Categoría:</label>
                <select id="marca-producto" name="ID_Marca" required>
                    <!-- Aquí se cargan las opciones de categorías -->
                    <jsp:include page="/ObtenerMarcas" />
                </select>
            </div>
            <div>
                <label for="imagen-producto">Imagen:</label>
                <input type="file" id="imagen-producto" name="imagen" accept="image/*" required>
            </div>
            <div>
                <label for="nombre-producto">Nombre:</label>
                <input type="text" id="nombre-producto" name="nombre" placeholder="Nombre del producto" required>
            </div>
            <div>
                <label for="descripcion-producto">Descripcion:</label>
                <input type="text" id="descripcion-producto" name="descripcion" placeholder="Descripcion del producto" required>
            </div>
            <div>
                <label for="precio-producto">Precio:</label>
                <input type="number" id="precio-producto" name="precio" placeholder="$00.00" step="0.01" required>
            </div>
            <div>
                <label for="stock-producto">Stock:</label>
                <input type="number" id="stock-producto" name="stock" placeholder="00">
            </div>

            <div>
                <button type="submit">Agregar Producto</button>
            </div>
        </form>
    </div>
</div>

<div id="clientesContainer">
    <jsp:include page="/ListarProductos" />
</div>

<!-- Contenedor para mostrar los resultados -->
<div id="resultContainer"></div>

<script src="js/productos.js"></script>
</body>
</html>