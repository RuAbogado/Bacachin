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
/*
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
    const idMarca = document.querySelector('[name="ID_Marca"]').value;
    const tipo = ' ';
    const imagen = document.querySelector('[name="imagen"]').files[0];

    if (!nombre || !descripcion || isNaN(parseFloat(precio)) || isNaN(parseInt(stock)) || !idCategoria || !imagen || !idMarca) {
        alert('Por favor complete todos los campos requeridos.');
        return;
    }

    let formData = new FormData();
    formData.append('ID_Categoria', idCategoria);
    formData.append('ID_Marca', idMarca);
    formData.append('nombre', nombre);
    formData.append('descripcion', descripcion);
    formData.append('precio', parseFloat(precio));
    formData.append('stock', parseInt(stock));
    formData.append('imagen', imagen);

    if (!idCategoria || !idMarca || !nombre || !descripcion || isNaN(parseFloat(precio)) || isNaN(parseInt(stock)) || !imagen) {
        alert('Por favor complete todos los campos requeridos.');
        return;
    }

    try {
        //Fetch
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
            window.location.reload();
            alert('El producto se habilitó correctamente.');
        } else {
            alert('Error al agregar el producto.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error en la solicitud.');
    }
});*/

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
        .then(response => response.text())
        .then(data => {
            console.log(data)
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const success = doc.querySelector('h1').textContent.includes('exitosamente');

            if (success) {
                window.location.reload();
            } else {
                alert('Error al agregar el producto a la lista.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud para agregar el producto en la lista.');
        });
});

/*
// Agregar una nueva categoría
document.getElementById('category-form').addEventListener('submit', function(e) {
    e.preventDefault();

    const nombre = document.querySelector('[name="nombre"]').value;
    const descripcion = document.querySelector('[name="descripcion"]').value;
    const precio = document.querySelector('[name="precio"]').value;
    const stock = document.querySelector('[name="stock"]').value;
    const idCategoria = document.querySelector('[name="ID_Categoria"]').value;
    const tipo = ' ';
    const imagen = document.querySelector('[name="imagen"]').files[0];
    const categoria = idCategoria;
    const modalProducto = document.getElementById('productModal');

    fetch('AgregarCategoria', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `nombre=${encodeURIComponent(newCategory)}&descripcion=${encodeURIComponent(description)}`
    })
        .then(response => response.text())
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const success = doc.querySelector('h1').textContent.includes('exitosamente');
            const ID_Categoria = doc.querySelector('input[name="ID_Categoria"]').value;

            if (success) {
                // Recargar el iframe que contiene categorias.jsp
                fetch('habilitarCategoria', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: `ID_Categoria=${encodeURIComponent(ID_Categoria)}`
                })
                    .then(response => response.json()) // Se espera una respuesta en formato JSON
                    .then(data => {
                        if (!data.success) {
                            alert('Error al habilitar la categoría.');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('Error en la solicitud.');
                    });
                window.location.reload();
            } else {
                alert('Error al agregar la categoría a la lista.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud para agregar la categoria en la lista.');
        });
});*/


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