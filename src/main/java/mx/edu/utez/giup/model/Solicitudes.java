package mx.edu.utez.giup.model;

import java.sql.Date;

public class Solicitudes {

    private int ID_Solicitud;
    private int ID_Cliente;
    private int ID_Producto;
    private int Cantidad;
    private int Fecha_Solicitud;
    private String Estado;


    public Solicitudes(int ID_Solicitud, int ID_Cliente, int ID_Producto, int cantidad, int fecha_Solicitud, String estado) {
        this.ID_Solicitud = ID_Solicitud;
        this.ID_Cliente = ID_Cliente;
        this.ID_Producto = ID_Producto;
        Cantidad = cantidad;
        Fecha_Solicitud = fecha_Solicitud;
        Estado = estado;
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

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public int getFecha_Solicitud() {
        return Fecha_Solicitud;
    }

    public void setFecha_Solicitud(int fecha_Solicitud) {
        Fecha_Solicitud = fecha_Solicitud;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
