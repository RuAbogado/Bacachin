<%@ page import="mx.edu.utez.giup.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tienda</title>
    <link rel="stylesheet" href="css/Carrito.css" />

    <%
        // Aquí obtenemos el tipo de usuario desde la sesión y lo asignamos a una variable de JavaScript
        User user = (User) session.getAttribute("user");
        String tipoUsuario = user != null ? user.getTipo() : "cliente"; // Puedes ajustar el valor por defecto si es necesario
    %>
    <script type="text/javascript">
        // Inyectar el tipo de usuario desde la sesión en una variable JS
        var tipoUsuario = "<%= tipoUsuario %>";
        console.log("Tipo de usuario:", tipoUsuario); // Para depuración
    </script>
</head>
<body>
<header>
    <h1>Tienda</h1>

    <div class="container-icon">
        <div class="container-cart-icon">
            <svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    class="icon-cart"
            >
                <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        d="M15.75 10.5V6a3.75 3.75 0 10-7.5 0v4.5m11.356-1.993l1.263 12c.07.665-.45 1.243-1.119 1.243H4.25a1.125 1.125 0 01-1.12-1.243l1.264-12A1.125 1.125 0 015.513 7.5h12.974c.576 0 1.059.435 1.119 1.007zM8.625 10.5a.375.375 0 11-.75 0 .375.375 0 01.75 0z"
                />
            </svg>
            <div class="count-products">
                <span id="contador-productos">0</span>
            </div>
        </div>

        <div class="container-cart-products hidden-cart">
            <div class="row-product hidden">
                <!-- Aquí se agregarán dinámicamente los productos del carrito -->
            </div>

            <div class="cart-total hidden">
                <h3>Total:</h3>
                <span class="total-pagar">$0</span>
            </div>
            <p class="cart-empty">El carrito está vacío</p>
            <button class="btn-send-request ">Enviar Solicitud</button>
        </div>

    </div>
</header>
<div class="container-items" id="product-list">
    <!-- Los artículos se llenarán dinámicamente aquí -->
</div>

<script src="js/Carrito.js"></script>
</body>
</html>