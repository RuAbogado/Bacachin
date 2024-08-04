<%--
  Created by IntelliJ IDEA.
  User: torre
  Date: 04/08/2024
  Time: 01:46 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, y contribuyentes de Bootstrap">
    <meta name="generator" content="Hugo 0.122.0">
    <title>Inicio</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <header class="headerr">
        <div class="container347">
            <div class="btn-menu">
                <label for="btn-menu" style="">☰</label>
            </div>
            <div class="logo">
                <a href="newInicio.jsp"><span>GIUP</span></a>
            </div>
            <nav class="menu">
                <a href="perfil_usuario.jsp"><img alt="logo_perfil" src="img/usuario.png"></a>
            </nav>
        </div>
    </header>

    <input type="checkbox" id="btn-menu">
    <div class="container-menu347">
        <div class="cont-menu">
            <nav>
                <ul>
                    <li>
                        <a href="newInicio.jsp" style="color:white">
                            Inicio
                        </a>
                    </li>
                    <li>
                        <a href="newClientes.jsp">
                            Clientes
                        </a>
                    </li>
                    <li>
                        <a href="newVentas.jsp">
                            Ventas
                        </a>
                    </li>
                    <li>
                        <a href="newProductos.jsp">
                            Productos
                        </a>
                    </li>
                    <li>
                        <a href="newEmpleados.jsp">
                            Empleados
                        </a>
                    </li>
                    <li>
                        <a href="newInventario.jsp">
                            Inventario
                        </a>
                    </li>
                </ul>
            </nav>
            <label for="btn-menu">✖️</label>
        </div>
    </div>
    <div class="inline">
        <h1>inicio</h1>
    </div>
</body>
</html>
