<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Productos</title>
    <link rel="stylesheet" href="css/custom.css">
    <link rel="stylesheet" href="css/skeleton.css">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/formulario.css">
</head>
<body>
<div id="hero" class="py-5">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
            </div>
        </div>
    </div>
</div>

<div id="productos-container" class="container">
    <div id="categoria-productos" class="mb-5">
        <h1 id="encabezado" class="encabezado">Productos</h1>
        <div class="container-btn">
            <button id="btn-agregar-producto" class="btn btn-primary mb-3">Agregar Producto</button>
        </div>
        <div id="productos" class="row"></div>
    </div>

    <div id="categoria-bebidas" class="mb-5">
        <h1 id="encabezado_01" class="encabezado">Bebidas</h1>
        <div class="container-btn">
            <button id="btn-agregar-bebida" class="btn btn-primary mb-3">Agregar Bebida</button>
        </div>
        <div id="bebidas" class="row"></div>
    </div>
</div>

<div id="agregar-producto" class="container d-none">
    <h1 id="encabezado_02" class="encabezado">Agregar Nuevo Producto</h1>
    <div class="limiter">
        <div class="container-login100">
            <div class="wrap-login100">
                <form id="form-agregar-producto">
                    <div class="form-group">
                        <label for="imagen-producto">Imagen:</label>
                        <input type="file" id="imagen-producto" name="imagen-producto" class="form-control" accept="image/*" required>
                    </div>
                    <div class="form-group">
                        <label for="nombre-producto">Nombre:</label>
                        <input type="text" id="nombre-producto" name="nombre-producto" class="form-control" placeholder="Nombre del producto" required>
                    </div>
                    <div class="form-group">
                        <label for="descripcion-producto">Descripción:</label>
                        <input type="text" id="descripcion-producto" name="descripcion-producto" class="form-control" placeholder="Descripción del producto" required>
                    </div>
                    <div class="form-group">
                        <label for="precio-producto">Precio:</label>
                        <input type="number" id="precio-producto" name="precio-producto" class="form-control" placeholder="Precio del producto" min="0" step="0.01" required>
                    </div>
                    <div class="form-group">
                        <label for="descuento-producto">Descuento:</label>
                        <input type="number" id="descuento-producto" name="descuento-producto" class="form-control" placeholder="Descuento del producto" min="0" step="0.01">
                    </div>
                    <div class="form-group">
                        <label for="categoria-producto">Categoría:</label>
                        <select id="categoria-producto" name="categoria-producto" class="form-control" required>
                            <option value="productos">Productos</option>
                            <option value="bebidas">Bebidas</option>
                        </select>
                    </div>
                    <div class="container-btn">
                        <button class="btn btn-primary" type="submit">Agregar Producto</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="js/productos.js"></script>
</body>
</html>