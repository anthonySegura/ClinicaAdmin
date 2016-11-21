package tec.clases;

/**
 * Created by anthony on 18/11/16.
 */

public class UsuarioCliente extends Usuario{

    private String username;
    private String fechaRegistro;
    private String ultimoLogin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UsuarioCliente(String username, String nombre, String password, String telefono) {
        super(nombre, password, telefono);
        this.username = username;
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
