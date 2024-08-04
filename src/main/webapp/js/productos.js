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

function removeAllChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

// Función para cargar las categorías existentes desde el servidor
function cargarCategorias(){
    fetch('ObtenerCategorias')
        .then(response => response.text()) // Procesar como texto en lugar de JSON
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const categorias = doc.querySelectorAll('option');

            removeAllChildren(document.getElementById('productos-container'))
            categorias.forEach(category => {
                const newCategoryContainer = document.createElement('div');
                newCategoryContainer.className = 'categoria-container mb-5';
                newCategoryContainer.id = `categoria-${category.value}`;
                newCategoryContainer.innerHTML = `
                <div class="categoria-header">
                    <h1 class="encabezado">${category.text}</h1>
                    <div class="container-btn">
                        <button class="boton-agregar" onclick="mostrarFormulario(${category.value})">Agregar ${category.text}</button>
                        <button class="boton-eliminar" onclick="eliminarCategoria(${category.value})">Eliminar Categoría</button>
                    </div>
                </div>
                <div class="row" id="${category.value}"></div>
            `;
                document.getElementById('productos-container').appendChild(newCategoryContainer);
            })

            categorias.forEach(categoria => {
                document.getElementById('categoria-producto').appendChild(categoria);
            });

            cargarEventListenersEliminar(); // Cargar los event listeners de eliminar
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar las categorías.');
        });
}

// Llamar a la función para cargar las categorías al cargar la página

// No puede obtener la lista de categorias aparece un error de CORS
// pero al ingresar a la pagina de productos y cargar productos.jsp y productos.js
// si obtiene la informacion y no hay error de CORS
document.addEventListener('DOMContentLoaded', function() {
    cargarCategorias()
});

// Event listener para agregar producto
const formAgregarProducto = document.getElementById('form-agregar-producto');
formAgregarProducto.addEventListener('submit', async function(event) {
    event.preventDefault();

    // Obtener valores del formulario
    const nombre = document.querySelector('[name="nombre"]').value;
    const descripcion = document.querySelector('[name="descripcion"]').value;
    const precio = document.querySelector('[name="precio"]').value;
    const stock = document.querySelector('[name="stock"]').value;
    const idCategoria = document.querySelector('[name="categoria"]').value;
    const tipo = ''; // Aquí debes definir cómo obtener el tipo, si es necesario
    const imagen = document.querySelector('[name="imagen"]').files[0];
    const categoria = idCategoria; // O el ID del contenedor de la categoría
    const modalProducto = document.getElementById('productModal'); // Asumiendo que el modal tiene este ID

    // Verificar que todos los campos necesarios estén presentes
    if (!nombre || !descripcion || isNaN(parseFloat(precio)) || isNaN(parseInt(stock)) || !idCategoria || !imagen) {
        alert('Por favor complete todos los campos requeridos.');
        return;
    }

    // Crear FormData con todos los datos necesarios
    let formData = new FormData();
    formData.append('nombre', nombre);
    formData.append('descripcion', descripcion);
    formData.append('precio', parseFloat(precio)); // Asegúrate de que el precio sea un float
    formData.append('stock', parseInt(stock)); // Asegúrate de que el stock sea un entero
    formData.append('ID_Categoria', idCategoria);
    formData.append('Tipo', tipo);
    formData.append('imagen', imagen);

    try {
        // Realizar la solicitud fetch
        const response = await fetch('AgregarProducto', {
            method: 'POST',
            body: formData
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.text(); // Procesar como texto

        // Analizar la respuesta HTML
        const parser = new DOMParser();
        const doc = parser.parseFromString(data, 'text/html');
        const success = doc.querySelector('h1') && doc.querySelector('h1').textContent.includes('exitosamente');

        // Verificar si el producto se agregó exitosamente
        if (success) {
            // Crear y agregar el nuevo producto al DOM
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

            // Ocultar el modal y resetear el formulario
            modalProducto.style.display = "none";
            formAgregarProducto.reset();

            // Cargar los event listeners de eliminar
            cargarEventListenersEliminar();
        } else {
            alert('Error al agregar el producto.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error en la solicitud.');
    }
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
