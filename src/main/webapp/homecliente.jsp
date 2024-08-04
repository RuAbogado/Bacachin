<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <script src="js/color-modes.js"></script>
  <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.min.js"></script>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="Mark Otto, Jacob Thornton, y Bootstrap contributors">
  <meta name="generator" content="Hugo 0.122.0">
  <title>Inicio</title>
  <link rel="icon" type="image/png" href="img/icons/favicon.ico" />
  <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sidebars/">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
  <link href="css/bootstrap.css" rel="stylesheet">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/estilos.css">

  <style>
    #inicio {
      background-image: url('img/portada_inicio.png');
      background-size: cover;
      background-repeat: no-repeat;
      background-position: center;
      padding: 60px;
      color: white;
    }

    .bd-placeholder-img {
      font-size: 1.125rem;
      text-anchor: middle;
      -webkit-user-select: none;
      -moz-user-select: none;
      user-select: none;
    }

    @media (min-width: 768px) {
      .bd-placeholder-img-lg {
        font-size: 3.5rem;
      }
    }

    .b-example-divider {
      width: 100%;
      height: 3rem;
      background-color: rgba(0, 0, 0, .1);
      border: solid rgba(0, 0, 0, .15);
      border-width: 1px 0;
      box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
    }

    .b-example-vr {
      flex-shrink: 0;
      width: 1.5rem;
      height: 100vh;
    }

    .bi {
      vertical-align: -.125em;
      fill: currentColor;
    }

    .nav-scroller {
      position: relative;
      z-index: 2;
      height: 2.75rem;
      overflow-y: hidden;
    }

    .nav-scroller .nav {
      display: flex;
      flex-wrap: nowrap;
      padding-bottom: 1rem;
      margin-top: -1px;
      overflow-x: auto;
      text-align: center;
      white-space: nowrap;
      -webkit-overflow-scrolling: touch;
    }

    .btn-bd-primary {
      --bd-violet-bg: #9C5D00;
      --bd-violet-rgb: 112.520718, 44.062154, 249.437846;
      --bs-btn-font-weight: 600;
      --bs-btn-color: var(--bs-white);
      --bs-btn-bg: var(--bd-violet-bg);
      --bs-btn-border-color: var(--bd-violet-bg);
      --bs-btn-hover-color: var(--bs-white);
      --bs-btn-hover-bg: #9C5D00;
      --bs-btn-hover-border-color: #9C5D00;
      --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
      --bs-btn-active-color: var(--bs-btn-hover-color);
      --bs-btn-active-bg: #9C5D00;
      --bs-btn-active-border-color: #9C5D00;
    }

    .bd-mode-toggle {
      z-index: 1500;
    }

    .bd-mode-toggle .dropdown-menu .active .bi {
      display: block !important;
    }

    .icon-img {
      width: 65px;
      height: 65px;
      border-radius: 40%;
      object-fit: cover;
    }

    .nav-link.active {
      background-color: #D2A679;
      color: white;
    }

    .nav-link:hover {
      background-color: #9C5D00;
      color: white;
    }

    .label-input100 {
      font-family: 'Poppins-Regular', sans-serif;
      font-size: 15px;
      color: #333;
      line-height: 2;
      text-align: left;
      display: inline-block;
      margin-bottom: 7px;
    }

    .vertical-divider {
      border-left: 2px solid #000;
      height: calc(100% + 70px);
      position: absolute;
      left: 70%;
      top: 80px;
    }

    .vertical-divider h2 {
      margin-left: 10px;
    }

    .container {
      position: relative;
      height: 100vh;
    }
  </style>
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
  <div class="cont-menu" style="border-right: 2px solid white; border-bottom-right-radius: 5%; border-top-right-radius: 5%;">
    <nav>
      <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
          <a href="#" class="nav-link" style="color:white" aria-current="page" data-target="inicio">
            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#home"/></svg>
            Inicio
          </a>
        </li>
        <li>
          <a href="#" class="nav-link text-white" data-target="compras">
            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#table"/></svg>
            Compras
          </a>
        </li>
        <li>
          <a href="#" class="nav-link text-white" data-target="productos">
            <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="#grid"/></svg>
            Productos
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

      <iframe src="inicio.jsp" width="100%" height="500px"></iframe>

    </div>



    <div id="compras" style="display:none;">
      <h2>Compras</h2>
      <p>Contenido de la página de Compras.</p>
    </div>

    <div id="productos" style="display:none;">
      <h2>Productos</h2>
      <div class="btn-2">
        <h1>¡Agrega productos!</h1>
        <a href="" data-bs-toggle="modal" data-bs-target="#exampleModal"><span>Agregar Producto</span></a>
      </div>
      <div class="vertical-divider">
        <div>
          <hr>
          <hr>
          <hr>
          <hr>
          <h2>Carrito de Compras</h2>
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
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
        <button type="button" class="btn btn-primary">Guardar Cambios</button>
      </div>
    </div>
  </div>
</div>

<script>
  document.addEventListener('DOMContentLoaded', function () {
    console.log('DOM completamente cargado');

    function mostrarClientes() {
      console.log('mostrarClientes se ha llamado');
      const clientesElement = document.getElementById('clientes');
      if (clientesElement) {
        console.log('Elemento clientes encontrado');
        clientesElement.style.display = 'block';
      } else {
        console.log('Elemento clientes no encontrado');
      }
    }

    const targetLink = document.querySelector('a[data-target="clientes"]');
    if (targetLink) {
      console.log('Enlace encontrado');
      targetLink.addEventListener('click', function (event) {
        event.preventDefault();
        mostrarClientes();
      });
    } else {
      console.log('Enlace no encontrado');
    }
  });



  function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
  }

  function submitForm() {
    var form = document.getElementById("reporteForm");
    form.action = getContextPath() + "/generarReporte";
    form.submit();
  }

</script>


<script src="js/bootstrap.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>

</body>
</html>
