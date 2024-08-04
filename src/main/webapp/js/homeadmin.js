$(document).ready(function() {
    console.log('DOM completamente cargado');

    // Función para mostrar una sección por ID
    function mostrarSeccion(id) {
       // $('#content > div').hide(); // Oculta todas las secciones
       // $('#' + id).show(); // Muestra la sección seleccionada
        document.getElementsByTagName('iframe')[0].src =  id +".jsp"
    }

    // Asocia el evento de clic a los enlaces
    $('a[data-target]').on('click', function(event) {
        event.preventDefault();
        const targetId = $(this).data('target');

        mostrarSeccion(targetId);
    });

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