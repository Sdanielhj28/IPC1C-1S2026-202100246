package sancarlistaacademy.modelo;

import java.awt.*;
import java.awt.print.*;
import javax.swing.JOptionPane;

public class ReportePDF implements Printable{
    
    private String titulo;
    private String contenido;
    
    public ReportePDF(String titulo, String contenido){
        this.titulo = titulo;
        this.contenido = contenido;
    }
    
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0){
            return NO_SUCH_PAGE;
        }
        Graphics2D g2 = (Graphics2D) graphics;
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString(titulo, 50, 50);

        g2.setFont(new Font("Arial", Font.PLAIN, 11));

        String[] lineas = contenido.split("\n");
        int y = 90;

        for (int i = 0; i < lineas.length; i++){
            g2.drawString(lineas[i], 50, y);
            y += 18;
        }
        return PAGE_EXISTS;
    }
    
    public void generar(){
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);

            boolean imprimir = job.printDialog();

            if (imprimir){
                job.print();
                JOptionPane.showMessageDialog(null, "Reporte generado correctamente");
            }
            
        } catch (PrinterException e){
            JOptionPane.showMessageDialog(null, "Error al generar reporte: " + e.getMessage());
        }
    }
}
/**
 *
 * @author danie
 */
