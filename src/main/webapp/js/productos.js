// productos.html

document.addEventListener('DOMContentLoaded', () => {
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

                        if (categoria === 'productos') {
                            document.getElementById('productos').appendChild(nuevoProducto);
                        } else if (categoria === 'bebidas') {
                            document.getElementById('bebidas').appendChild(nuevoProducto);
                        }

                        formularioAgregarProducto.classList.add('d-none');
                        formAgregarProducto.reset();

                        cargarEventListenersEliminar(); // Cargar los event listeners de eliminar
                    };
                    reader.readAsDataURL(imagen);
                }
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
});

//-------------------------------------------------------------------------------------AGREGAR NUEVA CATEGORIA-----------------------------------
// Obtener elementos del DOM
var modal = document.getElementById("categoryModal");
var btn = document.getElementById("add-category-btn");
var span = document.getElementsByClassName("close")[0];

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
    var newCategory = document.getElementById('new-category').value;
    var select = document.getElementById('categoria-producto');
    var option = document.createElement('option');
    option.value = newCategory.toLowerCase();
    option.text = newCategory;
    select.add(option);

    // Crear nuevo contenedor de categoría
    var newCategoryContainer = document.createElement('div');
    newCategoryContainer.className = 'categoria-container mb-5';
    newCategoryContainer.innerHTML = `
            <div class="categoria-header">
                <h1 class="encabezado">${newCategory}</h1>
                <div class="container-btn">
                    <button class="boton-agregar">Agregar ${newCategory}</button>
                </div>
            </div>
            <div class="row" id="${newCategory.toLowerCase()}"></div>
        `;
    document.getElementById('productos-container').appendChild(newCategoryContainer);

    modal.style.display = "none";
    document.getElementById('new-category').value = '';
});

