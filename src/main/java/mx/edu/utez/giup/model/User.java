package mx.edu.utez.giup.model;

public class User {
    private int id;
    private String nombre;
    private String apellido;
    private String username;
    private String telefono;
    private String sexo;
    private String correo;
    private String password;
    private boolean estado;
    private String codigo;
    private String tipo;
    private String imagen;

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
        if(nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("Nombre no puede estar vacío");
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if(apellido != null && !apellido.trim().isEmpty()) {
            this.apellido = apellido;
        } else {
            throw new IllegalArgumentException("Apellido no puede estar vacío");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username != null && !username.trim().isEmpty()) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Username no puede estar vacío");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Teléfono no puede estar vacío");
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        if(sexo != null && (sexo.equalsIgnoreCase("Masculino") || sexo.equalsIgnoreCase("Femenino"))) {
            this.sexo = sexo;
        } else {
            throw new IllegalArgumentException("Sexo debe ser 'Masculino' o 'Femenino'");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if(correo != null && correo.matches("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$")) {
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("Correo no es válido");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null && password.length() >= 6) {
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
        if(codigo != null && !codigo.trim().isEmpty()) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("Código no puede estar vacío");
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if(tipo != null && !tipo.trim().isEmpty()) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo no puede estar vacío");
        }
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        if(imagen != null && !imagen.trim().isEmpty()) {
            this.imagen = imagen;
        } else {
            throw new IllegalArgumentException("Imagen no puede estar vacía");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", username='" + username + '\'' +
                ", telefono='" + telefono + '\'' +
                ", sexo='" + sexo + '\'' +
                ", correo='" + correo + '\'' +
                ", estado=" + estado +
                ", codigo='" + codigo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
