package mx.edu.utez.giup.model;

import java.sql.Date;

public class Productos {

    private int ID_Producto;
    private int ID_Categoria;
    private String Nombre;
    private String Descripcion;
    private float Precio; // Cambiado a float
    private int Stock;
    private Date Fecha_Creacion;
    private int ID_Marca; // Cambiado de String Marca a int ID_Marca
    private String Imagen;
    private boolean Estado;

    // Constructor
    public Productos(int ID_Producto, int ID_Categoria, String nombre, String descripcion, float precio, int stock, Date fecha_Creacion, int ID_Marca, String imagen, boolean estado) {
        this.ID_Producto = ID_Producto;
        this.ID_Categoria = ID_Categoria;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Precio = precio;
        this.Stock = stock;
        this.Fecha_Creacion = fecha_Creacion;
        this.ID_Marca = ID_Marca; // Ajustado para reflejar la clave externa de Marcas
        this.Imagen = imagen;
        this.Estado = estado;
    }

    public Productos() {

    }

    // Getters y Setters

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
        this.Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        this.Precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        this.Stock = stock;
    }

    public Date getFecha_Creacion() {
        return Fecha_Creacion;
    }

    public void setFecha_Creacion(Date fecha_Creacion) {
        this.Fecha_Creacion = fecha_Creacion;
    }

    public int getID_Marca() {
        return ID_Marca; // Devuelve el ID de la marca en lugar del nombre
    }

    public void setID_Marca(int ID_Marca) {
        this.ID_Marca = ID_Marca; // Ajustado para la clave externa
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        this.Imagen = imagen;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean estado) {
        this.Estado = estado;
    }
}