//productos.html
// Variables
const carrito = document.querySelector('#carrito');
const listaCursos = document.querySelector('#lista-cursos');
const contenedorCarrito = document.querySelector('#lista-carrito tbody');
const vaciarCarritoBtn = document.querySelector('#vaciar-carrito');
let articulosCarrito = [];

// Variables para agregar productos
const formularioAgregarProducto = document.querySelector('#agregar-producto');
const btnAgregarProducto = document.querySelector('#btn-agregar-producto');
const btnAgregarBebida = document.querySelector('#btn-agregar-bebida');
const formAgregarProducto = document.querySelector('#form-agregar-producto');
const productosContainer = document.querySelector('#productos');
const bebidasContainer = document.querySelector('#bebidas');

// Listeners
cargarEventListeners();

function cargarEventListeners() {
    // Cuando agregas un curso presionando 'Agregar al carrito'
    listaCursos.addEventListener('click', agregarCurso);
    // Elimina cursos del carrito
    carrito.addEventListener('click', eliminarCurso);
    // Muestra los cursos del storage
    document.addEventListener('DOMContentLoaded', () => {
        articulosCarrito = JSON.parse(localStorage.getItem('carrito')) || [];
        carritoHTML();
    });
    // Vaciar carrito
    vaciarCarritoBtn.addEventListener('click', () => {
        articulosCarrito = [];
        limpiarHTML();
        sincronizarStorage();
    });

    // Mostrar el formulario para agregar productos
    btnAgregarProducto.addEventListener('click', () => {
        formularioAgregarProducto.style.display = 'block';
        document.querySelector('#categoria-producto').value = 'productos';
    });

    // Mostrar el formulario para agregar bebidas
    btnAgregarBebida.addEventListener('click', () => {
        formularioAgregarProducto.style.display = 'block';
        document.querySelector('#categoria-producto').value = 'bebidas';
    });

    // Manejar el envío del formulario
    formAgregarProducto.addEventListener('submit', function(event) {
        event.preventDefault();

        const imagen = document.getElementById('imagen-producto').value;
        const nombre = document.getElementById('nombre-producto').value;
        const descripcion = document.getElementById('descripcion-producto').value;
        const precio = document.getElementById('precio-producto').value;
        const descuento = document.getElementById('descuento-producto').value;
        const categoria = document.getElementById('categoria-producto').value;

        // Convertir la imagen a una URL
        const reader = new FileReader();
        reader.onload = function(e) {
            const imagenURL = e.target.result;

            const nuevoProducto = document.createElement('div');
            nuevoProducto.className = 'four columns';
            nuevoProducto.innerHTML = `
                <div class="card" style="height: 100%;">
                    <img src="${imagenURL}" class="imagen-curso u-full-width">
                    <div class="info-card">
                        <h4>${nombre}</h4>
                        <p>${descripcion}</p>
                        <img src="img/estrellas.png">
                        <p class="precio">$${precio} <span class="u-pull-right">$${descuento}</span></p>
                        <a href="#" class="u-full-width button-primary button input agregar-carrito" data-id="${Date.now()}">Agregar Al Carrito</a>
                    </div>
                </div>
            `;

            if (categoria === 'productos') {
                productosContainer.appendChild(nuevoProducto);
            } else if (categoria === 'bebidas') {
                bebidasContainer.appendChild(nuevoProducto);
            }

            formularioAgregarProducto.style.display = 'none';
            formAgregarProducto.reset();
        };
        reader.readAsDataURL(document.getElementById('imagen-producto').files[0]);
    });
}

// Funciones
function agregarCurso(e) {
    e.preventDefault();
    if (e.target.classList.contains('agregar-carrito')) {
        const curso = e.target.parentElement.parentElement;
        leerDatosCurso(curso);
        productoAgregado(curso);
    }
}

function productoAgregado(curso) {
    const alert = document.createElement('h4');
    alert.className = 'alerta';
    alert.textContent = 'Añadido al carrito';
    curso.appendChild(alert);
    setTimeout(() => alert.remove(), 2000);
}

function eliminarCurso(e) {
    if (e.target.classList.contains('borrar-curso')) {
        const cursoId = e.target.getAttribute('data-id');
        articulosCarrito = articulosCarrito.filter(curso => curso.id !== cursoId);
        carritoHTML();
    }
}

function leerDatosCurso(curso) {
    const infoCurso = {
        imagen: curso.querySelector('img').src,
        titulo: curso.querySelector('h4').textContent,
        precio: curso.querySelector('.precio span').textContent,
        id: curso.querySelector('a').getAttribute('data-id'),
        cantidad: 1
    }
    const existe = articulosCarrito.some(curso => curso.id === infoCurso.id);
    if (existe) {
        articulosCarrito = articulosCarrito.map(curso => {
            if (curso.id === infoCurso.id) {
                curso.cantidad++;
            }
            return curso;
        });
    } else {
        articulosCarrito = [...articulosCarrito, infoCurso];
    }
    carritoHTML();
}

function carritoHTML() {
    limpiarHTML();
    articulosCarrito.forEach(curso => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><img src="${curso.imagen}" width="100"></td>
            <td>${curso.titulo}</td>
            <td>${curso.precio}</td>
            <td>${curso.cantidad}</td>
            <td><a href="#" class="borrar-curso" data-id="${curso.id}">X</a></td>
        `;
        contenedorCarrito.appendChild(row);
    });
    sincronizarStorage();
}

function sincronizarStorage() {
    localStorage.setItem('carrito', JSON.stringify(articulosCarrito));
}

function limpiarHTML() {
    while (contenedorCarrito.firstChild) {
        contenedorCarrito.removeChild(contenedorCarrito.firstChild);
    }
}
