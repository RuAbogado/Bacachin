package mx.edu.utez.giup.model;

import java.sql.Date;

public class Compras {

    private int ID_Compra;
    private int ID_Proveedor;
    private Date Fecha_Compra;
    private int Monto_Total;


    public Compras(int ID_Compra, int ID_Proveedor, Date fecha_Compra, int monto_Total) {
        this.ID_Compra = ID_Compra;
        this.ID_Proveedor = ID_Proveedor;
        Fecha_Compra = fecha_Compra;
        Monto_Total = monto_Total;
    }

    public int getID_Compra() {
        return ID_Compra;
    }

    public void setID_Compra(int ID_Compra) {
        this.ID_Compra = ID_Compra;
    }

    public int getID_Proveedor() {
        return ID_Proveedor;
    }

    public void setID_Proveedor(int ID_Proveedor) {
        this.ID_Proveedor = ID_Proveedor;
    }

    public Date getFecha_Compra() {
        return Fecha_Compra;
    }

    public void setFecha_Compra(Date fecha_Compra) {
        Fecha_Compra = fecha_Compra;
    }

    public int getMonto_Total() {
        return Monto_Total;
    }

    public void setMonto_Total(int monto_Total) {
        Monto_Total = monto_Total;
    }
}
