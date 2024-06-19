package cl.jacob.test;

import cl.jacob.modelo.CategoriaEnum;
import cl.jacob.modelo.Cliente;
import cl.jacob.servicio.ClienteServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteServicioTest {

    private ClienteServicio clienteServicio;

    @BeforeEach
    public void setUp() {
        clienteServicio = new ClienteServicio();
    }

    @Test
    public void agregarClienteTest() {
        Cliente cliente = new Cliente("12345678-9", "Juan", "Perez", 5, CategoriaEnum.ACTIVO);
        clienteServicio.agregarCliente(cliente);

        assertFalse(clienteServicio.getListaClientes().isEmpty());
        assertEquals(1, clienteServicio.getListaClientes().size());
        assertEquals("12345678-9", clienteServicio.getListaClientes().get(0).getRunCliente());
    }

    @Test
    public void agregarClienteNullTest() {
        Cliente cliente = null;
        Exception exception = assertThrows(NullPointerException.class, () -> {
            clienteServicio.agregarCliente(cliente);
        });

        String expectedMessage = "El cliente no puede ser nulo";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
