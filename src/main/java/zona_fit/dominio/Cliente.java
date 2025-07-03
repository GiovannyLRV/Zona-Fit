package zona_fit.dominio;

import java.util.Objects;

public class Cliente {
    private int id_cliente;
    private String client_name;
    private String last_name;
    private int membership;

    public Cliente(){}

    public Cliente(int id_cliente){
        this.id_cliente = id_cliente;
    }

    public Cliente(String client_name, String last_name, int membership){
        this.client_name = client_name;
        this.last_name = last_name;
        this.membership = membership;
    }
    public Cliente(int id_cliente, String client_name, String last_name, int membership){
        //llamada al constructor con los valores que necesitamos
        this(client_name, last_name, membership);
        this.id_cliente = id_cliente;

    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id_cliente=" + id_cliente +
                ", client_name='" + client_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", membership=" + membership +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id_cliente == cliente.id_cliente && membership == cliente.membership && Objects.equals(client_name, cliente.client_name) && Objects.equals(last_name, cliente.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cliente, client_name, last_name, membership);
    }
}
