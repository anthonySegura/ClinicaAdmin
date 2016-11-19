package tec.clases;

/**
 * Created by anthony on 18/11/16.
 */
public class Administrador extends Usuario {

    private String departamento;
    private String salario;
    private String cedula;

    public Administrador(String nombre, String password, String telefono, String departamento, String salario, String cedula) {
        super(nombre, password, telefono);
        this.departamento = departamento;
        this.salario = salario;
        this.cedula = cedula;
    }

    public Administrador(){

    }

    public Administrador(String departamento, String salario, String cedula) {
        this.departamento = departamento;
        this.salario = salario;
        this.cedula = cedula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
