<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos</title>
    <link href="css/clientes.css" rel="stylesheet">
    <link href="css/productos.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFE7C3FF;
            margin: 0;
            padding: 0;
        }

        .Productos {
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
            transition: background-color 0.3s ease;
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
            animation: fadeIn 0.5s;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
            transition: color 0.3s ease;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
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

        select {
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

        /* Estilo para la tabla de productos */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #60410d;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .img-fluid {
            max-width: 100px;
            height: auto;
            display: block;
            margin: 0 auto;
        }

        @media (max-width: 768px) {
            .modal-content {
                width: 95%;
            }

            table {
                width: 100%;
                font-size: 14px;
            }

            button {
                width: 100%;
                padding: 10px;
            }

            .container-login100-form-btn button {
                width: 100%;
                margin-bottom: 10px;
            }
        }
    </style>
</head>
<body>
<h2 class="Productos">Productos</h2>

<!-- Botón para mostrar el formulario -->
<div class="button-container">
    <button class="login100-form-btn" type="button" id="agregar-producto">Nuevo producto</button>
</div>

<!-- Contenedor para el formulario -->
<div id="productModal" class="modal">
    <div class="modal-content">
        <span class="close-product">&times;</span>
        <h2>Agregar Nuevo Producto</h2>
        <form method="post" action="AgregarProducto" enctype="multipart/form-data" id="form-agregar-producto">
            <div>
                <label for="imagen-producto">Imagen:</label>
                <input type="file" id="imagen-producto" name="imagen" accept="image/*" required>
            </div>
            <div>
                <label for="nombre-producto">Nombre:</label>
                <input type="text" id="nombre-producto" name="nombre" placeholder="Nombre del producto" required>
            </div>
            <div>
                <label for="descripcion-producto">Descripción:</label>
                <input type="text" id="descripcion-producto" name="descripcion" placeholder="Descripción del producto" required>
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
                <label for="categoria-producto">Categoría:</label>
                <select id="categoria-producto" name="ID_Categoria" required>
                    <!-- Aquí se cargan las opciones de categorías -->
                    <jsp:include page="/ObtenerCategorias" />
                </select>
            </div>
            <div>
                <button type="submit">Agregar Producto</button>
            </div>
        </form>
    </div>
</div>

<!-- Contenedor para la tabla de productos -->
<div id="categoriasContainer" style="background: white;">
    <table>
        <thead>
        <tr>
            <th>Imagen</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Estado</th>
            <th>Categoria</th>
        </tr>
        </thead>
        <tbody id="productos-tbody">
        <!-- Aquí se cargan los productos desde el servlet -->
        <jsp:include page="/CargarProductos" />
        </tbody>
    </table>
</div>

<script>
    // Mostrar el formulario
    document.getElementById("agregar-producto").addEventListener("click", function() {
        document.getElementById("productModal").style.display = "flex";
    });

    // Cerrar el formulario
    document.querySelector(".close-product").addEventListener("click", function() {
        document.getElementById("productModal").style.display = "none";
    });

    // Cerrar el formulario si se hace clic fuera del modal
    window.addEventListener("click", function(event) {
        if (event.target === document.getElementById("productModal")) {
            document.getElementById("productModal").style.display = "none";
        }
    });
</script>
</body>
</html>
