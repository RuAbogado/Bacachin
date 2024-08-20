package mx.edu.utez.giup.model;

import java.time.LocalDate;

public class Solicitudes {
    private int ID_Solicitud;
    private int ID_Cliente;
    private LocalDate Fecha_Solicitud;
    private String Estado;

    // Constructor sin argumentos
    public Solicitudes() {
    }

    // Constructor con todos los campos
    public Solicitudes(int ID_Solicitud, int ID_Cliente, LocalDate Fecha_Solicitud, String Estado) {
        this.ID_Solicitud = ID_Solicitud;
        this.ID_Cliente = ID_Cliente;
        this.Fecha_Solicitud = Fecha_Solicitud;
        this.Estado = Estado;
    }

    public int getID_Solicitud() {
        return ID_Solicitud;
    }

    public void setID_Solicitud(int ID_Solicitud) {
        this.ID_Solicitud = ID_Solicitud;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public LocalDate getFecha_Solicitud() {
        return Fecha_Solicitud;
    }

    public void setFecha_Solicitud(LocalDate Fecha_Solicitud) {
        this.Fecha_Solicitud = Fecha_Solicitud;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}