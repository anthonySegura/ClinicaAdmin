package tec.clases;

/**
 * Created by anthony on 19/11/16.
 */
public class Medicina {

    private String nombreMedicamento;
    private String descripcion;
    private int cantidad;
    private String precioUnidad;


    public Medicina(String nombreMedicamento, String descripcion, int cantidad, String precioUnidad) {
        this.nombreMedicamento = nombreMedicamento;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(String precioUnidad) {
        this.precioUnidad = precioUnidad;
    }
}
