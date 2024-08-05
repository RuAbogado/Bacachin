<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Empleados</title>
</head>
<body>
<table class="table table-striped mt-3">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Email</th>
        <th>Teléfono</th>
        <th>Dirección</th>
    </tr>
    </thead>
    <tbody id="empleadosTabla">
    <!-- Aquí se agregarán los empleados dinámicamente -->
    </tbody>
</table>
</div>
<p> </p>
<button type="button" class="btn btn-primary" onclick="mostrarFormularioAgregar()">Agregar Empleado</button>

<link href="css/empleados.css" rel="stylesheet">
<script src="js/empleados.js"></script>
</body>
</html>