<%--
  Created by IntelliJ IDEA.
  User: rubengo
  Date: 13/08/24
  Time: 2:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Solicitudes Empleados</title>
    <link href="css/clientes.css" rel="stylesheet">
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #ffffff;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            background-color: #60410d;
            color: white;
            border-radius: 5px;
        }
        button:hover {
            background-color: #60410d;
        }
        .cliente-tituloprincipal {
            text-align: center;
            font-size: 24px;
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
<h2 class="cliente-tituloprincipal">Solicitudes</h2>
<form id="reporteForm" method="post">
    <button id="Estatus" onclick="Estatus(123)">Estatus</button>

</form>
<!-- Contenedor para la tabla de clientes -->
<div id="clientesContainer">
    <jsp:include page="/ListarSolicitudes" />
</div>
<script>
</script>
</body>
</html>
