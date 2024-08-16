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
          <a href="#" class="nav-link text-white" data-target="ventas">
            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"/></svg>
            Ventas
          </a>
        </li>
        <li>
          <a href="#" class="nav-link text-white" data-target="categorias">
            <svg class="bi pe-none me-2" width="16" height="16">
              <use xlink:href="#grid"/>
            </svg>
            Categorias
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
        <li>
          <a href="#" class="nav-link text-white" data-target="empleados">
            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#people-circle"/></svg>
            Empleados
          </a>
        </li>
        <li>
          <a href="#" class="nav-link text-white" data-target="clientes">
            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#speedometer2"/></svg>
            Clientes
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
      <iframe src="inicio.jsp" width="100%" height="900px"></iframe>

    </div>

    <div id="ventas" style="display:none;">
      <iframe src="ventas.jsp" width="100%" height="100%" sandbox="allow-scripts"></iframe>
    </div>


    <div id="categorias" style="display:none;">
      <!-- Contenido cargado dinámicamente aquí -->
      <iframe src="categorias.jsp" width="100%" height="100%" sandbox="allow-scripts"></iframe>
    </div>

    <div id="productos" style="display:none;">

      <iframe src="productos.jsp" width="100%" height="100%" sandbox="allow-scripts"></iframe>

    </div>
    <!-- Parte Alison -->
    <div id="empleados" style="display:none;">

      <h2>Empleados</h2>
      <iframe src="empleados.jsp" width="100%" height="500px"></iframe>

    </div>

    <div id="clientes" style="display:none;">

      <iframe src="clientes.jsp" width="100%" height="500px"></iframe>

    </div>
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