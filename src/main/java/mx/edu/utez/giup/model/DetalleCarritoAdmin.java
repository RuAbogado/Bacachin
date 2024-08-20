package mx.edu.utez.giup.model;

public class DetalleCarritoAdmin {
    private int idDetalleCarritoAdmin;
    private int idCarritoAdmin;
    private int idProducto;
    private int cantidad;
    private String nombreProducto; // Opcional, si necesitas el nombre del producto
    private float precioProducto;  // Opcional, si necesitas el precio del producto

    // Constructor sin parámetros (por defecto)
    public DetalleCarritoAdmin() {}

    // Constructor con parámetros para inicializar el objeto
    public DetalleCarritoAdmin(int idCarritoAdmin, int idProducto, int cantidad) {
        this.idCarritoAdmin = idCarritoAdmin;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdDetalleCarritoAdmin() {
        return idDetalleCarritoAdmin;
    }

    public void setIdDetalleCarritoAdmin(int idDetalleCarritoAdmin) {
        this.idDetalleCarritoAdmin = idDetalleCarritoAdmin;
    }

    public int getIdCarritoAdmin() {
        return idCarritoAdmin;
    }

    public void setIdCarritoAdmin(int idCarritoAdmin) {
        this.idCarritoAdmin = idCarritoAdmin;
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