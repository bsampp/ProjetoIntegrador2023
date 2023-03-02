package DAO;

import Model.Cliente;
import Model.TipoCliente;
import DAO.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements ICliente {

    private Connection conexao;

    public ClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void cadastrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (codCliente, telefone, nome, email, cpf, tipocliente) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, cliente.getCodCliente());
        pstmt.setString(2, cliente.getTelefone());
        pstmt.setString(3, cliente.getNome());
        pstmt.setString(4, cliente.getEmail());
        pstmt.setString(5, cliente.getCpf());
        pstmt.setString(6, cliente.getTipoCliente().toString());
        pstmt.execute();
        pstmt.close();
    }

    @Override
    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET telefone=?, nome=?, email=?, cpf=?, tipocliente=? WHERE codCliente=?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, cliente.getTelefone());
        pstmt.setString(2, cliente.getNome());
        pstmt.setString(3, cliente.getEmail());
        pstmt.setString(4, cliente.getCpf());
        pstmt.setString(5, cliente.getTipoCliente().toString());
        pstmt.setInt(6, cliente.getCodCliente());
        pstmt.execute();
        pstmt.close();
    }

    @Override
    public void removerCliente(int codCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE codCliente=?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, codCliente);
        pstmt.execute();
        pstmt.close();
    }

    @Override
    public Cliente buscarCliente(int codCliente) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE codCliente=?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, codCliente);
        ResultSet rs = pstmt.executeQuery();
        Cliente cliente = null;
        if (rs.next()) {
            String telefone = rs.getString("telefone");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String cpf = rs.getString("cpf");
            TipoCliente tipoCliente = TipoCliente.valueOf(rs.getString("tipocliente"));
            cliente = new Cliente(codCliente, telefone, nome, email, cpf, tipoCliente);
        }
        rs.close();
        pstmt.close();
        return cliente;
    }

    @Override
    public List<Cliente> buscarTodosClientes() throws SQLException {
        String sql = "SELECT * FROM cliente";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Cliente> lista = new ArrayList<>();
        while (rs.next()) {
            int codCliente = rs.getInt("codCliente");
            String telefone = rs.getString("telefone");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String cpf = rs.getString("cpf");
            TipoCliente tipoCliente = TipoCliente.valueOf(rs.getString("tipocliente"));
            Cliente cliente = new Cliente(codCliente, telefone, nome, email, cpf, tipoCliente);
            lista.add(cliente);
        }
        rs.close();
        pstmt.close();
        return lista;
    }


}
