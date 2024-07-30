document.addEventListener('DOMContentLoaded', function() {
    // Obtener referencias a los elementos
    const openModalButton = document.getElementById('openModalButton');
    const closeModalButton = document.querySelector('.jsAddProductClose');
    const addProductModal = document.getElementById('jsAddProductModal');
    const addProductForm = document.getElementById('addProductForm');
    const productGrid = document.getElementById('productGrid');

    // Función para abrir el modal
    function openModal() {
        addProductModal.classList.add('active');
    }

    // Función para cerrar el modal
    function closeModal() {
        addProductModal.classList.remove('active');
    }

    // Función para manejar el envío del formulario
    function handleFormSubmit(event) {
        event.preventDefault();

        // Obtener datos del formulario
        const productId = addProductForm.productId.value;
        const categoryId = addProductForm.categoryId.value;
        const productName = addProductForm.productName.value;
        const productDesc = addProductForm.productDesc.value;
        const productPrice = addProductForm.productPrice.value;
        const productStock = addProductForm.productStock.value;
        const productDate = addProductForm.productDate.value;
        const productType = addProductForm.productType.value;
        const productImage = addProductForm.productImage.value;

        // Crear nuevo elemento para el producto
        const productItem = document.createElement('div');
        productItem.className = 'product-grid__item';
        productItem.innerHTML = `
            <div class="product-grid__imagen">
                <img src="${productImage}" alt="${productName}">
            </div>
            <div class="product-grid__info">
                <p class="product-grid__name">${productName}</p>
                <p class="product-grid__price">$${productPrice}</p>
                <a href="#" class="product-grid__btn btn-default" data-btn-action="add-btn-cart">Agregar al carrito</a>
            </div>
        `;

        // Añadir el nuevo producto a la cuadrícula
        productGrid.appendChild(productItem);

        // Ocultar el formulario
        closeModal();

        // Limpiar formulario
        addProductForm.reset();
    }

    // Agregar eventos
    openModalButton.addEventListener('click', openModal);
    closeModalButton.addEventListener('click', closeModal);
    addProductForm.addEventListener('submit', handleFormSubmit);

    // Cerrar el modal al hacer clic fuera de él
    window.addEventListener('click', function(event) {
        if (event.target == addProductModal) {
            closeModal();
        }
    });
});