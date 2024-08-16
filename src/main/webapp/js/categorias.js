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
document.getElementById("registroForm").addEventListener("submit", function(event) {
    event.preventDefault();  // Evita que el formulario se envíe de la manera tradicional

    // Obtener y validar los campos del formulario cambios ya es actualizaron
    var Nombre = document.getElementById("Nombre").value.trim();
    var Descripcion = document.getElementById("Descripcion").value.trim();


    if (Nombre === "" || Descripcion === "") {
        alert("Por favor, rellene todos los campos.");
        return;
    }

    // Enviar el formulario usando fetch
    var formData = new FormData();
    formData.append("Nombre", Nombre);
    formData.append("Descripcion", Descripcion);

    console.log(formData);

    fetch("AgregarCategoria", {
        method: "POST",
        body: formData
    })
        .then(response => response.text())  // Obtener la respuesta como texto HTML
        .then(html => {
            // Mostrar el resultado en el contenedor de resultados
            document.getElementById("resultContainer").innerHTML = html;
            // Actualizar la tabla de categorías
            actualizarTablaCategorias();
            // Ocultar el formulario y reiniciar el formulario
            document.getElementById("formularioContainer").style.display = "none";
            document.getElementById("registroForm").reset();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Ocurrió un error al procesar la solicitud. Por favor, intente nuevamente.');
        });
});

// Función para borrar el registro en el formulario
function borrarRegistro() {
    if (confirm("¿Estás seguro de que deseas borrar el registro?")) {
        document.getElementById("registroForm").reset();
    }
}

// Función para actualizar la tabla de categorías
function actualizarTablaCategorias() {
    fetch("ListarCategorias")  // URL del servlet o JSP para listar categorías
        .then(response => response.text())  // Obtener el HTML de la tabla actualizada
        .then(html => {
            document.getElementById("clientesContainer").innerHTML = html;  // Actualizar el contenido de la tabla
        })
        .catch(error => {
            console.error('Error al actualizar la tabla de categorías:', error);
            alert('Ocurrió un error al actualizar la tabla de categorías.');
        });
}