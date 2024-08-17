// Función para cargar productos desde el servidor
function cargarProductos() {
    fetch('CargarProductos')
        .then(response => response.json())
        .then(productos => {
            const tbody = document.getElementById('productos-tbody');
            tbody.innerHTML = ''; // Limpiar el contenido actual

            productos.forEach(producto => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${producto.ID_Producto}</td>
                    <td>${producto.Nombre}</td>
                    <td>${producto.Descripcion}</td>
                    <td>$${producto.Precio.toFixed(2)}</td>
                    <td>${producto.Stock}</td>
                    <td>${producto.Fecha_Creacion}</td>
                    <td>${producto.Tipo}</td>
                    <td><img src="${producto.Imagen}" alt="${producto.Nombre}" style="width: 50px; height: auto;"></td>
                    <td><button class="btn btn-primary btn-agregar" data-id="${producto.ID_Producto}">Agregar</button></td>
                `;
                tbody.appendChild(tr);
            });

            // Agregar eventos a los botones "Agregar"
            document.querySelectorAll('.btn-agregar').forEach(btn => {
                btn.addEventListener('click', function() {
                    agregarAlCarrito(this.getAttribute('data-id'));
                });
            });
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al cargar los productos.');
        });
}

// Función para agregar producto al carrito
function agregarAlCarrito(idProducto) {
    fetch('ObtenerProductoPorId?id=' + idProducto)
        .then(response => response.json())
        .then(producto => {
            // Crear un nuevo elemento en el carrito
            const carrito = document.getElementById('carrito');
            const item = document.createElement('li');
            item.className = 'list-group-item d-flex justify-content-between align-items-center';
            item.innerHTML = `
                ${producto.Nombre} - $${producto.Precio.toFixed(2)}
                <button class="btn btn-danger btn-sm" onclick="eliminarDelCarrito(this)">Eliminar</button>
            `;
            carrito.appendChild(item);

            // Actualizar el total
            actualizarTotal();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error al agregar el producto al carrito.');
        });
}

// Función para eliminar producto del carrito
function eliminarDelCarrito(button) {
    button.parentElement.remove();
    actualizarTotal();
}

// Función para actualizar el total del carrito
function actualizarTotal() {
    const carritoItems = document.querySelectorAll('#carrito .list-group-item');
    let total = 0;
    carritoItems.forEach(item => {
        const precio = parseFloat(item.textContent.split('$')[1]);
        total += precio;
    });
    document.getElementById('total').textContent = total.toFixed(2);
}

// Cargar productos cuando el DOM esté completamente cargado
document.addEventListener('DOMContentLoaded', function() {
    cargarProductos();
});