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

document.getElementById('form-agregar-producto').addEventListener('submit', function(e) {
    e.preventDefault();

    const nombre = document.querySelector('[name="nombre"]').value;
    const descripcion = document.querySelector('[name="descripcion"]').value;
    const precio = document.querySelector('[name="precio"]').value;
    const stock = document.querySelector('[name="stock"]').value;
    const idCategoria = document.querySelector('[name="ID_Categoria"]').value;
    const idMarca = document.querySelector('[name="ID_Marca"]').value;
    const imagen = document.querySelector('[name="imagen"]').files[0];

    if (!nombre || !descripcion || isNaN(parseFloat(precio)) || isNaN(parseInt(stock)) || !idCategoria || !imagen || !idMarca) {
        alert('Por favor complete todos los campos requeridos.');
        return;
    }

    // Crear un FormData
    let formData = new FormData();
    formData.append('ID_Categoria', idCategoria);
    formData.append('ID_Marca', idMarca);
    formData.append('nombre', nombre);
    formData.append('descripcion', descripcion);
    formData.append('precio', parseFloat(precio));
    formData.append('stock', parseInt(stock));
    formData.append('imagen', imagen);

    fetch('AgregarProducto', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json()) // Parsear la respuesta como JSON
        .then(data => {
            if (data.status === "success") {
                alert('Producto agregado exitosamente.');
                window.location.reload();
            } else {
                alert('Error al agregar el producto: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud para agregar el producto en la lista.');
        });
});

// Deshabilitar un producto
function DeshabilitarProducto(ID_Producto) {
    if (!confirm('¿Estás seguro de que quieres deshabilitar este producto?')) {
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
                alert('El producto se deshabilitó correctamente.');
                window.location.reload();
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// Habilitar un producto
function HabilitarProducto(ID_Producto) {
    if (!confirm('¿Estás seguro de que quieres habilitar este producto?')) {
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
                alert('El producto se habilitó correctamente.');
                window.location.reload();
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
                alert('La categoría se habilitó correctamente.');
                window.location.reload();
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// Habilitar una marca
function HabilitarMarca(Marcas_ID) {
    if (!confirm('¿Estás seguro de que quieres habilitar esta marca?')) {
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
                alert('La marca se habilitó correctamente.');
                window.location.reload();
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}