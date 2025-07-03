package Datos;

import zona_fit.dominio.Cliente;

import java.util.List;


public interface IClienteDAO {
    List<Cliente> listarCliente();
    boolean buscarCliente(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
}
