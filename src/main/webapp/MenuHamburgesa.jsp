<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" href="css/estilos.css">
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
                <a href="perfil_usuario.jsp"><img alt="logo_perfil" src="img/usuario.png"></a>
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
                    <li>
                        <a href="#" class="nav-link text-white" data-target="clientes">
                            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"/></svg>
                            Clientes
                        </a>
                    </li>
                    <li>
                        <a href="#" class="nav-link text-white" data-target="compras">
                            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"/></svg>
                            Ventas
                        </a>
                    </li>
                    <li>
                        <a href="#" class="nav-link text-white" data-target="productos">
                            <svg class="bi pe-none me-2" width="16" height="16">
                                <use xlink:href="#grid"/>
                            </svg>
                            Productos
                        </a>
                    </li>
                    <li>
                        <a href="#" class="nav-link text-white" data-target="empleados">
                            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"/></svg>
                            Empleados
                        </a>
                    </li>
                    <li>
                        <a href="#" class="nav-link text-white" data-target="inventario">
                            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"/></svg>
                            Inventario
                        </a>
                    </li>
                </ul>
            </nav>
            <label for="btn-menu">✖️</label>
        </div>
    </div>
</body>
</html>
