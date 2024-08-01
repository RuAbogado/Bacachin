
document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('formAgregarEmpleado').addEventListener('submit', function(event) {
        event.preventDefault();
        agregarEmpleado();
    });
});

let empleados = [
    { id: 1, nombre: 'Juan Pérez', email: 'juan.perez@example.com', telefono: '123456789', direccion: 'Calle 1' },
    { id: 2, nombre: 'María López', email: 'maria.lopez@example.com', telefono: '987654321', direccion: 'Avenida 2' }
];

function mostrarFormularioAgregar() {
    const modal = new bootstrap.Modal(document.getElementById('agregarEmpleadoModal'));
    modal.show();
}

function agregarEmpleado() {
    const nombre = document.getElementById('nombreEmpleado').value;
    const email = document.getElementById('emailEmpleado').value;
    const telefono = document.getElementById('telefonoEmpleado').value;
    const direccion = document.getElementById('direccionEmpleado').value;

    const nuevoEmpleado = {
        id: empleados.length + 1,
        nombre,
        email,
        telefono,
        direccion
    };

    empleados.push(nuevoEmpleado);
    actualizarTablaEmpleados();

    const modal = bootstrap.Modal.getInstance(document.getElementById('agregarEmpleadoModal'));
    modal.hide();

    document.getElementById('formAgregarEmpleado').reset();
}

function actualizarTablaEmpleados() {
    const tbody = document.getElementById('empleadosTabla');
    tbody.innerHTML = '';

    empleados.forEach(empleado => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
      <td>${empleado.id}</td>
      <td>${empleado.nombre}</td>
      <td>${empleado.email}</td>
      <td>${empleado.telefono}</td>
      <td>${empleado.direccion}</td>
    `;
        tbody.appendChild(tr);
    });
}

actualizarTablaEmpleados();