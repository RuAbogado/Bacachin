package mx.edu.utez.giup.model;

import java.sql.Date;

public class Productos {

    private int ID_Producto;
    private int ID_Categoria;
    private String Nombre;
    private String Descripcion;
    private float Precio; // Mantén el tipo float para representar DECIMAL(10, 2)
    private int Stock;
    private Date Fecha_Creacion; // Asegúrate de usar el nombre correcto en español
    private String Marca;
    private String Imagen;
    private boolean Estado; // Cambia Boolean a boolean para simplificar

    public Productos(int ID_Producto, int ID_Categoria, String nombre, String descripcion, float precio, int stock, Date fecha_Creacion, String marca, String imagen, boolean estado) {
        this.ID_Producto = ID_Producto;
        this.ID_Categoria = ID_Categoria;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Precio = precio;
        this.Stock = stock;
        this.Fecha_Creacion = fecha_Creacion;
        this.Marca = marca;
        this.Imagen = imagen;
        this.Estado = estado;
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

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        this.Marca = marca;
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