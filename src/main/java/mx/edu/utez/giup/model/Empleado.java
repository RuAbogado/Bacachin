package mx.edu.utez.giup.model;

import java.sql.Date;

public class Empleado {

    private int ID_Empleado;
    private int ID_Usuario;
    private Date Fecha_Contratacion;
    private int Salario;


    public int getID_Empleado() {
        return ID_Empleado;
    }

    public void setID_Empleado(int ID_Empleado) {
        this.ID_Empleado = ID_Empleado;
    }

    public int getID_Usuario() {
        return ID_Usuario;
    }

    public void setID_Usuario(int ID_Usuario) {
        this.ID_Usuario = ID_Usuario;
    }

    public Date getFecha_Contratacion() {
        return Fecha_Contratacion;
    }

    public void setFecha_Contratacion(Date fecha_Contratacion) {
        Fecha_Contratacion = fecha_Contratacion;
    }

    public int getSalario() {
        return Salario;
    }

    public void setSalario(int salario) {
        Salario = salario;
    }
}
