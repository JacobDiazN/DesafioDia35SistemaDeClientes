package cl.jacob.servicio;

import cl.jacob.modelo.Cliente;
import cl.jacob.modelo.CategoriaEnum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivoServicio {

    public List<Cliente> cargarDatos(String fileName) {
        List<Cliente> clientes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(",");
                if (datos.length == 5) {
                    String runCliente = datos[0].trim();
                    String nombreCliente = datos[1].trim();
                    String apellidoCliente = datos[2].trim();
                    int aniosCliente = Integer.parseInt(datos[3].trim());
                    CategoriaEnum nombreCategoria = CategoriaEnum.valueOf(datos[4].trim().toUpperCase()); // Convertir a mayúsculas y obtener el enum

                    Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
                    clientes.add(cliente);
                } else {
                    System.out.println("Error en el formato de línea en el archivo: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar datos desde " + fileName + ": " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al convertir categoría del cliente: " + e.getMessage());
        }
        return clientes;
    }
}



