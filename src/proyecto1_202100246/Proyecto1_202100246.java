package proyecto1_202100246;

import javax.swing.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
/**
 *
 * @author daniel
 */
public class Proyecto1_202100246 extends JFrame {
    
    //Vectores del inventario
    static String[] nombre = new String[100];
    static String[] categoria = new String[100];
    static double[] precio = new double[100];
    static int[] cantidad = new int[100];
    static String[] codigo = new String[100];
    
    static int contador =0;
    
    JButton btnAgregar = new JButton("Agregar producto");
    JButton btnBuscar = new JButton("Buscar producto");
    JButton btnEliminar = new JButton("Eliminar producto");
    JButton btnVenta = new JButton("Registrar venta");
    JButton btnReporte = new JButton("Reporte");
    JButton btnDatos = new JButton("Datos estudiante");
    
    public Proyecto1_202100246(){    
        setTitle("Sistema Inventario Tienda");
        setSize(400,400);
        setLayout(null);
        
        btnAgregar.setBounds(100,30,200,30);
        add(btnAgregar);
        btnBuscar.setBounds(100,70,200,30);
        add(btnBuscar);
        btnEliminar.setBounds(100,110,200,30);
        add(btnEliminar);
        btnVenta.setBounds(100,150,200,30);
        add(btnVenta);
        btnReporte.setBounds(100,190,200,30);
        add(btnReporte);
        btnDatos.setBounds(100,230,200,30);
        add(btnDatos);
        
       btnAgregar.addActionListener(e-> agregarProducto());
       btnBuscar.addActionListener(e -> buscarProducto());
       btnEliminar.addActionListener(e -> eliminarProducto());
       btnVenta.addActionListener(e -> registrarVenta());
       btnReporte.addActionListener(e -> generarReporte());
       btnDatos.addActionListener(e -> datosEstudiante());
    }
    
    public void agregarProducto(){
        String cod = JOptionPane.showInputDialog("Codigo");
        
        //Validar codigo duplicado
        
        for(int i = 0; i < contador; i++){
            if(codigo[i].equals(cod)){
                JOptionPane.showMessageDialog(null,"El codigo ya existe");
                return;
            }            
        }
        String n = JOptionPane.showInputDialog("Nombre del producto");
        String c = JOptionPane.showInputDialog("Categoria");
        double p = Double.parseDouble(JOptionPane.showInputDialog("Precio"));
        int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad"));
        
        //validar precio
        if(cant < 0){
            JOptionPane.showMessageDialog(null,"El precio debe ser positivo");
            return;
        }
        //Validar cantidad
        if(cant < 0){
            JOptionPane.showMessageDialog(null,"Cantidad invalida");
            return;
        }
        nombre[contador] = n;
        categoria[contador] = c;
        precio[contador] = p;
        cantidad[contador] = cant;
        codigo[contador] = cod;
        
        contador++;
        
        JOptionPane.showMessageDialog(null,"Producto agregado correctamente");
    }
    
    public void buscarProducto(){
        String buscar = JOptionPane.showInputDialog("Ingrese el codigo del producto");
        for(int i = 0; i < contador; i++){
            if(codigo[i].equals(buscar)){
                JOptionPane.showMessageDialog(null,
                        "nombre: " + nombre[i] +
                        "\nCategoria: " + categoria[i] +
                        "\nPrecio: " + precio[i] +       
                        "\nCantidad: " + cantidad[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Producto no encontrado");
    }
    
    public void eliminarProducto(){
        String buscar = JOptionPane.showInputDialog("Codigo del producto a eliminar");
        
        for(int i = 0; i < contador; i++){
            if(codigo[i].equals(buscar)){
                
                nombre[i] = nombre[contador-1];
                categoria[i] = categoria[contador-1];
                precio[i] = precio[contador-1];
                cantidad[i] = cantidad[contador-1];
                codigo[i] = codigo[contador-1];
                
                contador--;
                
                JOptionPane.showMessageDialog(null,"Producto eliminado");
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Producto no encontrado");
    }
    
    public void registrarVenta(){
        String cod =JOptionPane.showInputDialog("Codigo del producto");
        int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a vender"));
        
        for(int i = 0; i < contador; i++){
            if(codigo[i].equals(cod)){
                if(cant <= cantidad[i]){
                    cantidad[i] -= cant;
                    double total = cant * precio[i];
                    try{
                    FileWriter archivo = new FileWriter("ventas.txt", true);
                    PrintWriter escribir = new PrintWriter(archivo);
                    escribir.println(
                        codigo[i] + "," +
                        nombre[i] + "," +
                        cant + "," +
                        total + "," +
                        LocalDateTime.now()
                    );
                    escribir.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Error al guardar venta");
                }
                JOptionPane.showMessageDialog(null,"Venta registrada\nTotal: "+total);
            }else{
                JOptionPane.showMessageDialog(null,"No hay suficiente stock");
            }
            return;

            }
        }
        JOptionPane.showMessageDialog(null,"Producto no encontrado");
    }
    
    public void datosEstudiante(){
        JOptionPane.showMessageDialog(null,
                "Nombre: Sergio Daniel Hernandez Juarez\nCarnet: 202100246\nCurso: IPC1");
    }
    
    public void generarReporte(){
        try{
            FileWriter archivo = new FileWriter("Reporte_stock.html");
            PrintWriter escribir = new PrintWriter(archivo);
            
            escribir.println("<html>");
            escribir.println("<head><title>Reporte Stock</title></head>");
            escribir.println("<body>");
            escribir.println("<h1>Inventario</h1>");
            escribir.println("<table border='1'>");

            escribir.println("<tr>");
            escribir.println("<th>Codigo</th>");
            escribir.println("<th>Nombre</th>");
            escribir.println("<th>Categoria</th>");
            escribir.println("<th>Precio</th>");
            escribir.println("<th>Cantidad</th>");
            escribir.println("</tr>");
            
            for(int i = 0; i < contador; i++){
               escribir.println("<tr>");
               escribir.println("<td>"+codigo[i]+"</td>");
               escribir.println("<td>"+nombre[i]+"</td>");
               escribir.println("<td>"+categoria[i]+"</td>");
               escribir.println("<td>"+precio[i]+"</td>");
               escribir.println("<td>"+cantidad[i]+"</td>");
               escribir.println("</tr>");
            }
            
            escribir.println("</table>");
            escribir.println("</body");
            escribir.println("</html");
            
            escribir.close();
            JOptionPane.showMessageDialog(null,"Reporte generado");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al generar reporte");
        }
    }
    public static void main(String[] args) {
        Proyecto1_202100246 ventana = new Proyecto1_202100246();
        ventana.setVisible(true);
    }
    
}
