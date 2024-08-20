
function DeshabilitarUsuario(ID_Usuario) {
    if (confirm('¿Estás seguro de que deseas deshabilitar este cliente?')) {
        fetch('deshabilitarUsuario', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `ID_Usuario=${encodeURIComponent(ID_Usuario)}`
        })

            .then(response => {
                if (response.ok) {
                    alert('Cliente deshabilitado correctamente.');
                    location.reload(); // Recargar la página para actualizar la lista de empleados
                } else {
                    alert('Hubo un problema al deshabilitar el cliente.');
                }
            })
            .catch(error => {
                alert('Error en la solicitud: ' + error);
            });
    }
}

function HabilitarUsuario(ID_Usuario) {
    if (confirm('¿Estás seguro de que deseas habilitar este cliente?')) {
        fetch('habilitarUsuario', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `ID_Usuario=${encodeURIComponent(ID_Usuario)}`
        })

            .then(response => {
                if (response.ok) {
                    alert('Cliente habilitado correctamente.');
                    location.reload(); // Recargar la página para actualizar la lista de empleados
                } else {
                    alert('Hubo un problema al habilitar el cliente.');
                }
            })
            .catch(error => {
                alert('Error en la solicitud: ' + error);
            });
    }
}