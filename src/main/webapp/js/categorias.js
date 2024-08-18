// Obtener elementos del DOM
const btnAgregarProducto = document.getElementById('add-product-btn');
const btnAgregarCategoria = document.getElementById('add-category-btn');
const modalCategoria = document.getElementById('categoryModal');
const spanCerrarCategoria = modalCategoria.getElementsByClassName('close-category')[0];


// Mostrar modales
btnAgregarCategoria.onclick = function() {
    modalCategoria.style.display = "block";
}

// Cerrar modales
spanCerrarCategoria.onclick = function() {
    modalCategoria.style.display = "none";
}

// Cerrar modal cuando se hace clic fuera de él
window.onclick = function(event) {
    if (event.target == modalCategoria) {
        modalCategoria.style.display = "none";
    }
}

// Cargar categorías y productos desde el servidor
function cargarCategoriasYProductos() {
    // Primero, cargar las categorías
    fetch('ObtenerCategorias')
        .then(response => response.text())
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const categorias = doc.querySelectorAll('option');
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
            });

        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar las categorías.');
        });
}
// Iniciar la carga cuando el DOM esté completamente cargado
document.addEventListener('DOMContentLoaded', function() {
    cargarCategoriasYProductos();
});
//-------------------------------------------------


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
                // Recargar el iframe que contiene categorias.jsp
                window.location.reload();
            } else {
                alert('Error al agregar la categoría a la lista.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud para agregar la categoria en la lista.');
        });
});

// Deshabilitar una categoría
function DeshabilitarCategoria(ID_Categoria) {
    if (!confirm('¿Estás seguro de que quieres deshabilitar esta categoría?')) {
        return;
    }

    // Obtener la fila de la tabla correspondiente a la categoría
    const categoria = document.getElementById(`categoria-${ID_Categoria}`);
    if (categoria) {
        // Añadir la clase 'deshabilitada' para simular que no está disponible
        categoria.classList.add('deshabilitada');
    }

    fetch('deshabilitarCategoria', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `ID_Categoria=${encodeURIComponent(ID_Categoria)}`
    })
        .then(response => response.json()) // Se espera una respuesta en formato JSON
        .then(data => {
            if (!data.success) {
                alert('Error al deshabilitar la categoría.');
            } else {
                window.location.reload();
                alert('La categoría se deshabilitó correctamente.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// Deshabilitar una categoría
function HabilitarCategoria(ID_Categoria) {
    if (!confirm('¿Estás seguro de que quieres deshabilitar esta categoría?')) {
        return;
    }

    // Obtener la fila de la tabla correspondiente a la categoría
    const categoria = document.getElementById(`categoria-${ID_Categoria}`);
    if (categoria) {
        // Añadir la clase 'deshabilitada' para simular que no está disponible
        categoria.classList.add('deshabilitada');
    }

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

// Mostrar formulario para agregar producto en una categoría específica
function mostrarFormulario(categoria) {
    modalProducto.style.display = "block";
}