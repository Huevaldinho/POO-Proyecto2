package general;

/**
 * Modelo de un pedido que se pasa a recoger
 */
public class PedidoRecoger extends Pedido{
    private String nombreRecoge;
    private String celular;

    public PedidoRecoger(String nombre) {
        super.nombre = nombre;
    }
    public PedidoRecoger(String nombre, String nombreRecoge, String celular) {
        super(nombre);
        this.nombreRecoge = nombreRecoge;
        this.celular = celular;
    }

    public String getNombreRecoge() {
        return nombreRecoge;
    }

    public void setNombreRecoge(String nombreRecoge) {
        this.nombreRecoge = nombreRecoge;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
