package AppAdministrador.vista;

import javax.swing.*;

public class VistaPlatillo extends JFrame {

    private JLabel lblImagen;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JLabel lblTamanno;
    private JLabel lblPiezasPorPorcion;
    private JLabel lblCaloriasXPorcion;
    private JLabel lblCaloriasXPieza;
    private JLabel lblPrecio;
    private JPanel panel;

    public VistaPlatillo(String codigo,String nombre,String descrip,String tamanno,String piezaXPorcion,
                         String caloriasXPorcion,String caloriasXPieza,String precio,String ruta){
        super("Ver Platillo");
        setContentPane(panel);
        this.pack();
        lblCodigo.setText(codigo);
        lblNombre.setText(nombre);
        lblDescripcion.setText(descrip);
        lblTamanno.setText(tamanno);
        lblPiezasPorPorcion.setText(piezaXPorcion);
        lblCaloriasXPorcion.setText(caloriasXPorcion);
        lblCaloriasXPieza.setText(caloriasXPieza);
        lblPrecio.setText(precio);

        ImageIcon ii = new ImageIcon(ruta);
        System.out.println("RUTA: "+ruta);
        lblImagen.setIcon(ii);

    }
}
