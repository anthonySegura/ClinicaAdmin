package tec.clases;

/**
 * Created by anthony on 18/11/16.
 */
public class Usuario {

    private String nombre;
    private String password;
    private String telefono;

    public Usuario(String nombre, String password, String telefono) {
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
    }

    public Usuario(){

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
