package mx.edu.utez.giup.model;

import java.sql.Date;

public class Ventas {

    private int ID_Venta;
    private int ID_Cliente;
    private int ID_Solicitud;
    private Date Fecha_Venta;
    private int Total;


    public Ventas(int ID_Venta, int ID_Cliente, int ID_Solicitud, Date fecha_Venta, int total) {
        this.ID_Venta = ID_Venta;
        this.ID_Cliente = ID_Cliente;
        this.ID_Solicitud = ID_Solicitud;
        Fecha_Venta = fecha_Venta;
        Total = total;

    }


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

    public void setFecha_Venta(Date fecha_Venta) {
        Fecha_Venta = fecha_Venta;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }
}
