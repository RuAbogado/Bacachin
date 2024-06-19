package mx.edu.utez.giup.model;

public class User {
    private String username;
    private String password;
    private boolean estado;
    private String codigo;

    public User() {}

    public User(String username, String password, boolean estado, String codigo) {
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.codigo = codigo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}