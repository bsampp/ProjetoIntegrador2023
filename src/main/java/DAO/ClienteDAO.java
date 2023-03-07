package DAO;

import Model.Cliente;
import Model.TipoCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClienteDAO implements ICliente {

    private Connection conexao;

    public ClienteDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void cadastrarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (telefone, nome, email, cadastro, tipoCliente) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, cliente.getTelefone());
        pstmt.setString(2, cliente.getNome());
        pstmt.setString(3, cliente.getEmail());
        pstmt.setString(4, cliente.getCadastro());
        pstmt.setObject(5, cliente.getTipoCliente(), Types.OTHER);
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int codCliente = rs.getInt(1);
                cliente.setCodCliente(codCliente);
            }
        }
        pstmt.close();
    }

    @Override
    public void atualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET telefone=?, nome=?, email=?, cadastro=?, tipocliente=? WHERE codCliente=?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, cliente.getTelefone());
        pstmt.setString(2, cliente.getNome());
        pstmt.setString(3, cliente.getEmail());
        pstmt.setString(4, cliente.getCadastro());
        pstmt.setObject(5, cliente.getTipoCliente(), Types.OTHER);
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
            String cadastro = rs.getString("cadastro");
            TipoCliente tipoCliente;
            if (rs.getString("tipocliente").equals("F")) {
                tipoCliente = TipoCliente.F;
            } else {
                tipoCliente = TipoCliente.J;
            }
            cliente = new Cliente(codCliente, telefone, nome, email, cadastro, tipoCliente);
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
            String cadastro = rs.getString("cadastro");
            TipoCliente tipoCliente;
            if (rs.getString("tipocliente").equals("F")) {
                tipoCliente = TipoCliente.F;
            } else {
                tipoCliente = TipoCliente.J;
            }
            Cliente cliente = new Cliente(codCliente, telefone, nome, email, cadastro, tipoCliente);
            lista.add(cliente);
        }
        rs.close();
        pstmt.close();
        return lista;
    }


}
