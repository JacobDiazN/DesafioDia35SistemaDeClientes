package cl.jacob.vistas;

import cl.jacob.modelo.CategoriaEnum;
import cl.jacob.modelo.Cliente;
import cl.jacob.servicio.ArchivoServicio;
import cl.jacob.servicio.ExportadorCsv;
import cl.jacob.servicio.ExportadorTxt;
import cl.jacob.servicio.ClienteServicio;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private ClienteServicio clienteServicio;
    private ArchivoServicio archivoServicio;
    private ExportadorCsv exportadorCsv;
    private ExportadorTxt exportadorTxt;
    private final String fileName = "Clientes";
    private final String fileName1 = "DBClientes.csv";
    private Scanner scanner;
    private boolean datosImportados = false;

    public Menu() {
        this.clienteServicio = new ClienteServicio();
        this.archivoServicio = new ArchivoServicio();
        this.exportadorCsv = new ExportadorCsv();
        this.exportadorTxt = new ExportadorTxt();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarMenu() {
        while (true) {
            System.out.println("Menú Principal");
            System.out.println("1. Listar Clientes");
            System.out.println("2. Agregar Cliente");
            System.out.println("3. Editar Cliente");
            System.out.println("4. Importar Datos");
            System.out.println("5. Exportar Datos");
            System.out.println("6. Terminar Programa");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    listarClientes();
                    break;
                case 2:
                    agregarCliente();
                    break;
                case 3:
                    if (datosImportados) {
                        editarClienteMenu();
                    } else {
                        System.out.println("Debe importar los datos primero antes de editar.");
                    }
                case 4:
                    importarDatos();
                    break;
                case 5:
                    exportarDatos();
                    break;
                case 6:
                    terminarPrograma();
                    return;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }

    private void listarClientes() {
        clienteServicio.listarClientes();
    }

    private void agregarCliente() {
        System.out.println("Ingrese RUN del Cliente: ");
        String runCliente = scanner.nextLine();

        System.out.println("Ingrese Nombre del Cliente: ");
        String nombreCliente = scanner.nextLine();

        System.out.println("Ingrese Apellido del Cliente: ");
        String apellidoCliente = scanner.nextLine();

        System.out.println("Ingrese Años como Cliente: ");
        int aniosCliente = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese Categoría del Cliente (ACTIVO/INACTIVO):");
        String categoria = scanner.nextLine();
        CategoriaEnum nombreCategoria = CategoriaEnum.valueOf(categoria.toUpperCase());

        Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
        clienteServicio.agregarCliente(cliente);
    }

    private void editarClienteMenu() {
        System.out.println("Seleccione qué desea hacer:");
        System.out.println("1. Cambiar el estado del Cliente");
        System.out.println("2. Editar los datos ingresados del Cliente");

        int opcionEditar = scanner.nextInt();
        scanner.nextLine();

        if (opcionEditar == 1) {
            System.out.println("Ingrese RUN del Cliente a editar:");
            String runCliente = scanner.nextLine();

            Cliente cliente = clienteServicio.buscarClientePorRun(runCliente);

            if (cliente != null) {
                System.out.println("-----Actualizando estado del Cliente-----");
                System.out.println("El estado actual es: " + cliente.getNombreCategoria());

                System.out.println("1. Cambiar el estado del Cliente a Inactivo");
                System.out.println("2. Cambiar el estado del cliente a Activo");
                int opcionEstado = scanner.nextInt();
                scanner.nextLine();

                if (opcionEstado == 1) {
                    cliente.setNombreCategoria(CategoriaEnum.INACTIVO);
                    System.out.println("Estado del cliente cambiado a Inactivo.");
                    clienteServicio.editarCliente(cliente);
                } else if (opcionEstado == 2) {
                    cliente.setNombreCategoria(CategoriaEnum.ACTIVO);
                    System.out.println("Estado del cliente cambiado a Activo.");
                    clienteServicio.editarCliente(cliente);
                } else {
                    System.out.println("Opción no válida.");
                }
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } else if (opcionEditar == 2) {
            System.out.println("Ingrese RUN del Cliente a editar:");
            String runCliente = scanner.nextLine();

            Cliente cliente = clienteServicio.buscarClientePorRun(runCliente);

            if (cliente != null) {
                System.out.println("----Actualizando datos del Cliente----");
                System.out.println("1. El RUN del Cliente es: " + cliente.getRunCliente());
                System.out.println("2. El Nombre del Cliente es: " + cliente.getNombreCliente());
                System.out.println("3. El Apellido del Cliente es: " + cliente.getApellidoCliente());
                System.out.println("4. Los años como Cliente son: " + cliente.getAniosCliente());

                System.out.println("Ingrese opción a editar de los datos del cliente:");
                int opcionDato = scanner.nextInt();
                scanner.nextLine();

                switch (opcionDato) {
                    case 1:
                        System.out.println("Ingrese nuevo RUN del Cliente:");
                        String nuevoRun = scanner.nextLine();
                        cliente.setRunCliente(nuevoRun);
                        break;
                    case 2:
                        System.out.println("Ingrese nuevo Nombre del Cliente:");
                        String nuevoNombre = scanner.nextLine();
                        cliente.setNombreCliente(nuevoNombre);
                        break;
                    case 3:
                        System.out.println("Ingrese nuevo Apellido del Cliente:");
                        String nuevoApellido = scanner.nextLine();
                        cliente.setApellidoCliente(nuevoApellido);
                        break;
                    case 4:
                        System.out.println("Ingrese nuevos Años como Cliente:");
                        int nuevosAnios = scanner.nextInt();
                        scanner.nextLine();
                        cliente.setAniosCliente(nuevosAnios);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
                clienteServicio.editarCliente(cliente);
                System.out.println("Datos cambiados con éxito.");
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }

    private void importarDatos() {
        List<Cliente> clientesImportados = archivoServicio.cargarDatos(fileName1);

        if (clientesImportados != null && !clientesImportados.isEmpty()) {
            clienteServicio.actualizarListaClientes(clientesImportados);
            datosImportados = true;
            System.out.println("Datos importados correctamente desde DBClientes.csv");
        } else {
            System.out.println("Error al cargar datos desde DBClientes.csv");
        }
    }

    private void exportarDatos() {
        System.out.println("¿Exportar en formato CSV o TXT?");
        String formato = scanner.nextLine().toUpperCase();

        if (formato.equals("CSV")) {
            exportadorCsv.exportar(fileName, clienteServicio.getListaClientes());
        } else if (formato.equals("TXT")) {
            exportadorTxt.exportar(fileName, clienteServicio.getListaClientes());
        } else {
            System.out.println("Formato no válido.");
        }
    }

    private void terminarPrograma() {
        System.out.println("Programa terminado.");
    }
}



