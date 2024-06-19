package cl.jacob.servicio;
import cl.jacob.modelo.Cliente;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExportadorCsv extends Exportador {

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        String nombreArchivo = fileName + ".csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Cliente cliente : listaClientes) {
                writer.println(cliente.getRunCliente() + "," +
                        cliente.getNombreCliente() + "," +
                        cliente.getApellidoCliente() + "," +
                        cliente.getAniosCliente() + "," +
                        cliente.getNombreCategoria());
            }
            System.out.println("Exportación CSV exitosa: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar CSV: " + e.getMessage());
        }
    }
}

