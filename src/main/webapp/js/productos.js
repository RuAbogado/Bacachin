// Obtener elementos del DOM
const btnAgregarProducto = document.getElementById('add-product-btn');
const btnAgregarCategoria = document.getElementById('add-category-btn');
const modalProducto = document.getElementById('productModal');
const modalCategoria = document.getElementById('categoryModal');
const spanCerrarProducto = modalProducto.getElementsByClassName('close-product')[0];
const spanCerrarCategoria = modalCategoria.getElementsByClassName('close-category')[0];

// Función para mostrar el modal de agregar producto
btnAgregarProducto.onclick = function() {
    modalProducto.style.display = "block";
}

// Función para mostrar el modal de agregar categoría
btnAgregarCategoria.onclick = function() {
    modalCategoria.style.display = "block";
}

// Función para cerrar el modal de agregar producto
spanCerrarProducto.onclick = function() {
    modalProducto.style.display = "none";
}

// Función para cerrar el modal de agregar categoría
spanCerrarCategoria.onclick = function() {
    modalCategoria.style.display = "none";
}

// Función para cerrar los modales cuando se hace clic fuera de ellos
window.onclick = function(event) {
    if (event.target == modalProducto) {
        modalProducto.style.display = "none";
    }
    if (event.target == modalCategoria) {
        modalCategoria.style.display = "none";
    }
}

// Función para cargar las categorías existentes desde el servidor
function cargarCategorias() {
    fetch('ObtenerCategorias')
        .then(response => response.text()) // Procesar como texto en lugar de JSON
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const categorias = doc.querySelectorAll('.categoria-container');

            categorias.forEach(categoria => {
                document.getElementById('productos-container').appendChild(categoria);
            });

            cargarEventListenersEliminar(); // Cargar los event listeners de eliminar
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar las categorías.');
        });
}

// Llamar a la función para cargar las categorías al cargar la página
document.addEventListener('DOMContentLoaded', cargarCategorias);

// Event listener para agregar producto
const formAgregarProducto = document.getElementById('form-agregar-producto');
formAgregarProducto.addEventListener('submit', function(event) {
    event.preventDefault();

    const imagen = document.getElementById('imagen-producto').files[0];
    const nombre = document.getElementById('nombre-producto').value;
    const descripcion = document.getElementById('descripcion-producto').value;
    const precio = document.getElementById('precio-producto').value;
    const stock = document.getElementById('stock-producto').value;
    const categoria = document.getElementById('categoria-producto').value;

    const formData = new FormData();
    formData.append('nombre', nombre);
    formData.append('descripcion', descripcion);
    formData.append('precio', precio);
    formData.append('stock', stock);
    formData.append('ID_Categoria', categoria); // Cambiar a ID_Categoria
    if (imagen) {
        formData.append('imagen', imagen);
    }

    fetch('AgregarProducto', {
        method: 'POST',
        body: formData
    })
        .then(response => response.text()) // Procesar como texto en lugar de JSON
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const success = doc.querySelector('h1').textContent.includes('exitosamente');

            if (success) {
                const nuevoProducto = document.createElement('div');
                nuevoProducto.className = 'col-md-3 producto';
                nuevoProducto.innerHTML = `
                <div>
                    <div class="four columns">
                        <img src="${URL.createObjectURL(imagen)}" class="imagen-curso u-full-width" alt="${nombre}">
                        <div class="info-card">
                            <h5 class="card-title">${nombre}</h5>
                            <p class="card-text">${descripcion}</p>
                            <p class="card-text text-muted">${stock} <span class="u-pull-right">$${precio}</span></p>
                            <a href="#" class="u-full-width boton-cancelar button input eliminar-producto" data-id="${Date.now()}">Eliminar Producto</a>
                        </div>
                    </div>
                </div>
            `;
                document.getElementById(categoria).appendChild(nuevoProducto);

                modalProducto.style.display = "none";
                formAgregarProducto.reset();

                cargarEventListenersEliminar(); // Cargar los event listeners de eliminar
            } else {
                alert('Error al agregar el producto.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
});

// Event listener para agregar categoría
document.getElementById('category-form').addEventListener('submit', function(e) {
    e.preventDefault();

    const newCategory = document.getElementById('new-category').value;
    const description = document.getElementById('category-description').value;

    fetch('AgregarCategoria', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `nombre=${encodeURIComponent(newCategory)}&descripcion=${encodeURIComponent(description)}`
    })
        .then(response => response.text()) // Procesar como texto en lugar de JSON
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const success = doc.querySelector('h1').textContent.includes('exitosamente');
            const ID_Categoria = doc.querySelector('input[name="ID_Categoria"]').value;

            if (success) {
                const select = document.getElementById('categoria-producto');
                const option = document.createElement('option');
                option.value = ID_Categoria;
                option.text = newCategory;
                select.add(option);

                const newCategoryContainer = document.createElement('div');
                newCategoryContainer.className = 'categoria-container mb-5';
                newCategoryContainer.id = `categoria-${ID_Categoria}`;
                newCategoryContainer.innerHTML = `
                <div class="categoria-header">
                    <h1 class="encabezado">${newCategory}</h1>
                    <div class="container-btn">
                        <button class="boton-agregar" onclick="mostrarFormulario(${ID_Categoria})">Agregar ${newCategory}</button>
                        <button class="boton-eliminar" onclick="eliminarCategoria(${ID_Categoria})">Eliminar Categoría</button>
                    </div>
                </div>
                <div class="row" id="${ID_Categoria}"></div>
            `;
                document.getElementById('productos-container').appendChild(newCategoryContainer);

                modalCategoria.style.display = "none";
                document.getElementById('new-category').value = '';
                document.getElementById('category-description').value = '';
            } else {
                alert('Error al agregar la categoría.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
});

// Función para eliminar categoría
function eliminarCategoria(ID_Categoria) {
    if (!confirm('¿Estás seguro de que quieres eliminar esta categoría?')) {
        return;
    }

    const categoria = document.getElementById(`categoria-${ID_Categoria}`);
    if (categoria) {
        categoria.parentNode.removeChild(categoria);
    }

    const select = document.getElementById('categoria-producto');
    for (let i = 0; i < select.options.length; i++) {
        if (select.options[i].value == ID_Categoria) {
            select.remove(i);
            break;
        }
    }

    fetch('EliminarCategoria', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Categoria=${encodeURIComponent(ID_Categoria)}`
    })
        .then(response => response.text()) // Procesar como texto en lugar de JSON
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const success = doc.querySelector('h1').textContent.includes('eliminado');

            if (!success) {
                alert('Error al eliminar la categoría.');
                // Opcional: Puedes volver a mostrar la categoría en caso de error
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// Función para mostrar el formulario y establecer la categoría
function mostrarFormulario(categoria) {
    modalProducto.style.display = "block";
    document.getElementById('categoria-producto').value = categoria;
}

// Event listener para eliminar producto
function cargarEventListenersEliminar() {
    const botonesEliminar = document.querySelectorAll('.eliminar-producto');
    botonesEliminar.forEach(boton => {
        boton.addEventListener('click', function (e) {
            e.preventDefault();
            const producto = this.closest('.producto');
            const productoId = producto.getAttribute('data-id');

            fetch('EliminarProducto', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `ID_Producto=${encodeURIComponent(productoId)}`
            })
                .then(response => response.text()) // Procesar como texto en lugar de JSON
                .then(data => {
                    const parser = new DOMParser();
                    const doc = parser.parseFromString(data, 'text / html');
                    const success = doc.querySelector('h1')
                    textContent.includes('eliminado')
                    ;
                    if (success) {
                        producto.remove();
                    } else {
                        alert('Error al eliminar el producto.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Error en la solicitud.');
                });
        });
    });
}