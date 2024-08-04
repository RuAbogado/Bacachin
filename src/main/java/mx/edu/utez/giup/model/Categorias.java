package mx.edu.utez.giup.model;

public class  Categorias {
    private int ID_Categoria;
    private String nombre;
    private String descripcion;

    public Categorias() {
    }

    public Categorias(int ID_Categoria, String nombre, String descripcion) {
        this.ID_Categoria = ID_Categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getID_Categoria() {
        return ID_Categoria;
    }

    public void setID_Categoria(int ID_Categoria) {
        this.ID_Categoria = ID_Categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}