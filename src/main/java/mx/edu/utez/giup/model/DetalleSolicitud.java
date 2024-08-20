package mx.edu.utez.giup.model;

public class DetalleSolicitud {
    private int ID_Detalle_Solicitud;
    private int ID_Solicitud;
    private int ID_Producto;
    private int Cantidad;
    private float Precio;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(int ID_Detalle_Solicitud, int ID_Solicitud, int ID_Producto, int Cantidad, float Precio) {
        this.ID_Detalle_Solicitud = ID_Detalle_Solicitud;
        this.ID_Solicitud = ID_Solicitud;
        this.ID_Producto = ID_Producto;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
    }

    public int getID_Detalle_Solicitud() {
        return ID_Detalle_Solicitud;
    }

    public void setID_Detalle_Solicitud(int ID_Detalle_Solicitud) {
        this.ID_Detalle_Solicitud = ID_Detalle_Solicitud;
    }

    public int getID_Solicitud() {
        return ID_Solicitud;
    }

    public void setID_Solicitud(int ID_Solicitud) {
        this.ID_Solicitud = ID_Solicitud;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }
}