package DAO;

import Model.Cliente;
import Model.TipoCliente;
import Model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements IVeiculo {

    private Connection conexao;

    public VeiculoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void cadastrarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculo (placa, modelo, cor, anoFabricacao, codCliente) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setString(3, veiculo.getCor());
            pstmt.setInt(4, veiculo.getAnoFabricacao());
            pstmt.setInt(5, veiculo.getCodCliente());

            pstmt.executeUpdate();

    }

    @Override
    public Veiculo buscarVeiculo(String placa) throws SQLException {
        String sql = "SELECT * FROM veiculo WHERE placa=?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, placa);
        ResultSet rs = pstmt.executeQuery();
        Veiculo veiculo = null;
        if (rs.next()) {
            String modelo = rs.getString("modelo");
            String cor = rs.getString("cor");
            int anoFabricacao = rs.getInt("anoFabricacao");
            int codCliente = rs.getInt("codCliente");
            veiculo = new Veiculo(placa, modelo, cor, anoFabricacao, codCliente);
        }
        rs.close();
        pstmt.close();
        return veiculo;
    }

    @Override
    public List<Veiculo> buscarTodosVeiculos() throws SQLException {
        String sql = "SELECT * FROM veiculo";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<Veiculo> lista = new ArrayList<>();
        while (rs.next()) {
            String placa = rs.getString("placa");
            String modelo = rs.getString("modelo");
            int anoFabricacao = rs.getInt("anoFabricacao");
            String cor = rs.getString("cor");
            int codCliente = rs.getInt("codCliente");
            Veiculo veiculo = new Veiculo(placa, modelo, cor, anoFabricacao, codCliente);
            lista.add(veiculo);
        }
        rs.close();
        pstmt.close();
        return lista;
    }

    @Override
    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculo SET modelo=?, cor=?, anoFabricacao=?, codCliente=? WHERE placa=?";

        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, veiculo.getModelo());
        pstmt.setString(2, veiculo.getCor());
        pstmt.setInt(3, veiculo.getAnoFabricacao());
        pstmt.setInt(4, veiculo.getCodCliente());
        pstmt.setString(5, veiculo.getPlaca());
        pstmt.execute();
        pstmt.close();;
    }


    @Override
    public void removerVeiculo(String placa) throws SQLException {
        String sql = "DELETE FROM veiculo WHERE placa = ?";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, placa);
        pstmt.execute();
        pstmt.close();
    }

}
