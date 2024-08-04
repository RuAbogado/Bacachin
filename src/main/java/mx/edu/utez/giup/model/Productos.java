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

    // Constructor con parámetros
    public Productos(int ID_Producto, int ID_Categoria, String Nombre, String Descripcion, float Precio, int Stock, String Fecha_creacion, String Tipo, String Imagen) {
        this.ID_Producto = ID_Producto;
        this.ID_Categoria = ID_Categoria;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Stock = Stock;
        setFecha_creacion(Fecha_creacion); // Llama al setter para parsear la fecha
        this.Tipo = Tipo;
        this.Imagen = Imagen;
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

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public Date getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = sdf.parse(fecha_creacion);
            this.Fecha_creacion = new Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
}