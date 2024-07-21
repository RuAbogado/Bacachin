
    document.addEventListener('DOMContentLoaded', function () {
    console.log('DOM completamente cargado');

    function mostrarClientes() {
    console.log('mostrarClientes se ha llamado');
    const clientesElement = document.getElementById('clientes');
    if (clientesElement) {
    console.log('Elemento clientes encontrado');
    clientesElement.style.display = 'block';
} else {
    console.log('Elemento clientes no encontrado');
}
}

    const targetLink = document.querySelector('a[data-target="clientes"]');
    if (targetLink) {
    console.log('Enlace encontrado');
    targetLink.addEventListener('click', function (event) {
    event.preventDefault();
    mostrarClientes();
});
} else {
    console.log('Enlace no encontrado');
}
});



    function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

    function submitForm() {
    var form = document.getElementById("reporteForm");
    form.action = getContextPath() + "/generarReporte";
    form.submit();
}

