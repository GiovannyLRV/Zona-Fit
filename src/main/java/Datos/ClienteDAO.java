package Datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import static zona_fit.conexion.Conexion.getConexion;

public class ClienteDAO implements IClienteDAO {
    @Override
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        var sql = "SELECT * FROM cliente ORDER BY id_cliente";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setClient_name(rs.getString("client_name"));
                cliente.setLast_name(rs.getString("last_name"));
                cliente.setMembership(rs.getInt("membership"));
                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al listar cliente " + e.getMessage());
        }
        finally{
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarCliente(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        var con = getConexion();
        var sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId_cliente());
            rs = ps.executeQuery();
            if (rs.next()){
                cliente.setClient_name(rs.getString("client_name"));
                cliente.setLast_name(rs.getString("last_name"));
                cliente.setMembership(rs.getInt("membership"));
                return true;
            }
        }catch (Exception e){
            System.out.println("Error al recuperar cliente por id: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean agregarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        String sql = "INSERT INTO cliente(client_name, last_name, membership)"
                //Parametros posicionales se señalan con = ?
                + " VALUES(?, ?, ?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getClient_name());
            ps.setString(2, cliente.getLast_name());
            ps.setInt(3, cliente.getMembership());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error al agregar al cliente" + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexión" + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "UPDATE cliente SET client_name=?, last_name=?, membership=? " +
                "WHERE id_cliente =?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getClient_name());
            ps.setString(2, cliente.getLast_name());
            ps.setInt(3, cliente.getMembership());
            ps.setInt(4, cliente.getId_cliente());
            ps.execute();
            return true;

        }catch (Exception e){
            System.out.println("Error al modificar cliente" + e.getMessage());
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexión" + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = getConexion();
        var sql = "DELETE FROM cliente " +
                "WHERE id_cliente=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getId_cliente());
            ps.execute();
            return true;
        } catch (Exception e){
            System.out.println("Error al eliminar cliente " + e.getMessage() );
        }
        finally {
            try{
                con.close();
            } catch (Exception e){
                System.out.println("Error al cerrar conexión " + e.getMessage());
            }
        }
        return false;
    }


}
