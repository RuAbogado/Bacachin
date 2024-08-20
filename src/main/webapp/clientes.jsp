<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clientes</title>
    <link href="css/productos.css" rel="stylesheet">
    <style>
        .cliente-tituloprincipal {
            text-align: center;
            font-weight: bold;
            color: #30303F;
            margin-top: 60px;
            margin-bottom: 70px;
        }

        #clientesContainer table {
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<h2 class="cliente-tituloprincipal">Clientes</h2>

<!-- Contenedor para la tabla de clientes -->
<div id="clientesContainer">
    <jsp:include page="/ListarClientes" />
</div>
<script src="js/clientes.js"></script>
</body>
</html>
