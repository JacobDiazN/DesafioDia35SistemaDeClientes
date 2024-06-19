package cl.jacob.servicio;

import cl.jacob.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteServicio {
    private List<Cliente> listaClientes;

    public ClienteServicio() {
        this.listaClientes = new ArrayList<>();
    }

    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            System.out.println("No hay clientes para listar.");
        } else {
            for (Cliente cliente : listaClientes) {
                System.out.println(cliente);
            }
        }
    }

    public void agregarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new NullPointerException("El cliente no puede ser nulo");
        }
        listaClientes.add(cliente);
    }

    public void editarCliente(Cliente cliente) {
        for (Cliente c : listaClientes) {
            if (c.getRunCliente().equals(cliente.getRunCliente())) {
                c.setNombreCliente(cliente.getNombreCliente());
                c.setApellidoCliente(cliente.getApellidoCliente());
                c.setAniosCliente(cliente.getAniosCliente());
                c.setNombreCategoria(cliente.getNombreCategoria());
                break;
            }
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void actualizarListaClientes(List<Cliente> nuevosClientes) {
        listaClientes.clear(); // Limpiar la lista actual
        listaClientes.addAll(nuevosClientes); // Añadir los nuevos clientes importados
    }

    public Cliente buscarClientePorRun(String runCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getRunCliente().equals(runCliente)) {
                return cliente;
            }
        }
        return null;
    }
}

