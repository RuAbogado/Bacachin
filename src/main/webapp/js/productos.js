// Obtener elementos del DOM
const btnAgregarProducto = document.getElementById('add-product-btn');
const modalProducto = document.getElementById('productModal');
const spanCerrarProducto = modalProducto.getElementsByClassName('close-product')[0];

// Mostrar modales
btnAgregarProducto.onclick = function() {
    modalProducto.style.display = "block";
}

// Cerrar modales
spanCerrarProducto.onclick = function() {
    modalProducto.style.display = "none";
}

// Cerrar modal cuando se hace clic fuera de él
window.onclick = function(event) {
    if (event.target === modalProducto) {
        modalProducto.style.display = "none";
    }
}

// Agregar un nuevo producto
const formAgregarProducto = document.getElementById('form-agregar-producto');
formAgregarProducto.addEventListener('submit', async function(event) {
    event.preventDefault();
    console.log("Enviando producto para agregar")



    const nombre = document.querySelector('[name="nombre"]').value;
    const descripcion = document.querySelector('[name="descripcion"]').value;
    const precio = document.querySelector('[name="precio"]').value;
    const stock = document.querySelector('[name="stock"]').value;
    const idCategoria = document.querySelector('[name="ID_Categoria"]').value;
    const tipo = ' ';
    const imagen = document.querySelector('[name="imagen"]').files[0];
    const categoria = idCategoria;
    const modalProducto = document.getElementById('productModal');

    if (!nombre || !descripcion || isNaN(parseFloat(precio)) || isNaN(parseInt(stock)) || !idCategoria || !imagen) {
        alert('Por favor complete todos los campos requeridos.');
        return;
    }

    let formData = new FormData();
    formData.append('nombre', nombre);
    formData.append('descripcion', descripcion);
    formData.append('precio', parseFloat(precio));
    formData.append('stock', parseInt(stock));
    formData.append('ID_Categoria', idCategoria);
    formData.append('Tipo', tipo);
    formData.append('imagen', imagen);

    try {
        const response = await fetch('AgregarProducto', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.text();
        const parser = new DOMParser();
        const doc = parser.parseFromString(data, 'text/html');
        const success = doc.querySelector('h1') && doc.querySelector('h1').textContent.includes('exitosamente');

        if (success) {
            const nuevoProducto = document.createElement('div');
            // Asumiendo que `nuevoProducto` es un elemento de producto que se está creando
            nuevoProducto.className = 'producto'; // Cambiado a 'producto' para coincidir con el CSS
            nuevoProducto.innerHTML = `
    <div class="producto-content">
        <img src="${URL.createObjectURL(imagen)}" class="imagen-curso" alt="${nombre}">
        <div class="info-card">
            <h5 class="card-title">${nombre}</h5>
            <p class="card-text">${descripcion}</p>
            <p class="card-text text-muted">${stock} <span class="u-pull-right">$${precio}</span></p>
            <a href="#" class="boton-cancelar button input eliminar-producto" data-id="${Date.now()}">Eliminar Producto</a>
        </div>
    </div>
`;

            document.getElementById(categoria).appendChild(nuevoProducto);

            modalProducto.style.display = "none";
            formAgregarProducto.reset();
            cargarEventListenersEliminar();
        } else {
            alert('Error al agregar el producto.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error en la solicitud.');
    }
});

// Deshabilitar una categoría
function DeshabilitarProducto(ID_Producto) {
    if (!confirm('¿Estás seguro de que quieres deshabilitar esta categoría?')) {
        return;
    }

    fetch('deshabilitarProducto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Producto=${encodeURIComponent(ID_Producto)}`
    })
        .then(response => response.json()) // Se espera una respuesta en formato JSON
        .then(data => {
            if (!data.success) {
                alert('Error al deshabilitar el producto.');
            } else {
                window.location.reload();
                alert('El producto se deshabilitó correctamente.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// habilitar una categoría
function HabilitarProducto(ID_Producto) {
    if (!confirm('¿Estás seguro de que quieres habilitar esta categoría?')) {
        return;
    }

    fetch('habilitarProducto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Producto=${encodeURIComponent(ID_Producto)}`
    })
        .then(response => response.json()) // Se espera una respuesta en formato JSON
        .then(data => {
            if (!data.success) {
                alert('Error al habilitar el producto.');
            } else {
                window.location.reload();
                alert('El producto se habilitó correctamente.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// Habilitar una categoría
function HabilitarCategoria(Categorias_id) {
    if (!confirm('¿Estás seguro de que quieres habilitar esta categoría?')) {
        return;
    }


    fetch('habilitarCategoria', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Categoria=${encodeURIComponent(Categorias_id)}`
    })
        .then(response => response.json()) // Se espera una respuesta en formato JSON
        .then(data => {
            if (!data.success) {
                alert('Error al habilitar la categoría.');
            } else {
                window.location.reload();
                alert('La categoría se habilitó correctamente.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// habilitar una marca
function HabilitarMarca(Marcas_ID) {
    if (!confirm('¿Estás seguro de que quieres habilitar esta Marca?')) {
        return;
    }

    fetch('habilitarMarca', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Marcas=${encodeURIComponent(Marcas_ID)}`
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