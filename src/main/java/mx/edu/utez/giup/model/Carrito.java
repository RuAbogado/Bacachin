package mx.edu.utez.giup.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;

public class Carrito {

    private int ID_Carrito;
    private int ID_Cliente;
    private Date Fecha_Creacion;
    private int Cantidad;
    private ArrayList<Productos> productos;

    public Carrito() {

    }

    public Carrito(int ID_Cliente, Date Fecha_Creacion) {
        this.ID_Cliente = ID_Cliente;
        this.Fecha_Creacion = Fecha_Creacion;
    }

    public Carrito(int ID_Carrito, int ID_Cliente, Date fecha_Creacion, int cantidad, ArrayList<Productos> productos) {
        this.ID_Carrito = ID_Carrito;
        this.ID_Cliente = ID_Cliente;
        Fecha_Creacion = fecha_Creacion;
        Cantidad = cantidad;
        this.productos = productos;
    }

    public int getID_Carrito() {
        return ID_Carrito;
    }

    public void setID_Carrito(int ID_Carrito) {
        this.ID_Carrito = ID_Carrito;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public Date getFecha_Creacion() {
        return Fecha_Creacion;
    }

    public void setFecha_Creacion(Date fecha_Creacion) {
        Fecha_Creacion = fecha_Creacion;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public ArrayList<Productos> getProductos() {
        return productos;
    }
    public void setProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }
}
