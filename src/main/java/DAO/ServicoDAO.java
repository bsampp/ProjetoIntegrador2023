package DAO;

import Model.Cliente;
import Model.Servico;
import Model.TipoCliente;
import Model.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO implements IServico {

    private Connection conexao;

    public ServicoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void cadastrarServico(Servico servico) throws SQLException {
        String sql = "INSERT INTO servico (nome, valor) VALUES (?, ?)";

        PreparedStatement pstmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, servico.getNome());
        pstmt.setFloat(2, servico.getValor());
        int rowsAffected = pstmt.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int codServico = rs.getInt(1);
                servico.setCodServico(codServico);
            }
        }
        pstmt.close();
    }

    @Override
    public Servico buscarServico(int codServico) throws SQLException {
        String sql = "SELECT * FROM servico WHERE codServico=?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, codServico);
        ResultSet rs = pstmt.executeQuery();
        Servico servico = null;
        if (rs.next()) {
            String nome = rs.getString("nome");
            float valor = rs.getFloat("valor");
            servico = new Servico(codServico, nome, valor);
        }
        rs.close();
        pstmt.close();
        return servico;
    }

    @Override
    public List<Servico> buscarTodosServicos() throws SQLException {
        String sql = "SELECT * FROM servico";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Servico> lista = new ArrayList<>();
        while (rs.next()) {
            int codServico = rs.getInt("codServico");
            String nome = rs.getString("nome");
            float valor = rs.getFloat("valor");
            Servico servico = new Servico(codServico, nome, valor);
            lista.add(servico);
        }
        rs.close();
        pstmt.close();
        return lista;
    }

    @Override
    public void atualizarServico(Servico servico) throws SQLException {
        String sql = "UPDATE servico SET nome=?, valor=? WHERE codServico=?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, servico.getNome());
        pstmt.setFloat(2, servico.getValor());
        pstmt.setInt(3, servico.getCodServico());
        pstmt.execute();
        pstmt.close();
    }

    @Override
    public void removerServico(int codServico) throws SQLException {
        String sql = "DELETE FROM servico WHERE codServico=?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, codServico);
        pstmt.execute();
        pstmt.close();

    }
}
