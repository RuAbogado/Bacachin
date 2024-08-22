package mx.edu.utez.giup.model;

import java.time.LocalDate;

public class Solicitudes {
    private int ID_Solicitud;
    private int ID_Cliente;
    private LocalDate Fecha_Solicitud;
    private String Estado;
    private int Cantidad;
    private double Precio;
    private String NombreProducto;

    // Constructor sin argumentos
    public Solicitudes() {
    }

    // Constructor con todos los campos
    public Solicitudes(int ID_Solicitud, int ID_Cliente, LocalDate Fecha_Solicitud, String Estado, int Cantidad, double Precio, String NombreProducto) {
        this.ID_Solicitud = ID_Solicitud;
        this.ID_Cliente = ID_Cliente;
        this.Fecha_Solicitud = Fecha_Solicitud;
        this.Estado = Estado;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
        this.NombreProducto = NombreProducto;
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

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double Precio) {
        this.Precio = Precio;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    // Método para calcular el total de la compra
    public double calcularTotal() {
        return this.Cantidad * this.Precio;
    }
}
