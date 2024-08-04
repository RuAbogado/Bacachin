<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos</title>

</head>
<body>
<div id="productos-container">
    <!-- Aquí se agregarán dinámicamente las nuevas categorías -->
</div>

<div>
    <button type="button" id="add-product-btn">Agregar Producto</button>
    <button type="button" id="add-category-btn">Agregar Categoría</button>
</div>

<!-- Modal para agregar nuevo producto -->
<div id="productModal" style="display: none;">
    <div>
        <span class="close-product">&times;</span>
        <h2>Agregar Nuevo Producto</h2>
        <form id="form-agregar-producto">
            <div>
                <label for="imagen-producto">Imagen:</label>
                <input type="file" id="imagen-producto" name="imagen-producto" accept="image/*" required>
            </div>
            <div>
                <label for="nombre-producto">Nombre:</label>
                <input type="text" id="nombre-producto" name="nombre-producto" placeholder="Nombre del producto" required>
            </div>
            <div>
                <label for="descripcion-producto">Descripción:</label>
                <input type="text" id="descripcion-producto" name="descripcion-producto" placeholder="Descripción del producto" required>
            </div>
            <div>
                <label for="precio-producto">Precio:</label>
                <input type="number" id="precio-producto" name="precio-producto" placeholder="$00.00" required>
            </div>
            <div>
                <label for="stock-producto">Stock:</label>
                <input type="number" id="stock-producto" name="stock-producto" placeholder="00">
            </div>
            <div>
                <label for="categoria-producto">Categoría:</label>
                <select id="categoria-producto" name="categoria-producto" required>
                    <!-- Las categorías se agregarán dinámicamente -->
                </select>
            </div>
            <div>
                <button type="submit">Agregar Producto</button>
            </div>
        </form>
    </div>
</div>

<!-- Modal para agregar nueva categoría -->
<div id="categoryModal" style="display: none;">
    <div>
        <span class="close-category">&times;</span>
        <h2>Agregar nueva categoría</h2>
        <form id="category-form">
            <div>
                <label for="new-category">Nombre de la nueva categoría:</label>
                <input type="text" id="new-category" required>
            </div>
            <div>
                <label for="category-description">Descripción de la categoría:</label>
                <textarea id="category-description" rows="4" required></textarea>
            </div>
            <button type="submit">Agregar</button>
        </form>
    </div>
</div>

<script src="js/productos.js"></script>
</body>
</html>