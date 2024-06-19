package cl.jacob.servicio;

import cl.jacob.modelo.Cliente;

import java.util.List;

public abstract class Exportador {
    public abstract void exportar(String fileName, List<Cliente> listaClientes);
}
