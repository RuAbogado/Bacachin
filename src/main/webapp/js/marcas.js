// Obtener elementos del DOM
const btnAgregarmarca = document.getElementById('add-marca-btn');
const modalmarca = document.getElementById('marcaModal');
const spanCerrarmarca = modalmarca.getElementsByClassName('close-marca')[0];


// Mostrar modales
btnAgregarmarca.onclick = function() {
    modalmarca.style.display = "block";
}

// Cerrar modales
spanCerrarmarca.onclick = function() {
    modalmarca.style.display = "none";
}

// Cerrar modal cuando se hace clic fuera de él
window.onclick = function(event) {
    if (event.target === modalmarca) {
        modalmarca.style.display = "none";
    }
}

// Agregar una nueva marca
document.getElementById('marca-form').addEventListener('submit', function(e) {
    e.preventDefault();

    const newmarca = document.getElementById('new-marca').value;
    const description = document.getElementById('marca-description').value;

    fetch('AgregarMarca', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `nombre=${encodeURIComponent(newmarca)}&descripcion=${encodeURIComponent(description)}`
    })
        .then(response => response.text())
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const success = doc.querySelector('h1').textContent.includes('exitosamente');

            if (success) {
                // Recargar el iframe que contiene marcas.jsp
                window.location.reload();
            } else {
                alert('Error al agregar la marca a la lista.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud para agregar la marca en la lista.');
        });
});

// Deshabilitar una marca
function DeshabilitarMarca(ID_Marcas) {
    if (!confirm('¿Estás seguro de que quieres deshabilitar esta Marca?')) {
        return;
    }

    // Obtener la fila de la tabla correspondiente a la marca
    const marca = document.getElementById(`marca-${ID_Marcas}`);
    if (marca) {
        // Añadir la clase 'deshabilitada' para simular que no está disponible
        marca.classList.add('deshabilitada');
    }

    fetch('deshabilitarMarca', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Marcas=${encodeURIComponent(ID_Marcas)}`
    })
        .then(response => response.json()) // Se espera una respuesta en formato JSON
        .then(data => {
            if (!data.success) {
                alert('Error al deshabilitar la marca.');
            } else {
                window.location.reload();
                alert('La marca se deshabilitó correctamente.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// habilitar una marca
function HabilitarMarca(ID_Marcas) {
    if (!confirm('¿Estás seguro de que quieres habilitar esta Marca?')) {
        return;
    }

    fetch('habilitarMarca', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Marcas=${encodeURIComponent(ID_Marcas)}`
    })
        .then(response => response.json()) // Se espera una respuesta en formato JSON
        .then(data => {
            if (!data.success) {
                alert('Error al habilitar la marca.');
            } else {
                window.location.reload();
                alert('La marca se habilitó correctamente.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}