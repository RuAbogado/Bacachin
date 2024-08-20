package mx.edu.utez.giup.model;

public class DetalleCarritoEmpleado {
    private int idDetalleCarritoEmpleado;
    private int idCarritoEmpleado;
    private int idProducto;
    private int cantidad;
    private String nombreProducto; // Opcional, si necesitas el nombre del producto
    private float precioProducto;  // Opcional, si necesitas el precio del producto

    // Constructor por defecto
    public DetalleCarritoEmpleado() {}

    // Constructor con parámetros
    public DetalleCarritoEmpleado(int idCarritoEmpleado, int idProducto, int cantidad) {
        this.idCarritoEmpleado = idCarritoEmpleado;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }


    // Getters y Setters
    public int getIdDetalleCarritoEmpleado() {
        return idDetalleCarritoEmpleado;
    }

    public void setIdDetalleCarritoEmpleado(int idDetalleCarritoEmpleado) {
        this.idDetalleCarritoEmpleado = idDetalleCarritoEmpleado;
    }

    public int getIdCarritoEmpleado() {
        return idCarritoEmpleado;
    }

    public void setIdCarritoEmpleado(int idCarritoEmpleado) {
        this.idCarritoEmpleado = idCarritoEmpleado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(float precioProducto) {
        this.precioProducto = precioProducto;
    }
}