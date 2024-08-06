// Obtener elementos del DOM
const btnAgregarProducto = document.getElementById('add-product-btn');
const btnAgregarCategoria = document.getElementById('add-category-btn');
const modalProducto = document.getElementById('productModal');
const modalCategoria = document.getElementById('categoryModal');
const spanCerrarProducto = modalProducto.getElementsByClassName('close-product')[0];
const spanCerrarCategoria = modalCategoria.getElementsByClassName('close-category')[0];

// Mostrar modales
btnAgregarProducto.onclick = function() {
    modalProducto.style.display = "block";
}

btnAgregarCategoria.onclick = function() {
    modalCategoria.style.display = "block";
}

// Cerrar modales
spanCerrarProducto.onclick = function() {
    modalProducto.style.display = "none";
}

spanCerrarCategoria.onclick = function() {
    modalCategoria.style.display = "none";
}

// Cerrar modal cuando se hace clic fuera de él
window.onclick = function(event) {
    if (event.target == modalProducto) {
        modalProducto.style.display = "none";
    }
    if (event.target == modalCategoria) {
        modalCategoria.style.display = "none";
    }
}

// Eliminar todos los hijos de un elemento
function removeAllChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

// Cargar categorías desde el servidor
function cargarCategorias(){
    fetch('ObtenerCategorias')
        .then(response => response.text())
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
            });

            categorias.forEach(categoria => {
                document.getElementById('categoria-producto').appendChild(categoria);
            });

            cargarEventListenersEliminar();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar las categorías.');
        });
}

document.addEventListener('DOMContentLoaded', function() {
    cargarCategorias()
});

// Agregar un nuevo producto
const formAgregarProducto = document.getElementById('form-agregar-producto');
formAgregarProducto.addEventListener('submit', async function(event) {
    event.preventDefault();

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
            cargarEventListenersEliminar();
        } else {
            alert('Error al agregar el producto.');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error en la solicitud.');
    }
});

// Agregar una nueva categoría
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
        .then(response => response.text())
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

// Eliminar una categoría
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


    //Si tengo un formulario con imagenes
    //agarra el formulario en una var
    //luego hacer un
    //var datos= new FormData(x)
    fetch('EliminarCategoria', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Categoria=${encodeURIComponent(ID_Categoria)}`
    })
        .then(response => response.text())
        .then(data => {
            data = JSON.parse(data);

            if (!data.success) {
                alert('Error al eliminar la categoría.');
            }else{
                alert('La categoria se elimino correctamente.')
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// Mostrar formulario para agregar producto en una categoría específica
function mostrarFormulario(categoria) {
    modalProducto.style.display = "block";
    document.getElementById('categoria-producto').value = categoria;
}

// Eliminar un producto
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
                .then(response => response.text())
                .then(data => {
                    const parser = new DOMParser();
                    const doc = parser.parseFromString(data, 'text/html');
                    const success = doc.querySelector('h1').textContent.includes('eliminado');
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


    const productosContainer = document.getElementById('productos-container');

        // Función para cargar productos desde el servlet
        function cargarProductos() {
            fetch('CargarProductos')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('internet no responde');
                    }
                    return response.json();
                })
                .then(data => {
                    mostrarProductosPorCategoria(data);
                })
                .catch(error => {
                    console.error('Ocurrio un error con el fetch', error);
                });
        }

        // Función para mostrar los productos organizados por categoría
    function mostrarProductosPorCategoria(productos) {
        productosContainer.innerHTML = '';

        // Agrupamos productos por categoría
        const categorias = productos.reduce((acc, producto) => {
            if (!acc[producto.categoria]) {
                acc[producto.categoria] = [];
            }
            acc[producto.categoria].push(producto);
            return acc;
        }, {});

        // Mostrar productos por categoría
        for (const categoria in categorias) {
            const productosPorCategoria = categorias[categoria];
            const categoriaDiv = document.createElement('div');
            categoriaDiv.className = 'categoria';
            categoriaDiv.innerHTML = `<h2>${categoria}</h2>`;

            productosPorCategoria.forEach(producto => {
                const productoDiv = document.createElement('div');
                productoDiv.className = 'producto';
                productoDiv.innerHTML = `
            <h3>${producto.nombre}</h3>
            <p>${producto.descripcion}</p>
            <p class="precio">Precio: $${producto.precio.toFixed(2)}</p>
            <p>Stock: ${producto.stock}</p>
            <img src="${producto.imagen}" alt="${producto.nombre}" class="img-fluid">
        `;
                categoriaDiv.appendChild(productoDiv);
            });

            productosContainer.appendChild(categoriaDiv);
        }
    }

}