<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clientes</title>
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
<h2 class="cliente-tituloprincipal">Clientes</h2>

<!-- Contenedor para la tabla de clientes -->
<div id="clientesContainer">
    <jsp:include page="/ListarClientes" />
</div>
<script>
    // Función para deshabilitar un usuario
    function deshabilitarUsuario(userId) {
        if (confirm('¿Estás seguro de que deseas deshabilitar este usuario?')) {
            fetch(`/deshabilitar_usuario/${userId}`, {
                method: 'PUT', // Usualmente para deshabilitar se usa PUT
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.ok) {
                        alert('Usuario deshabilitado con éxito');
                        location.reload(); // Recargar la página para actualizar la tabla
                    } else {
                        alert('Hubo un problema al deshabilitar el usuario');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error al deshabilitar el usuario');
                });
        }
    }
</script>
</body>
</html>
