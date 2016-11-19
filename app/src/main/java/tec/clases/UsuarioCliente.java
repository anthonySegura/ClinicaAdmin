package tec.clases;


/**
 * Created by anthony on 18/11/16.
 */
public class UsuarioCliente extends Usuario{

    private String fechaRegistro;
    private String ultimoLogin;

    public UsuarioCliente(String nombre, String password, String telefono) {
        super(nombre, password, telefono);
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(String ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }
}
