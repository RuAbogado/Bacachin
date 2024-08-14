// Obtener elementos del DOM
const btnAgregarCategoria = document.getElementById('add-category-btn');
const modalCategoria = document.getElementById('categoryModal');
const spanCerrarCategoria = modalCategoria.getElementsByClassName('close-category')[0];

// Mostrar modal para agregar categoría
btnAgregarCategoria.onclick = function() {
    modalCategoria.style.display = "block";
}

// Cerrar modal de categoría
spanCerrarCategoria.onclick = function() {
    modalCategoria.style.display = "none";
}

// Cerrar modal cuando se hace clic fuera de él
window.onclick = function(event) {
    if (event.target == modalCategoria) {
        modalCategoria.style.display = "none";
    }
}

// Cargar categorías desde el servidor
function cargarCategorias() {
    fetch('ObtenerCategorias')
        .then(response => response.text())
        .then(data => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(data, 'text/html');
            const categorias = doc.querySelectorAll('option');

            removeAllChildren(document.getElementById('clientesContainer'));
            categorias.forEach(category => {
                const newCategoryContainer = document.createElement('div');
                newCategoryContainer.className = 'categoria-container mb-5';
                newCategoryContainer.id = `categoria-${category.value}`;
                newCategoryContainer.innerHTML = `
                <div class="categoria-header">
                    <h1 class="encabezado">${category.text}</h1>
                    <div class="container-btn">
                        <button class="boton-eliminar" onclick="eliminarCategoria(${category.value})">Eliminar Categoría</button>
                    </div>
                </div>
                `;
                document.getElementById('clientesContainer').appendChild(newCategoryContainer);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar las categorías.');
        });
}

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
                cargarCategorias();  // Recargar categorías después de agregar
                modalCategoria.style.display = "none";
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
            } else {
                document.getElementById(`categoria-${ID_Categoria}`).remove();
                alert('La categoría se eliminó correctamente.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error en la solicitud.');
        });
}

// Eliminar todos los hijos de un elemento
function removeAllChildren(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild);
    }
}

// Iniciar la carga cuando el DOM esté completamente cargado
document.addEventListener('DOMContentLoaded', function() {
    cargarCategorias();
});
