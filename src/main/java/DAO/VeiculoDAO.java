package DAO;

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

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setString(3, veiculo.getCor());
            pstmt.setInt(4, veiculo.getAnoFabricacao());
            pstmt.setInt(5, veiculo.getCodCliente());

            pstmt.executeUpdate();
        }
    }

    @Override
    public Veiculo buscarVeiculo(String placa) throws SQLException {
        return null;
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

    }

    @Override
    public void removerVeiculo(String placa) throws SQLException {

    }
}
