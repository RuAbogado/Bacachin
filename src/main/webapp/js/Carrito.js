const btnCart = document.querySelector('.container-cart-icon');
const containerCartProducts = document.querySelector('.container-cart-products');
const rowProduct = document.querySelector('.row-product');
const productsList = document.querySelector('.container-items');
let allProducts = [];

// Elementos del carrito
const valorTotal = document.querySelector('.total-pagar');
const countProducts = document.querySelector('#contador-productos');
const cartEmpty = document.querySelector('.cart-empty');
const cartTotal = document.querySelector('.cart-total');

// `tipoUsuario` ya está disponible como una variable global inyectada desde el JSP
console.log("Tipo de usuario:", tipoUsuario);

// Función para obtener el ID del carrito según el tipo de usuario
const obtenerIdCarrito = async () => {
    try {
        let url = '/GIUP_war/ObtenerIdCarrito';  // Por defecto para cliente

        // Modificar la URL del endpoint según el tipo de usuario
        if (tipoUsuario === 'empleado') {
            url = '/GIUP_war/ObtenerIdCarritoEmpleado';
        } else if (tipoUsuario === 'admin') {
            url = '/GIUP_war/ObtenerIdCarritoAdmin';
        }

        const response = await fetch(url, {
            method: 'GET',
            credentials: 'include'
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const idCarrito = await response.text();
        console.log("ID_Carrito obtenido:", idCarrito);
        return idCarrito;
    } catch (error) {
        console.error("Error al obtener el ID del carrito:", error);
        return null;
    }
};

// Función para obtener los detalles del carrito
const obtenerDetallesDelCarrito = async () => {
    try {
        const idCarrito = await obtenerIdCarrito();

        if (!idCarrito) return;

        let url = `/GIUP_war/ObtenerDetallesCarrito`;  // Por defecto para cliente

        // Modificar la URL del endpoint según el tipo de usuario
        if (tipoUsuario === 'empleado') {
            url = `/GIUP_war/ObtenerDetallesCarritoEmpleado?idCarrito=${idCarrito}`;
        } else if (tipoUsuario === 'admin') {
            url = `/GIUP_war/ObtenerDetallesCarritoAdmin?idCarrito=${idCarrito}`;
        }

        const response = await fetch(url, {
            method: 'GET',
            credentials: 'include'
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const detallesCarrito = await response.json();
        console.log("Detalles del carrito:", detallesCarrito);

        // Itera sobre cada detalle y ajusta la estructura según lo que se recibe
        allProducts = detallesCarrito.map(detalle => {
            console.log("Detalle actual:", detalle);  // Esto imprimirá cada detalle en la consola

            let title, price;
            if (detalle.nombreProducto && detalle.precioProducto) {
                // Caso de admin
                title = detalle.nombreProducto;
                price = detalle.precioProducto;
            } else if (detalle.producto) {
                // Caso de cliente
                title = detalle.producto.nombre;
                price = detalle.producto.precio;
            } else {
                throw new Error("El formato de los detalles del carrito no es reconocido");
            }

            return {
                title: title,
                quantity: detalle.cantidad,
                price: price
            };
        });

        showHTML();
    } catch (error) {
        console.error("Error al obtener detalles del carrito:", error);
    }
};

// Función para enviar producto al servlet
const enviarProductoAlCarrito = async (product) => {
    const idProducto = obtenerIdProductoPorTitulo(product.title); // Implementa esta función para obtener el ID del producto
    const idCarrito = await obtenerIdCarrito(); // Obtén el ID del carrito

    // Ajusta la URL según el tipo de usuario
    let url = '/GIUP_war/AgregarProductoCarrito';  // Por defecto para cliente

    if (tipoUsuario === 'empleado') {
        url = '/GIUP_war/AgregarProductoCarritoEmpleado';
    } else if (tipoUsuario === 'admin') {
        url = '/GIUP_war/AgregarProductoCarritoAdmin';
    }

    const data = {
        ID_Carrito: idCarrito, // Usa exactamente el mismo nombre de variable que espera el servidor
        idProducto: idProducto,
        quantity: product.quantity
    };

    console.log("Datos enviados al servidor:", data);

    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();
        console.log(result.status)
        if (result.status === "success") {
            console.log("Producto agregado al carrito exitosamente.");
        } else {
            console.error("Error al agregar producto al carrito:", result.message);
        }
    } catch (error) {
        console.error("Error:", error);
    }
};

// Ejemplo de función para obtener ID del producto (deberás implementarla)
const obtenerIdProductoPorTitulo = (title) => {
    // Lógica para obtener el ID del producto según su título
    // Implementa aquí la lógica para obtener el ID del producto basado en el título
    return 1; // Devuelve el ID correspondiente del producto
};

// Función para mostrar HTML
const showHTML = () => {
    if (!allProducts.length) {
        cartEmpty.classList.remove('hidden');
        rowProduct.classList.add('hidden');
        cartTotal.classList.add('hidden');
    } else {
        cartEmpty.classList.add('hidden');
        rowProduct.classList.remove('hidden');
        cartTotal.classList.remove('hidden');
    }

    rowProduct.innerHTML = '';

    let total = 0;
    let totalOfProducts = 0;

    allProducts.forEach(product => {
        const containerProduct = document.createElement('div');
        containerProduct.classList.add('cart-product');

        containerProduct.innerHTML = `
            <div class="info-cart-product">
                <span class="cantidad-producto-carrito">${product.quantity}</span>
                <p class="titulo-producto-carrito">${product.title}</p>
                <span class="precio-producto-carrito">$${(product.price * product.quantity).toFixed(2)}</span>
            </div>
            <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke-width="1.5"
                stroke="currentColor"
                class="icon-close"
            >
                <path
                    stroke-linecap="round"
                    stroke-linejoin="round"
                    d="M6 18L18 6M6 6l12 12"
                />
            </svg>
        `;

        rowProduct.append(containerProduct);

        total += product.quantity * product.price;
        totalOfProducts += product.quantity;
    });

    valorTotal.innerText = `$${total.toFixed(2)}`;
    countProducts.innerText = totalOfProducts;
};

// Event Listener para abrir y cerrar el carrito
btnCart.addEventListener('click', () => {
    containerCartProducts.classList.toggle('hidden-cart');
});

// Event Listener para añadir productos al carrito
productsList.addEventListener('click', e => {
    if (e.target.classList.contains('btn-add-cart')) {
        const product = e.target.parentElement;

        const infoProduct = {
            quantity: 1,
            title: product.querySelector('h2').textContent,
            price: parseFloat(product.querySelector('.price').textContent.slice(1)) // Convertir a float
        };

        console.log("Producto añadido:", infoProduct);

        const exists = allProducts.some(
            p => p.title === infoProduct.title
        );

        if (exists) {
            allProducts = allProducts.map(p => {
                if (p.title === infoProduct.title) {
                    p.quantity++;
                    return p;
                } else {
                    return p;
                }
            });
        } else {
            allProducts = [...allProducts, infoProduct];
        }

        showHTML();
        enviarProductoAlCarrito(infoProduct);
    }
});

// Event Listener para eliminar productos del carrito
rowProduct.addEventListener('click', async (e) => {
    if (e.target.classList.contains('icon-close')) {
        const product = e.target.parentElement;
        const title = product.querySelector('.titulo-producto-carrito').textContent;

        // Encuentra el producto en la lista
        const productToRemove = allProducts.find(p => p.title === title);

        if (productToRemove) {
            const idProducto = obtenerIdProductoPorTitulo(productToRemove.title); // Implementa esta función para obtener el ID del producto
            const idCarrito = await obtenerIdCarrito(); // Obtén el ID del carrito

            try {
                // Enviar la solicitud al servidor para eliminar el producto del carrito
                const response = await fetch('/GIUP_war/EliminarProductoCarrito', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: `idProducto=${encodeURIComponent(idProducto)}&ID_Carrito=${encodeURIComponent(idCarrito)}&tipoUsuario=${encodeURIComponent(tipoUsuario)}`
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const result = await response.json();

                if (result.success) {
                    // Eliminar el producto de la lista en el frontend si se eliminó con éxito en el servidor
                    allProducts = allProducts.filter(
                        p => p.title !== title
                    );

                    showHTML(); // Actualizar la vista del carrito
                } else {
                    console.error('Error al eliminar el producto del carrito:', result.message);
                }
            } catch (error) {
                console.error('Error:', error);
            }
        }
    }
});

// Cargar productos al inicio
document.addEventListener("DOMContentLoaded", function() {
    fetch("/GIUP_war/CargarProductos")
        .then(response => response.json())
        .then(data => {
            console.log("Productos cargados:", data);

            const container = document.querySelector(".container-items");
            container.innerHTML = ''; // Limpiar contenido previo

            data.forEach(producto => {
                const item = document.createElement("div");
                item.className = "item";
                item.innerHTML = `
                <figure>
                    <img src="img/${producto.Imagen}" alt="${producto.Nombre}" />
                </figure>
                <div class="info-product">
                    <h2>${producto.Nombre}</h2>
                    <p class="price">$${producto.Precio}</p>
                    <button class="btn-add-cart">Añadir al carrito</button>
                </div>
                `;
                container.appendChild(item);
            });
            // Cargar detalles del carrito después de cargar los productos
            obtenerDetallesDelCarrito();
        })
        .catch(error => console.error('Error:', error));
});