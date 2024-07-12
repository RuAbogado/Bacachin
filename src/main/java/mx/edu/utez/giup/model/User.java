package mx.edu.utez.giup.model;

public class User {
    private int id; // ID del usuario
    private String nombre;
    private String apellido;
    private String username;
    private String telefono;
    private String sexo;
    private String correo;
    private String password;
    private boolean estado;
    private String codigo;
    private String tipo; // Tipo de usuario (por ejemplo, administrador, usuario regular, etc.)
    private String imagen; // Ruta o URL de la imagen del usuario

    public User() {
    }

    public User(int id, String nombre, String apellido, String username, String telefono, String sexo, String correo, String password, boolean estado, String codigo, String tipo, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.telefono = telefono;
        this.sexo = sexo;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
        this.codigo = codigo;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    // Getters y setters para todos los campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}