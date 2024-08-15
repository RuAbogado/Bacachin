package mx.edu.utez.giup.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Productos {

    private int ID_Producto;
    private int ID_Categoria;
    private String Nombre;
    private String Descripcion;
    private float Precio; // Cambiado a float
    private int Stock;
    private Date Fecha_creacion;
    private String Tipo;
    private String Imagen;
    private Boolean Estado;



    public Productos(int ID_Producto, int ID_Categoria, String nombre, String descripcion, float precio, int stock, Date fecha_creacion, String tipo, String imagen, Boolean estado) {
        this.ID_Producto = ID_Producto;
        this.ID_Categoria = ID_Categoria;
        Nombre = nombre;
        Descripcion = descripcion;
        Precio = precio;
        Stock = stock;
        Fecha_creacion = fecha_creacion;
        Tipo = tipo;
        Imagen = imagen;
        Estado = estado;
    }


    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getID_Categoria() {
        return ID_Categoria;
    }

    public void setID_Categoria(int ID_Categoria) {
        this.ID_Categoria = ID_Categoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public Date getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        Fecha_creacion = fecha_creacion;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

}