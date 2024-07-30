document.addEventListener('DOMContentLoaded', function () {
    console.log('DOM completamente cargado');

    // Función para mostrar una sección por ID
    function mostrarSeccion(id) {
        const seccionElement = document.getElementById(id);
        if (seccionElement) {
            console.log(`Elemento ${id} encontrado`);
            seccionElement.style.display = 'block';
        } else {
            console.log(`Elemento ${id} no encontrado`);
        }
    }

    // Asocia el evento de clic al enlace de clientes
    const clienteLink = document.querySelector('a[data-target="clientes"]');
    if (clienteLink) {
        console.log('Enlace de clientes encontrado');
        clienteLink.addEventListener('click', function (event) {
            event.preventDefault();
            mostrarSeccion('clientes');
        });
    } else {
        console.log('Enlace de clientes no encontrado');
    }

    // Función para obtener la ruta del contexto
    function getContextPath() {
        return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    }

    // Función para enviar el formulario de reporte
    window.submitForm = function () {
        const form = document.getElementById("reporteForm");
        if (form) {
            form.action = `${getContextPath()}/generarReporte`;
            form.submit();
        } else {
            console.log('Formulario no encontrado');
        }
    };
});

// jQuery ready function para manejar la carga de contenido y navegación
$(document).ready(function() {
    // Cargar contenido de productos.html
    $('a[data-target="productos"]').on('click', function(event) {
        event.preventDefault();
        $("#productos").load("productos.html", function() {
            $('#content > div').hide(); // Oculta todas las secciones
            $("#productos").show(); // Muestra la sección de productos
        });
    });

    // Funcionalidad para mostrar otras secciones
    $('a[data-target]').on('click', function(event) {
        event.preventDefault();
        const targetId = $(this).data('target');
        $('#content > div').hide(); // Oculta todas las secciones
        $(`#${targetId}`).show(); // Muestra la sección objetivo
    });
});

