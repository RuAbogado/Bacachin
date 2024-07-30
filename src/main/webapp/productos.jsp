<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Diseño modal carrito de compras con HTML, CSS y JavaScript</title>

    <!-- FUENTE DE ICONOS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/productos.css">
</head>
<body>

<div class="container">

    <div class="banner">
        <img src="img/banner.jpg" alt="">
    </div>

    <h1 class="title">Nuevos Productos</h1>

    <div class="product-grid" id="productGrid">
        <!-- Los productos añadidos dinámicamente se mostrarán aquí -->
    </div>

    <button class="btn-primary" id="openAddProductModal">Agregar Producto</button>

    <!-- FORMULARIO PARA AGREGAR PRODUCTO -->
    <div class="modal" id="jsAddProductModal" style="display: none;">
        <div class="modal__container">
            <button type="button" class="modal__close fa-solid fa-xmark jsAddProductClose"></button>
            <h2>Agregar Producto</h2>
            <form id="addProductForm">
                <label for="productId">ID Producto:</label>
                <input type="text" id="productId" name="productId" required>
                <label for="categoryId">ID Categoría:</label>
                <input type="text" id="categoryId" name="categoryId" required>
                <label for="productName">Nombre:</label>
                <input type="text" id="productName" name="productName" required>
                <label for="productDesc">Descripción:</label>
                <input type="text" id="productDesc" name="productDesc">
                <label for="productPrice">Precio:</label>
                <input type="number" id="productPrice" name="productPrice" step="0.01" required>
                <label for="productStock">Stock:</label>
                <input type="number" id="productStock" name="productStock" required>
                <label for="productDate">Fecha de Creación:</label>
                <input type="date" id="productDate" name="productDate" required>
                <label for="productType">Tipo:</label>
                <input type="text" id="productType" name="productType">
                <label for="productImage">Imagen URL:</label>
                <input type="text" id="productImage" name="productImage">
                <button type="submit">Agregar Producto</button>
            </form>
        </div>
    </div>

</div>

<script src="js/productos.js"></script>
</body>
</html>