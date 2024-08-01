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
  <div class="container">
    <div class="btn-menu">
      <label for="btn-menu" style="margin-left: -100%">☰</label>
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
<div class="container-menu">
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

<article style="padding-top: 100px;">
  <div class="flex-grow-1 p-3" id="content">
    <div id="inicio">
      <h2>Bienvenido a la página de inicio</h2>
      <p>Contenido de la página de inicio.</p>




    </div>

    <div id="clientes" style="display:none;">
      <h2>Clientes</h2>
      <p>Contenido de la página de clientes.</p>
      <form id="reporteForm" method="post">
        <button type="button" onclick="submitForm()">Generar Reporte</button>
      </form>
    </div>

    <div id="compras" style="display:none;">
      <h2>Ventas</h2>
      <p>Contenido de la página de ventas.</p>
    </div>

    <div id="productos" style="display:none;">
      <!-- Contenido cargado dinámicamente aquí -->
    </div>

    <div id="empleados" style="display:none;">
      <h2>Empleados</h2>
      <p>Contenido de la página de empleados.</p>
    </div>

    <div id="inventario" style="display:none;">
      <h2>Inventario</h2>
      <p>Contenido de la página de Inventario.</p>
    </div>
  </div>
</article>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Agregar Productos</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary">Guardar Cambios</button>
      </div>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/homeadmin.js"></script>
</body>
</html>