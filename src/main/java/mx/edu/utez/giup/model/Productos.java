package mx.edu.utez.giup.model;

public class Productos {

        private int ID_Producto;
        private int ID_categoria;
        private String Nombre;
        private String Descripcion;
        private int Precio;
        private int Stock;
        private String Fecha_creacion;
        private String Tipo;
        private String Imagen;

        public Productos() {

        }

    public Productos(int ID_Producto, int ID_categoria, String nombre, String descripcion, int precio, int stock, String fecha_creacion, String tipo, String imagen) {
        this.ID_Producto = ID_Producto;
        this.ID_categoria = ID_categoria;
        Nombre = nombre;
        Descripcion = descripcion;
        Precio = precio;
        Stock = stock;
        Fecha_creacion = fecha_creacion;
        Tipo = tipo;
        Imagen = imagen;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getID_categoria() {
        return ID_categoria;
    }

    public void setID_categoria(int ID_categoria) {
        this.ID_categoria = ID_categoria;
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

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getFecha_creacion() {
        return Fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
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
}
