package mx.edu.utez.giup.model;

public class  Categorias {
    private int ID_Categoria;
    private String Nombre;
    private String Descripcion;
    private boolean Estado;

    public Categorias() {
    }

    // Constructor con todos los parámetros
    public Categorias(int ID_Categoria, String Nombre, String Descripcion, boolean Estado) {
        this.ID_Categoria = ID_Categoria;
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
    }

    // Getters y Setters
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

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }
}