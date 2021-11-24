package general;

public class PedidoExpress extends Pedido{
    private String celular;
    private String direccion;

    public PedidoExpress(String nombre) {
        super.nombre = nombre;
    }
    public PedidoExpress(String nombre, String celular, String direccion) {
        super(nombre);
        this.celular = celular;
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
