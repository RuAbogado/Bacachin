package mx.edu.utez.giup.model;

import java.sql.Date;
import java.util.ArrayList;

public class CarritoEmpleado {

    private int ID_Carrito_empleado;
    private int ID_Empleado;
    private Date Fecha_Creacion;
    private ArrayList<Productos> productos;

    public CarritoEmpleado() {}

    public CarritoEmpleado(int ID_Empleado, Date Fecha_Creacion) {
        this.ID_Empleado = ID_Empleado;
        this.Fecha_Creacion = Fecha_Creacion;
    }

    public int getID_Carrito_empleado() {
        return ID_Carrito_empleado;
    }

    public void setID_Carrito_empleado(int ID_Carrito_empleado) {
        this.ID_Carrito_empleado = ID_Carrito_empleado;
    }

    public int getID_Empleado() {
        return ID_Empleado;
    }

    public void setID_Empleado(int ID_Empleado) {
        this.ID_Empleado = ID_Empleado;
    }

    public Date getFecha_Creacion() {
        return Fecha_Creacion;
    }

    public void setFecha_Creacion(Date Fecha_Creacion) {
        this.Fecha_Creacion = Fecha_Creacion;
    }

    public ArrayList<Productos> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }
}