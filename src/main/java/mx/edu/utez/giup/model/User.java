package mx.edu.utez.giup.model;

public class User {
    private int ID_Usuario;
    private String nombre;
    private String apellido;
    private String Nombre_Usuario;
    private String telefono;
    private String correo;
    private String password;
    private boolean estado;
    private String codigo;
    private String tipo;

    public User() {
    }

    public User(int ID_Usuario, String nombre, String apellido, String Nombre_Usuario, String telefono, String correo, String password, boolean estado, String codigo, String tipo) {
        this.ID_Usuario = ID_Usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.Nombre_Usuario = Nombre_Usuario;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
        this.estado = estado;
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public int getId() {
        return ID_Usuario;
    }

    public void setId(int id) {
        this.ID_Usuario = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido != null && !apellido.trim().isEmpty()) {
            this.apellido = apellido;
        } else {
            throw new IllegalArgumentException("Apellido no puede estar vacío");
        }
    }

    public String getUsername() {
        return Nombre_Usuario;
    }

    public void setUsername(String username) {
        if (username != null && !username.trim().isEmpty()) {
            this.Nombre_Usuario = username;
        } else {
            throw new IllegalArgumentException("Username no puede estar vacío");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Teléfono no puede estar vacío");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo != null && correo.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$")) {
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("Correo no es válido");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null && password.length() >= 6) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Contraseña debe tener al menos 6 caracteres");
        }
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
        if (codigo != null && !codigo.trim().isEmpty()) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("Código no puede estar vacío");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.trim().isEmpty()) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo no puede estar vacío");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "ID_Usuario=" + ID_Usuario +
                ", Nombre='" + nombre + '\'' +
                ", Apellido='" + apellido + '\'' +
                ", Nombre_Usuario='" + Nombre_Usuario + '\'' +
                ", Telefono='" + telefono + '\'' +
                ", Correo='" + correo + '\'' +
                ", Estado=" + estado +
                ", Codigo_RE='" + codigo + '\'' +
                ", Tipo='" + tipo + '\'' +
                '}';
    }
}
