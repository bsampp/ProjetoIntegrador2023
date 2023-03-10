package DAO;

import java.sql.SQLException;
import java.util.List;
import Model.OrdemServico;

public interface IOrdemServico {
    // CREATE
    void cadastrarOS(OrdemServico ordemServico) throws SQLException;

    // READ
    OrdemServico buscarOS(int codOrdemServico) throws SQLException;
    List<OrdemServico> buscarTodasOS() throws SQLException;

    // UPDATE
    void atualizarOS(OrdemServico ordemServico) throws SQLException;

    // DELETE
    void removerOS(int codOrdemServico) throws SQLException;
}
