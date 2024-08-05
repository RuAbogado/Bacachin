<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Inicio - Sistema de Cafetería</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      font-family: 'Arial', sans-serif;
      background: url('img/banner.png') no-repeat center center fixed;
      background-size: cover;
      color: #333;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      position: relative;
    }
    body::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(255, 255, 255, 0.42); /* Opacidad sobre la imagen de fondo */
      z-index: 0;
    }
    .content {
      position: relative;
      z-index: 1;
      text-align: center;
      background-color: rgba(210, 166, 121, 0.9); /* Color de fondo del contenedor con opacidad */
      padding: 50px;
      border-radius: 15px;
      box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }
    .content h1 {
      font-size: 2.5rem;
      margin-bottom: 20px;
    }
    .content p {
      font-size: 1.2rem;
      margin-bottom: 30px;
    }
    .btn-primary {
      background-color: #60410d;
      border: none;
      padding: 10px 20px;
      font-size: 1rem;
      border-radius: 5px;
    }
    .btn-primary:hover {
      background-color: #4b330a;
    }
    footer {
      position: absolute;
      bottom: 0;
      width: 100%;
      text-align: center;
      padding: 10px 0;
      background-color: #343a40;
      color: white;
      z-index: 1;
    }
  </style>
</head>
<body>

<div class="content">
  <h1>Bienvenido a Nuestra Cafetería</h1>
  <p>El mejor café de la ciudad, solo para ti.</p>
  <button class="btn btn-primary" onclick="location.href='productos.jsp'">Ver Menú</button>
</div>

<footer>
  <p>&copy; 2024 Sistema de Cafetería. Todos los derechos reservados.</p>
</footer>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>