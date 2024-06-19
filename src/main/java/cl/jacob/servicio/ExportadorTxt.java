package cl.jacob.servicio;
import cl.jacob.modelo.Cliente;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExportadorTxt extends Exportador {

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        String nombreArchivo = fileName + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (Cliente cliente : listaClientes) {
                writer.println("RUN: " + cliente.getRunCliente());
                writer.println("Nombre: " + cliente.getNombreCliente());
                writer.println("Apellido: " + cliente.getApellidoCliente());
                writer.println("Años como cliente: " + cliente.getAniosCliente());
                writer.println("Categoría: " + cliente.getNombreCategoria());
                writer.println();
            }
            System.out.println("Exportación TXT exitosa: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar TXT: " + e.getMessage());
        }
    }
}

