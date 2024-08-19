const btnCart = document.querySelector('.container-cart-icon');
const containerCartProducts = document.querySelector('.container-cart-products');

btnCart.addEventListener('click', () => {
    containerCartProducts.classList.toggle('hidden-cart');
});

/* ========================= */
const rowProduct = document.querySelector('.row-product');

// Lista de todos los contenedores de productos
const productsList = document.querySelector('.container-items');

// Variable de arreglos de Productos
let allProducts = [];

// Elementos del carrito
const valorTotal = document.querySelector('.total-pagar');
const countProducts = document.querySelector('#contador-productos');
const cartEmpty = document.querySelector('.cart-empty');
const cartTotal = document.querySelector('.cart-total');

const obtenerIdCarrito = async () => {
try {
    const response = await fetch('/GIUP_war/ObtenerIdCarrito', { // Actualiza esta ruta con la ruta real de tu servlet
        method: 'GET',
        credentials: 'include' // Importante para incluir cookies si usas sesiones
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    const idCarrito = await response.text(); // Asumiendo que el servidor devuelve el ID como texto
    console.log("ID_Carrito obtenido:", idCarrito);
    return idCarrito;
} catch (error) {
    console.error("Error al obtener el ID del carrito:", error);
    return null; // Devuelve null o un valor por defecto en caso de error
}
};

productsList.addEventListener('click', e => {
    if (e.target.classList.contains('btn-add-cart')) {
        const product = e.target.parentElement;

        const infoProduct = {
            quantity: 1,
            title: product.querySelector('h2').textContent,
            price: parseFloat(product.querySelector('p').textContent.slice(1)) // Convertir a float
        };

        console.log("Producto añadido:", infoProduct); // Verificar datos del producto

        const exists = allProducts.some(
            product => product.title === infoProduct.title
        );

        if (exists) {
            const products = allProducts.map(product => {
                if (product.title === infoProduct.title) {
                    product.quantity++;
                    return product;
                } else {
                    return product;
                }
            });
            allProducts = [...products];
        } else {
            allProducts = [...allProducts, infoProduct];
        }

        showHTML();
        enviarProductoAlCarrito(infoProduct);
    }
});

rowProduct.addEventListener('click', e => {
    if (e.target.classList.contains('icon-close')) {
        const product = e.target.parentElement;
        const title = product.querySelector('.titulo-producto-carrito').textContent;

        allProducts = allProducts.filter(
            product => product.title !== title
        );

        showHTML();
    }
});

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

    // Limpiar HTML
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

// Función para enviar producto al servlet
const enviarProductoAlCarrito = (product) => {
    const idProducto = obtenerIdProductoPorTitulo(product.title); // Implementa esta función para obtener el ID del producto
    const idCarrito = obtenerIdCarrito(); // Obtén el ID del carrito
    const data = {
        idCarrito: idCarrito,
        idProducto: idProducto,
        quantity: product.quantity
    };

    console.log("Datos enviados al servidor:", data); // Verificar los datos enviados al servidor

    fetch("/GIUP_war/AgregarProductoCarrito", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(result => {
            if (result.status === "success") {
                console.log("Producto agregado al carrito exitosamente.");
            } else {
                console.error("Error al agregar producto al carrito:", result.message);
            }
        })
        .catch(error => console.error("Error:", error));
};

// Ejemplo de función para obtener ID del producto (deberás implementarla)
const obtenerIdProductoPorTitulo = (title) => {
    // Lógica para obtener el ID del producto según su título
    // Puedes mantener un mapeo de productos en una variable o realizar una consulta al servidor
    return 1; // Retorna el ID del producto correspondiente
};

document.addEventListener("DOMContentLoaded", function() {
    fetch("/GIUP_war/CargarProductos")
        .then(response => response.json())
        .then(data => {
            console.log("Productos cargados:", data); // Verificar los productos cargados

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
        })
        .catch(error => console.error('Error:', error));
});