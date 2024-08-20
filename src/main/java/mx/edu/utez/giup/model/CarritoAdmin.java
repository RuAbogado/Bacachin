package mx.edu.utez.giup.model;

import java.sql.Date;
import java.util.ArrayList;

public class CarritoAdmin {

    private int ID_Carrito_admin; // ID del carrito de admin
    private int ID_Admin;         // ID del administrador
    private Date Fecha_Creacion;  // Fecha de creación del carrito
    private ArrayList<Productos> productos; // Lista de productos en el carrito

    // Constructor vacío
    public CarritoAdmin() {}

    // Constructor con parámetros
    public CarritoAdmin(int ID_Admin, Date Fecha_Creacion) {
        this.ID_Admin = ID_Admin;
        this.Fecha_Creacion = Fecha_Creacion;
    }

    // Getters y setters
    public int getID_Carrito_admin() {
        return ID_Carrito_admin;
    }

    public void setID_Carrito_admin(int ID_Carrito_admin) {
        this.ID_Carrito_admin = ID_Carrito_admin;
    }

    public int getID_Admin() {
        return ID_Admin;
    }

    public void setID_Admin(int ID_Admin) {
        this.ID_Admin = ID_Admin;
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