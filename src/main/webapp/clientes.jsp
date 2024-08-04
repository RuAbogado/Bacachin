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
<!-- Formulario para generar reporte -->
<form id="reporteForm" method="post">
    <button type="button" onclick="submitForm()">Generar Reporte</button>
    <button id="delete_user" onclick="deleteUser(123)">Eliminar Usuario</button>

</form>
<!-- Contenedor para la tabla de clientes -->
<div id="clientesContainer">
    <jsp:include page="/ListarClientes" />
</div>
<script>
    // Función para manejar el formulario de reporte
    function submitForm() {
        document.getElementById('reporteForm').submit();
    }
    //Funcion para eliminar un usuario
    function deleteUser(userId) {
        if (confirm('¿Estás seguro de que deseas eliminar este usuario?')) {
            fetch(`/delete_user/${userId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.ok) {
                        alert('Usuario eliminado con éxito');
                        // Aquí puedes agregar código para actualizar la UI
                    } else {
                        alert('Hubo un problema al eliminar el usuario');
                    }
                })
                .catch(error => console.error('Error:', error));
        }
    }
</script>
</body>
</html>