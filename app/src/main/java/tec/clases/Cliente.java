package tec.clases;

import java.util.Date;

/**
 * Created by anthony on 18/11/16.
 */
public class Cliente extends Usuario{

    private Date fechaRegistro;
    private Date ultimoLogin;

    public Cliente(String nombre, String password, String telefono) {
        super(nombre, password, telefono);
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }
}
