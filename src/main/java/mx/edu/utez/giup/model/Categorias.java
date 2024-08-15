package mx.edu.utez.giup.model;

public class  Categorias {
    private int ID_Categoria;
    private String nombre;
    private String descripcion;
    private boolean estado;

    public Categorias() {
    }

    public Categorias(int ID_Categoria, String nombre, String descripcion, boolean estado) {
        this.ID_Categoria = ID_Categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado=estado;
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

    public void setEstado(Boolean estado) {this.estado = estado;}

}