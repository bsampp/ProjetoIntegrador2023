package DAO;

import Model.OrdemServico;
import Model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoDAO implements IOrdemServico {

    private Connection conexao;

    public OrdemServicoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void cadastrarOS(OrdemServico ordemServico) throws SQLException {

    }

    @Override
    public OrdemServico buscarOS(int codOrdemServico) throws SQLException {
        return null;
    }

    @Override
    public List<OrdemServico> buscarTodasOS() throws SQLException {
        String sql = "SELECT * FROM ordemServico";
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<OrdemServico> lista = new ArrayList<>();
        while (rs.next()) {
            int codOrdemServico = rs.getInt("codOrdemServico");
            float orcamentoFinal = rs.getFloat("orcamentoFinal");
            LocalDate previsaoEntrega = rs.getDate("previsaoEntrega").toLocalDate();
            LocalDate dataSolicitacao = rs.getDate("dataSolicitacao").toLocalDate();
            int codCliente = rs.getInt("codCliente");
            String placa = rs.getString("placa");
            String status = rs.getString("status");
            OrdemServico ordemServico = new OrdemServico(codOrdemServico, orcamentoFinal, previsaoEntrega, dataSolicitacao, codCliente, placa, status);
            lista.add(ordemServico);
        }
        rs.close();
        pstmt.close();
        return lista;
    }

    @Override
    public void atualizarOS(OrdemServico ordemServico) throws SQLException {

    }

    @Override
    public void removerOS(int codOrdemServico) throws SQLException {

    }
}
