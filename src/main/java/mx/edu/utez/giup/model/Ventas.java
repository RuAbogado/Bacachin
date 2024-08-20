package mx.edu.utez.giup.model;

import java.sql.Date;

public class Ventas {

    private int ID_Venta;
    private int ID_Cliente;
    private int ID_Solicitud;
    private Date Fecha_Venta;
    private int Total;
    private String Estado;  // Nuevo campo Estado

    public Ventas(int ID_Venta, int ID_Cliente, int ID_Solicitud, Date Fecha_Venta, int Total, String Estado) {
        this.ID_Venta = ID_Venta;
        this.ID_Cliente = ID_Cliente;
        this.ID_Solicitud = ID_Solicitud;
        this.Fecha_Venta = Fecha_Venta;
        this.Total = Total;
        this.Estado = Estado;  // Inicialización del nuevo campo
    }

    // Getters y Setters
    public int getID_Venta() {
        return ID_Venta;
    }

    public void setID_Venta(int ID_Venta) {
        this.ID_Venta = ID_Venta;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public int getID_Solicitud() {
        return ID_Solicitud;
    }

    public void setID_Solicitud(int ID_Solicitud) {
        this.ID_Solicitud = ID_Solicitud;
    }

    public Date getFecha_Venta() {
        return Fecha_Venta;
    }

    public void setFecha_Venta(Date Fecha_Venta) {
        this.Fecha_Venta = Fecha_Venta;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public String getEstado() {  // Getter para Estado
        return Estado;
    }

    public void setEstado(String Estado) {  // Setter para Estado
        this.Estado = Estado;
    }
}
