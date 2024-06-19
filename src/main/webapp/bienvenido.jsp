<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h1>Bienvenido, <c:out value="${user.username}"/>!</h1>
    <p>Estado: <c:out value="${user.estado ? 'Activo' : 'Inactivo'}"/></p>
    <p>Código: <c:out value="${user.codigo}"/></p>
    <a href="index.jsp" class="btn btn-primary">Cerrar sesión</a>
</div>
</body>
</html>