// Mostrar el formulario de agregar categoría
document.getElementById("agregar-categoria").addEventListener("click", function() {
    document.getElementById("formularioContainer").style.display = "flex";
});

// Cerrar el formulario de agregar categoría
document.getElementById("closeForm").addEventListener("click", function() {
    document.getElementById("formularioContainer").style.display = "none";
});

// Cerrar el formulario si se hace clic fuera del modal
window.addEventListener("click", function(event) {
    if (event.target === document.getElementById("formularioContainer")) {
        document.getElementById("formularioContainer").style.display = "none";
    }
});

// Validar y enviar el formulario de agregar categoría
function validarYEnviar() {
    var nombre = document.getElementById("nombre").value.trim();
    var descripcion = document.getElementById("descripcion").value.trim();

    // Validar si algún campo está vacío
    if (nombre === "" || descripcion === "") {
        alert("Por favor, rellene todos los campos.");
        return false;
    }

    return true;
}

// Función para borrar el registro en el formulario
function borrarRegistro() {
    if (confirm("¿Estás seguro de que deseas borrar el registro?")) {
        document.getElementById("registroForm").reset();
    }
}