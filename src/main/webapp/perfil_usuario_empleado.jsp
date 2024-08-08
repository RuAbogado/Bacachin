<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Perfil</title>
    <link rel="icon" type="image/png" href="img/icons/icono.png" />
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: url('img/cafetería.webp') no-repeat center center fixed;
            background-size: cover;
        }

        .container-login100 {
            width: 100%;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 15px;
            position: relative;
        }

        .container-login100::before {
            content: "";
            display: block;
            position: absolute;
            z-index: -1;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background-color: rgba(255, 255, 255, 0.5);
        }

        .container {
            padding: 20px;
            position: relative;
        }

        .contenedor-perfil {
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            padding: 30px;
            position: relative;
        }

        .circle-img {
            width: 200px;
            height: 200px;
            margin-bottom: 20px;
            border-radius: 50%;
            display: block;
            margin-left: -20px;
            margin-right: auto;
            overflow: hidden;
            cursor: pointer;
        }

        .circle-img img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .boton-modificar,
        .boton-guardar,
        .boton-cancelar {
            background-color: #8B4513;
            color: #fff;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 20px;
        }

        .boton-modificar:hover,
        .boton-guardar:hover,
        .boton-cancelar:hover {
            background-color: #5D3A00;
        }

        .wrap-input100 {
            width: 100%;
            position: relative;
            margin-bottom: 20px;
        }

        .input-file {
            display: none;
        }

        .label-input100 {
            font-family: 'Poppins-Regular', sans-serif;
            font-size: 14px;
            color: #333;
            line-height: 1.5;
            text-align: left;
            display: block;
            margin-bottom: 5px;
        }

        .input100,
        .input-select {
            font-family: 'Poppins-Medium', sans-serif;
            font-size: 15px;
            color: #333;
            line-height: 1.2;
            display: block;
            width: 100%;
            height: 45px;
            background: #f7f7f7;
            padding: 0 10px;
            border: 1px solid #e6e6e6;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .input100::placeholder,
        .input-select::placeholder {
            color: #666;
        }

        .login100-form-btn {
            font-family: 'Poppins-Medium', sans-serif;
            font-size: 16px;
            color: #fff;
            line-height: 1.2;
            text-transform: uppercase;
            width: 48%;
            height: 45px;
            border-radius: 5px;
            background: #8B4513;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 0 25px;
            transition: all 0.4s;
            border: none;
            margin-top: 20px;
        }

        .login100-form-btn:hover {
            background: #5D3A00;
            color: #fff;
        }

        .container-login100-form-btn {
            width: 100%;
            display: flex;
            justify-content: space-between;
        }

        .close-icon {
            position: absolute;
            top: 10px;
            right: 10px;
            font-size: 24px;
            cursor: pointer;
            color: #333;
        }
    </style>
</head>

<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="container contenedor-perfil">
                <div class="close-icon" onclick="window.location.href='homeempleados.jsp';">✖️</div>
                <div class="row justify-content-center">
                    <div class="col-md-6">
                        <label for="file-upload" class="circle-img">
                            <img id="foto-perfil" src="img/icons/imagen.png" alt="Foto de perfil">
                        </label>
                        <input type="file" id="file-upload" class="input-file" accept="image/*"
                               onchange="cargarFoto(this)">

                        <div>
                            <span>Nombres: </span><span id="nombre" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Apellidos: </span><span id="apellido" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Nombre usuario: </span><span id="nombre_usuario"
                                                               class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Teléfono: </span><span id="telefono" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Sexo: </span><span id="sexo" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>E-mail: </span><span id="email-usuario" class="font-weight-bold"></span>
                        </div>
                        <p class="estado mt-3">Estado: <span id="estado-usuario"></span></p>
                        <button class="boton-modificar" onclick="habilitarEdicion()">Modificar Todos</button>
                        <button class="boton-guardar" onclick="guardarCambios()" style="display:none;">Guardar
                            Cambios</button>
                        <button class="boton-cancelar" onclick="cancelarCambios()" style="display:none;">Cancelar
                            Cambios</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
<script>

        const usuarioId = 1; // ID del usuario que quieres cargar
        let datosOriginales = {};

        document.addEventListener("DOMContentLoaded", function () {
        cargarDatosUsuario();
    });

        const cargarDatosUsuario = () => {
        fetch(`usuarioId${usuarioId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al cargar los datos del usuario');
                }
                return response.json();
            })
            .then(data => {
                datosOriginales = data;  // Guardar los datos originales
                document.getElementById("nombre").textContent = data.nombre;
                document.getElementById("apellido").textContent = data.apellido;
                document.getElementById("nombre_usuario").textContent = data.nombreUsuario;
                document.getElementById("telefono").textContent = data.telefono;
                document.getElementById("sexo").textContent = data.sexo;
                document.getElementById("email-usuario").textContent = data.email;
                document.getElementById("foto-perfil").src = data.fotoPerfil || "img/icons/imagen.png";
            })
            .catch(error => {
                console.error('Error al cargar los datos del usuario:', error);
                alert('Error al cargar los datos del usuario. Por favor, inténtalo de nuevo más tarde.');
            });
    };

        const habilitarEdicion = () => {
        const campos = ['nombre', 'apellido', 'nombre_usuario', 'telefono', 'sexo', 'email-usuario'];
        campos.forEach(campo => {
        const span = document.getElementById(campo);
        let input;
        if (campo === 'sexo') {
        input = document.createElement('select');
        input.id = `input-${campo}`;
        input.classList.add('input-select');
        ['Masculino', 'Femenino', 'Otro'].forEach(optionText => {
        const option = document.createElement('option');
        option.value = optionText;
        option.textContent = optionText;
        if (optionText === span.textContent) {
        option.selected = true;
    }
        input.appendChild(option);
    });
    } else {
        input = document.createElement('input');
        input.id = `input-${campo}`;
        input.type = 'text';
        input.value = span.textContent;
        input.classList.add('input100');
    }
        span.parentNode.replaceChild(input, span);
    });
        document.querySelector('.boton-modificar').style.display = 'none';
        document.querySelector('.boton-guardar').style.display = 'inline-block';
        document.querySelector('.boton-cancelar').style.display = 'inline-block';
    };

        const cancelarCambios = () => {
        const campos = ['nombre', 'apellido', 'nombre_usuario', 'telefono', 'sexo', 'email-usuario'];
        campos.forEach(campo => {
        const input = document.getElementById(`input-${campo}`);
        const span = document.createElement('span');
        span.id = campo;
        span.classList.add('font-weight-bold');
        span.textContent = datosOriginales[campo];
        input.parentNode.replaceChild(span, input);
    });
        document.getElementById("foto-perfil").src = datosOriginales.fotoPerfil || "img/icons/imagen.png";
        document.querySelector('.boton-modificar').style.display = 'inline-block';
        document.querySelector('.boton-guardar').style.display = 'none';
        document.querySelector('.boton-cancelar').style.display = 'none';
    };

        const guardarCambios = () => {
        const campos = ['nombre', 'apellido', 'nombre_usuario', 'telefono', 'sexo', 'email-usuario'];
        const datosNuevos = {};

        campos.forEach(campo => {
        const input = document.getElementById(`input-${campo}`);
        if (input.value.trim() !== '') {
        datosNuevos[campo] = input.value.trim();
    } else {
        alert(`El campo ${campo} no puede estar vacío`);
        return;
    }
    });

        datosNuevos.id = usuarioId;
        datosNuevos.fotoPerfil = document.getElementById("foto-perfil").src;

        fetch('http://localhost:8080/api/usuario/actualizar', {
        method: 'POST',
        headers: {
        'Content-Type': 'application/json',
    },
        body: JSON.stringify(datosNuevos)
    })
        .then(response => response.json())
        .then(response => {
        if (response.id) {
        cargarDatosUsuario();
        document.querySelector('.boton-modificar').style.display = 'inline-block';
        document.querySelector('.boton-guardar').style.display = 'none';
        document.querySelector('.boton-cancelar').style.display = 'none';
    } else {
        alert('Error al actualizar los datos');
    }
    })
        .catch(error => {
        console.error('Error al actualizar los datos:', error);
        alert('Error al actualizar los datos. Por favor, inténtalo de nuevo más tarde.');
    });
    };

        const cargarFoto = (input) => {
        if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
        document.getElementById('foto-perfil').src = e.target.result;
    };
        reader.readAsDataURL(input.files[0]);
    }
    };
</script>
</body>

</html>