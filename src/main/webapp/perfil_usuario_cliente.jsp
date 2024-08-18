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
            margin-top: 240px;
            width: 100%;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 15px;
            position: relative;
        }

        .container-login100::before {
            margin-top: -240px;
            content: "";
            display: block;
            position: absolute;
            z-index: -1;
            width: 100%;
            height: 1500px;
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
                        <div>
                            <span>Nombres: </span><span id="nombre" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Apellidos: </span><span id="apellido" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Nombre usuario: </span><span id="nombre_usuario" class="font-weight-bold"></span>
                        </div>
                        <div>
                            <span>Teléfono: </span><span id="telefono" class="font-weight-bold"></span>
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
        console.log(usuarioId);
        fetch(`cargarUsuario/${usuarioId}`)  // Asegúrate de que esta sea la ruta correcta de tu API
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al cargar los datos del usuario');
                }
                return response.text();  // Cambiado a texto para usar un DOMParser
            })
            .then(data => {
                // Si la respuesta es HTML, parsear el texto
                const parser = new DOMParser();
                const doc = parser.parseFromString(data, 'text/html');

                // Suponiendo que el HTML de respuesta tiene los datos en elementos específicos
                const nombre = doc.querySelector('#nombre').textContent;
                const apellido = doc.querySelector('#apellido').textContent;
                const nombreUsuario = doc.querySelector('#nombre_usuario').textContent;
                const telefono = doc.querySelector('#telefono').textContent;
                const correo = doc.querySelector('#correo').textContent; // Asegúrate de que el nombre coincida con tu respuesta

                // Actualizar el DOM
                document.getElementById("nombre").textContent = nombre;
                document.getElementById("apellido").textContent = apellido;
                document.getElementById("nombre_usuario").textContent = nombreUsuario;
                document.getElementById("telefono").textContent = telefono;
                document.getElementById("email-usuario").textContent = correo;

                // Guardar los datos originales
                datosOriginales = {
                    nombre,
                    apellido,
                    nombreUsuario,
                    telefono,
                    correo
                };
            })
            .catch(error => {
                console.error('Error al cargar los datos del usuario:', error);
                alert('Error al cargar los datos del usuario. Por favor, inténtalo de nuevo más tarde.');
            });
    };



    const habilitarEdicion = () => {
        const campos = ['nombre', 'apellido', 'nombre_usuario', 'telefono', 'email-usuario'];
        campos.forEach(campo => {
            const span = document.getElementById(campo);
            let input = document.createElement('input');
            input.id = `input-${campo}`;
            input.type = 'text';
            input.value = span.textContent;
            input.classList.add('input100');
            span.parentNode.replaceChild(input, span);
        });
        document.querySelector('.boton-modificar').style.display = 'none';
        document.querySelector('.boton-guardar').style.display = 'inline-block';
        document.querySelector('.boton-cancelar').style.display = 'inline-block';
    };

    const cancelarCambios = () => {
        const campos = ['nombre', 'apellido', 'nombre_usuario', 'telefono', 'email-usuario'];
        campos.forEach(campo => {
            const input = document.getElementById(`input-${campo}`);
            const span = document.createElement('span');
            span.id = campo;
            span.classList.add('font-weight-bold');
            span.textContent = datosOriginales[campo];
            input.parentNode.replaceChild(span, input);
        });
        document.querySelector('.boton-modificar').style.display = 'inline-block';
        document.querySelector('.boton-guardar').style.display = 'none';
        document.querySelector('.boton-cancelar').style.display = 'none';
    };

    const guardarCambios = () => {
        const campos = ['nombre', 'apellido', 'nombre_usuario', 'telefono', 'email-usuario'];
        let datosActualizados = {};

        campos.forEach(campo => {
            const input = document.getElementById(`input-${campo}`);
            datosActualizados[campo] = input.value;
        });

        fetch(`usuarioId${usuarioId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datosActualizados)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error al guardar los cambios');
                }
                return response.json();
            })
            .then(data => {
                datosOriginales = data;  // Actualizar los datos originales
                cancelarCambios();
                alert('Los cambios se guardaron correctamente.');
            })
            .catch(error => {
                console.error('Error al guardar los cambios:', error);
                alert('Error al guardar los cambios. Por favor, inténtalo de nuevo más tarde.');
            });
    };

</script>
</body>

</html>
