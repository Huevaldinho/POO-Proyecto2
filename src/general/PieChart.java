package general;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.IOException;
import java.text.ParseException;

/**
 * Objeto para generar un grafico de pastel
 */

public class PieChart extends JFrame {
    public PieChart( String title,double[] datos) {
        super( title );
        setContentPane(createDemoPanel(datos));
    }

    private PieDataset createDataset(double[] datos){
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue( "Para Recoger" , new Double( datos[0] ) );
        dataset.setValue( "Express" , new Double(  datos[1] ) );
        dataset.setValue( "En Sitio" , new Double(  datos[2] ) );
        return dataset;
    }

    private static JFreeChart createChart( PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart(
                "Relación entre lugares de pedido",   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        return chart;
    }

    public JPanel createDemoPanel( double[]datos){
        JFreeChart chart = createChart(createDataset(datos) );
        return new ChartPanel( chart );
    }

}