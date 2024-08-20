package mx.edu.utez.giup.model;

import java.sql.Date;
import java.util.ArrayList;

public class Carrito {

    private int ID_Carrito;
    private int userID;  // Generalizado para cualquier tipo de usuario
    private Date Fecha_Creacion;
    private int Cantidad;
    private ArrayList<Productos> productos;
    private String userType;  // Nuevo campo para almacenar el tipo de usuario

    public Carrito() {}

    public Carrito(int userID, Date Fecha_Creacion, String userType) {
        this.userID = userID;
        this.Fecha_Creacion = Fecha_Creacion;
        this.userType = userType;
    }

    public Carrito(int ID_Carrito, int userID, Date fecha_Creacion, int cantidad, ArrayList<Productos> productos, String userType) {
        this.ID_Carrito = ID_Carrito;
        this.userID = userID;
        this.Fecha_Creacion = fecha_Creacion;
        this.Cantidad = cantidad;
        this.productos = productos;
        this.userType = userType;
    }

    // Getters and setters

    public int getID_Carrito() {
        return ID_Carrito;
    }

    public void setID_Carrito(int ID_Carrito) {
        this.ID_Carrito = ID_Carrito;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}