package mx.edu.utez.giup.model;

public class Marcas {
    private int ID_Marcas;
    private String Nombre;
    private String Descripcion;
    private boolean Estado;

    public Marcas() {
    }

    // Constructor con todos los parámetros
    public Marcas(int ID_Marcas, String Nombre, String Descripcion, boolean Estado) {
        this.ID_Marcas = ID_Marcas;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
    }

    // Getters y Setters
    public int getID_Marcas() {
        return ID_Marcas;
    }

    public void setID_Marcas(int ID_Marcas) {
        this.ID_Marcas = ID_Marcas;
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

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
}