package DAO;

import java.sql.SQLException;
import java.util.List;
import Model.Cliente;

public interface ICliente {

    // CREATE
    void adicionarCliente(Cliente cliente) throws SQLException;

    // READ
    Cliente buscarCliente(int codCliente) throws SQLException;
    List<Cliente> buscarTodosClientes() throws SQLException;

    // UPDATE
    void atualizarCliente(Cliente cliente) throws SQLException;

    // DELETE
    void removerCliente(int codCliente) throws SQLException;
}
