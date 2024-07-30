// productos.html

document.addEventListener('DOMContentLoaded', function() {
    initializeProductModal();
});

function initializeProductModal() {
    // Mostrar el modal para agregar productos
    document.getElementById('openAddProductModal').addEventListener('click', function() {
        document.getElementById('jsAddProductModal').classList.add('active');
    });

    // Cerrar el modal cuando se haga clic en el botón de cerrar
    document.querySelector('.jsAddProductClose').addEventListener('click', function() {
        document.getElementById('jsAddProductModal').classList.remove('active');
    });

    // Manejar el envío del formulario
    document.getElementById('addProductForm').addEventListener('submit', function(event) {
        event.preventDefault();

        // Obtener datos del formulario
        const productId = document.getElementById('productId').value;
        const categoryId = document.getElementById('categoryId').value;
        const productName = document.getElementById('productName').value;
        const productDesc = document.getElementById('productDesc').value;
        const productPrice = document.getElementById('productPrice').value;
        const productStock = document.getElementById('productStock').value;
        const productDate = document.getElementById('productDate').value;
        const productType = document.getElementById('productType').value;
        const productImage = document.getElementById('productImage').value;

        // Crear nuevo elemento para el producto
        const productItem = document.createElement('div');
        productItem.className = 'product-grid__item';
        productItem.innerHTML = `
        <div class="product-grid__imagen">
            <img src="${productImage}" alt="${productName}">
        </div>
        <div class="product-grid__info">
            <p class="product-grid__name">${productName}</p>
            <p class="product-grid__price">$${productPrice} / kg</p>
            <a href="#" class="product-grid__btn btn-default" data-btn-action="add-btn-cart">Agregar al carrito</a>
        </div>
    `;

        // Añadir el nuevo producto a la cuadrícula
        document.getElementById('productGrid').appendChild(productItem);

        // Ocultar el formulario
        document.getElementById('jsAddProductModal').classList.remove('active');

        // Limpiar formulario
        document.getElementById('addProductForm').reset();
    });
}