package mx.edu.utez.giup.model;

import java.sql.Date;

public class Proveedores {

    private int ID_Proveedor;
    private String Nombre;
    private String Informacion_Contacto;
    private String Direccion;


    public Proveedores(int ID_Proveedor, String nombre, String informacion_Contacto, String direccion) {
        this.ID_Proveedor = ID_Proveedor;
        Nombre = nombre;
        Informacion_Contacto = informacion_Contacto;
        Direccion = direccion;
    }

    public int getID_Proveedor() {
        return ID_Proveedor;
    }

    public void setID_Proveedor(int ID_Proveedor) {
        this.ID_Proveedor = ID_Proveedor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getInformacion_Contacto() {
        return Informacion_Contacto;
    }

    public void setInformacion_Contacto(String informacion_Contacto) {
        Informacion_Contacto = informacion_Contacto;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
}
