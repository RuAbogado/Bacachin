// productos.html

const btnAgregarProducto = document.getElementById('btn-agregar-producto');
const btnAgregarBebida = document.getElementById('btn-agregar-bebida');
const formularioAgregarProducto = document.getElementById('agregar-producto');
const formAgregarProducto = document.getElementById('form-agregar-producto');

function cargarEventListeners() {
    if (btnAgregarProducto) {
        btnAgregarProducto.addEventListener('click', () => {
            formularioAgregarProducto.classList.toggle('d-none');
            document.getElementById('categoria-producto').value = 'productos';
        });
    }

    if (btnAgregarBebida) {
        btnAgregarBebida.addEventListener('click', () => {
            formularioAgregarProducto.classList.toggle('d-none');
            document.getElementById('categoria-producto').value = 'bebidas';
        });
    }

    if (formAgregarProducto) {
        formAgregarProducto.addEventListener('submit', function (event) {
            event.preventDefault();

            const imagen = document.getElementById('imagen-producto').files[0];
            const nombre = document.getElementById('nombre-producto').value;
            const descripcion = document.getElementById('descripcion-producto').value;
            const precio = document.getElementById('precio-producto').value;
            const stock = document.getElementById('stock-producto').value;
            const categoria = document.getElementById('categoria-producto').value;

            if (imagen) {
                const reader = new FileReader();
                reader.onload = function (e) {
                    const imagenURL = e.target.result;

                    const nuevoProducto = document.createElement('div');
                    nuevoProducto.className = 'col-md-3 producto';
                    nuevoProducto.innerHTML = `
                        <div>
                            <div class="four columns">
                                <img src="${imagenURL}" class="imagen-curso u-full-width" alt="${nombre}">
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

                    formularioAgregarProducto.classList.add('d-none');
                    formAgregarProducto.reset();

                    cargarEventListenersEliminar(); // Cargar los event listeners de eliminar
                };
                reader.readAsDataURL(imagen);
            }
            // Aquí se puede agregar la comunicación con el servidor para guardar el producto en la base de datos
        });
    }
}

function cargarEventListenersEliminar() {
    const botonesEliminar = document.querySelectorAll('.eliminar-producto');
    botonesEliminar.forEach(boton => {
        boton.addEventListener('click', (event) => {
            event.preventDefault();
            const producto = boton.closest('.producto');
            if (producto) {
                producto.remove();
            }
        });
    });
}

cargarEventListeners();

// Modal para nueva categoría
const modal = document.getElementById("categoryModal");
const btn = document.getElementById("add-category-btn");
const span = document.getElementsByClassName("close")[0];

// Mostrar el modal
btn.onclick = function() {
    modal.style.display = "block";
}

// Ocultar el modal
span.onclick = function() {
    modal.style.display = "none";
}

// Ocultar el modal si se hace clic fuera de él
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// Agregar nueva categoría al formulario y a la página
document.getElementById('category-form').addEventListener('submit', function(e) {
    e.preventDefault();
    const newCategory = document.getElementById('new-category').value;
    const select = document.getElementById('categoria-producto');
    const option = document.createElement('option');
    option.value = newCategory.toLowerCase();
    option.text = newCategory;
    select.add(option);

    // Crear nuevo contenedor de categoría
    const newCategoryContainer = document.createElement('div');
    newCategoryContainer.className = 'categoria-container mb-5';
    newCategoryContainer.id = `categoria-${newCategory.toLowerCase()}`;
    newCategoryContainer.innerHTML = `
        <div class="categoria-header">
            <h1 class="encabezado">${newCategory}</h1>
            <div class="container-btn">
                <button class="boton-agregar" onclick="mostrarFormulario('${newCategory.toLowerCase()}')">Agregar ${newCategory}</button>
                <button class="boton-eliminar" onclick="eliminarCategoria('categoria-${newCategory.toLowerCase()}')">Eliminar Categoría</button>
            </div>
        </div>
        <div class="row" id="${newCategory.toLowerCase()}"></div>
    `;
    document.getElementById('productos-container').appendChild(newCategoryContainer);

    modal.style.display = "none";
    document.getElementById('new-category').value = '';
});

// Función para eliminar categoría
function eliminarCategoria(idCategoria) {
    const categoria = document.getElementById(idCategoria);
    categoria.parentNode.removeChild(categoria);

    // Eliminar la opción del select
    const select = document.getElementById('categoria-producto');
    for (let i = 0; i < select.options.length; i++) {
        if (select.options[i].value === idCategoria.replace('categoria-', '')) {
            select.remove(i);
            break;
        }
    }
}

// Función para mostrar el formulario y establecer la categoría
function mostrarFormulario(categoria) {
    formularioAgregarProducto.classList.remove('d-none');
    document.getElementById('categoria-producto').value = categoria;
}