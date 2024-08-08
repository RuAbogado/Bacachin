<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Inicio - Sistema de Cafetería</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    @keyframes rise {
      0% {
        transform: translateX(0);
        opacity: 0.1;
      }
      50% {
        transform: translateX(10%);
        opacity: 0.5;
      }
      100% {
        bottom: 110%;
        transform: translateX(-10%);
        opacity: 0;
      }
    }

    body {
      font-family: 'Arial', sans-serif;
      background: url('img/cafeportada.png') no-repeat center center fixed;
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
      background-color: rgba(255, 255, 255, 0.42);
      z-index: 0;
    }

    .content {
      position: relative;
      z-index: 1;
      text-align: center;
      background-color: rgba(210, 166, 121, 0.9);
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

    .bubble {
      position: absolute;
      bottom: 0;
      width: 20px;
      height: 20px;
      background-color: rgba(255, 255, 255, 0.8);
      border-radius: 50%;
      animation: rise 10s infinite ease-in-out;
      opacity: 0;
    }

    .bubble:nth-child(2) {
      width: 30px;
      height: 30px;
      left: 25%;
      animation-duration: 7s;
      animation-delay: 1s;
    }

    .bubble:nth-child(3) {
      width: 40px;
      height: 40px;
      left: 50%;
      animation-duration: 5s;
      animation-delay: 2s;
    }

    .bubble:nth-child(4) {
      width: 50px;
      height: 50px;
      left: 75%;
      animation-duration: 6s;
      animation-delay: 3s;
    }

    .bubble:nth-child(5) {
      width: 60px;
      height: 60px;
      left: 90%;
      animation-duration: 8s;
      animation-delay: 4s;
    }
  </style>
</head>
<body>

<div class="content">
  <h1>Bienvenido </h1>
  <p>Esperamos que sus compras sean de su agrado</p>
  <button class="btn btn-primary" onclick="location.href='Carrito.jsp'">Ver Carrito</button>
</div>

<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>
<div class="bubble"></div>

<footer>
  <p>&copy; 2024 Sistema de Cafetería. Todos los derechos reservados.</p>
</footer>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
