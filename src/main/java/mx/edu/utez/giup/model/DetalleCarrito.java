package mx.edu.utez.giup.model;

public class DetalleCarrito {
    private int idDetalleCarrito;
    private int idCarrito;
    private int idProducto;
    private int cantidad;
    private Productos producto;  // Objeto de tipo Productos

    public DetalleCarrito() {}

    public DetalleCarrito(int idCarrito, int idProducto, int cantidad) {
        this.idCarrito = idCarrito;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdDetalleCarrito() {
        return idDetalleCarrito;
    }

    public void setIdDetalleCarrito(int idDetalleCarrito) {
        this.idDetalleCarrito = idDetalleCarrito;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
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

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }
}