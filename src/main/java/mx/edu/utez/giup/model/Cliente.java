package mx.edu.utez.giup.model;

public class Cliente {
    private int idCliente;
    private int idUsuario;
    private boolean estado;

    // Constructor
    public Cliente(int idCliente, int idUsuario, boolean estado) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.estado = estado;
    }

    // Getters y setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}