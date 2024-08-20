// Mostrar el formulario
document.getElementById("agregar-empleado").addEventListener("click", function() {
    document.getElementById("formularioContainer").style.display = "flex";
});

// Cerrar el formulario
document.getElementById("closeForm").addEventListener("click", function() {
    document.getElementById("formularioContainer").style.display = "none";
});

// Cerrar el formulario si se hace clic fuera del formulario
window.addEventListener("click", function(event) {
    if (event.target === document.getElementById("formularioContainer")) {
        document.getElementById("formularioContainer").style.display = "none";
    }
});

function validarYEnviar() {
    var nombre = document.getElementById("nombre").value.trim();
    var apellido = document.getElementById("apellido").value.trim();
    var usuario = document.getElementById("usuario").value.trim();
    var telefono = document.getElementById("telefono").value.trim();
    var correo = document.getElementById("correo").value.trim();
    var contraseña = document.getElementById("contraseña").value;
    var confirmarContraseña = document.getElementById("confirmarContraseña").value;

    // Validar si algún campo está vacío
    if (nombre === "" || apellido === "" || usuario === "" || telefono === "" || correo === "" || contraseña === "" || confirmarContraseña === "") {
        alert("Por favor, rellene todos los campos.");
        return false; // Detener el envío del formulario si algún campo está vacío
    }

    // Validar si hay espacios en blanco en cualquier campo
    if (nombre.includes(" ") || telefono.includes(" ") || correo.includes(" ")) {
        alert("No se permiten espacios en blanco en ningún campo.");
        return false; // Detener el envío del formulario si se encuentra un espacio en blanco
    }

    // Validar si las contraseñas coinciden
    if (contraseña !== confirmarContraseña) {
        alert("Las contraseñas no coinciden");
        return false; // Detener el envío del formulario si las contraseñas no coinciden
    }

    // Si pasa todas las validaciones, enviar el formulario
    return true;
}

function borrarRegistro() {
    var confirmacion = confirm("¿Estás seguro de que deseas vaciar todos los registros? Esta acción no se puede deshacer.");

    if (confirmacion) {
        localStorage.clear();
        location.reload();
    }
}

// Función para deshabilitar un empleado
function DeshabilitarUsuario(ID_Usuario) {
    if (confirm('¿Estás seguro de que deseas deshabilitar este empleado?')) {
        fetch('deshabilitarUsuario', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `ID_Usuario=${encodeURIComponent(ID_Usuario)}`
        })

            .then(response => {
                if (response.ok) {
                    alert('Empleado deshabilitado correctamente.');
                    location.reload(); // Recargar la página para actualizar la lista de empleados
                } else {
                    alert('Hubo un problema al deshabilitar el empleado.');
                }
            })
            .catch(error => {
                alert('Error en la solicitud: ' + error);
            });
    }
}

function HabilitarUsuario(ID_Usuario) {
    if (confirm('¿Estás seguro de que deseas habilitar este empleado?')) {
        fetch('habilitarUsuario', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `ID_Usuario=${encodeURIComponent(ID_Usuario)}`
        })

            .then(response => {
                if (response.ok) {
                    alert('Empleado habilitado correctamente.');
                    location.reload(); // Recargar la página para actualizar la lista de empleados
                } else {
                    alert('Hubo un problema al habilitar el empleado.');
                }
            })
            .catch(error => {
                alert('Error en la solicitud: ' + error);
            });
    }
}