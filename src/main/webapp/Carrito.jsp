<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <style>
        body {
            background: #FFE7C3FF;
            margin-top: 90px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <!-- Tabla de productos -->
        <main id="items" class="col-md-8">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Fecha de Creación</th>
                    <th>Tipo</th>
                    <th>Imagen</th>
                    <th>Acción</th>
                </tr>
                </thead>
                <tbody id="productos-tbody">
                <!-- Los productos se insertarán aquí con JavaScript -->
                </tbody>
            </table>
        </main>
        <!-- Carrito -->
        <aside class="col-md-4">
            <h2>Carrito</h2>
            <ul id="carrito" class="list-group"></ul>
            <hr>
            <p class="text-right">Total: $<span id="total"></span></p>
            <button id="boton-vaciar" class="btn btn-danger">Vaciar</button>
            <button id="boton-solicitud" class="btn btn-danger">Realizar Solicitud</button>
        </aside>
    </div>
</div>

<!-- Cargar jQuery antes de Bootstrap -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- Cargar tu script después de jQuery y Bootstrap -->
<script src="js/Carrito.js"></script>

</body>
</html>