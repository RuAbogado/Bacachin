
    window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('error')) {
    alert('Correo o contraseña incorrectos');
}
};

    function irARegistro() {
    window.location.href = "registro.jsp";
}
