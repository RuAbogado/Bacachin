<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, y contribuyentes de Bootstrap">
    <meta name="generator" content="Hugo 0.122.0">
    <title>Inicio</title>
    <link rel="icon" type="image/png" href="img/icons/favicon.ico" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/estilos.css">
    <link href="css/homeadmin.css" rel="stylesheet">
</head>
<body>
<header class="header" style="background: #0c0c0c;">
    <div class="container347">
        <div class="btn-menu">
            <label for="btn-menu" style="">☰</label>
        </div>
        <div class="logo">
            <span class="fs-4" style="color: white">GIUP</span>
        </div>
        <nav class="menu">
            <a href="perfil_usuario_empleado.jsp"><img alt="logo_perfil" src="img/usuario.png"></a>
        </nav>
    </div>
</header>

<input type="checkbox" id="btn-menu">
<div class="container-menu347">
    <div class="cont-menu">
        <nav>
            <ul class="nav nav-pills flex-column mb-auto">
                <li class="nav-item">
                    <a href="#" class="nav-link" style="color:white" aria-current="page" data-target="inicio">
                        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"/></svg>
                        Inicio
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link text-white" data-target="Carrito">
                        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"/></svg>
                        Carrito
                    </a>
                </li>

                <li class="nav-item">
                    <a href="#" class="nav-link text-white" data-target="SolicitudesEmpleado">
                        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"/></svg>
                        Solicitudes
                    </a>
                </li>
                <li>
                    <a href="index.jsp" class="nav-link text-white">
                        Cerrar sesion
                    </a>
                </li>
            </ul>
        </nav>
        <label for="btn-menu">✖️</label>
    </div>
</div>

<article>
    <div class="flex-grow-1" id="content" >
        <div id="inicio" style="">
            <iframe src="inicioCliente.jsp" width="100%" height="900px"></iframe>

        </div>

        <div id="Carrito" style="margin-top: 70px; display:none;">
            <iframe src="Carrito.jsp" width="100%" height="100%"></iframe>

        </div>

        <div id="Solicitudes" style="margin-top: 20px; display:none;">
            <iframe src="SolicitudesEmpleado.jsp" width="100%" height="100%"></iframe>

        </div>

    </div>
</article>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/homeadmin.js"></script>
</body>
</html>
