package zona_fit.presentacion;

import Datos.ClienteDAO;
import Datos.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp (){
        var salir = false;
        var consola = new Scanner(System.in);
        //Creacion de objeto clase cliente Dao
        IClienteDAO clienteDao = new ClienteDAO();
        while(!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);

            } catch (Exception e){
                System.out.println("Error al ejecutar opciones " + e.getMessage());
            }
            System.out.println();
        }

    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Zona Fit (Gym)***
                1.- Listar Clientes
                2.- Buscar Cliente
                3.- Agregar Cliente
                4.- Modificar Cliente
                5.- Eliminar Cliente
                6.- Salir
                Elija una opción:\s""");
        return Integer.parseInt((consola.nextLine()));
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {// 1. listar cliente
                System.out.println("---Listado de Clientes---");
                var clientes = clienteDAO.listarCliente();
                clientes.forEach(System.out::println);
            }
            case 2 -> {//Buscar cliente por id
                System.out.print("Introduce el numero ID del cliente a buscar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarCliente(cliente);
                if (encontrado)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente no se pudo  encontrar al cliente: " + cliente);
            }
            case 3 -> {//Agregar cliente
                System.out.println("--- Agregar Cliente ---");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membership = Integer.parseInt(consola.nextLine());
                //Creamos el objeto cliente sin id
                var cliente = new Cliente(nombre, apellido, membership);
                var agregado = clienteDAO.agregarCliente(cliente);
                if (agregado)
                    System.out.println("Cliente agregado: " + cliente);
                else
                    System.out.println("Cliente no se pudo  agregar al cliente: " + cliente);
            }
            case 4 -> {//Modificar cliente
                System.out.println("--- Modificar información del Cliente ---");
                System.out.print("ID del cliente: ");
                var clienteID = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membership = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(clienteID, nombre, apellido, membership);
                var modificado = clienteDAO.modificarCliente(cliente);
                if (modificado)
                    System.out.println("Cliente modificado: " + cliente);
                else
                    System.out.println("Cliente no se pudo modificar al cliente: " + cliente);

            }
            case 5 -> {//Eliminar cliente
                System.out.println("--- Eliminar Cliente ---");
                System.out.print("Ingresa el ID del cliente que deseas eliminar: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if (eliminado)
                    System.out.println("Cliente eliminado de la base de datos: " + cliente);
                else
                    System.out.println("Cliente no se pudo eliminar el cliente: " + cliente);
            }
            case 6 -> {//Salida del programa
                System.out.println("Gracias por utilizar la App de ZonaFit, vuelve pronto!");
                salir = true;
            }
            //En caso de selección invalida
            default -> System.out.println("La selección es invalida!");
        }
        return salir;
    }
}
