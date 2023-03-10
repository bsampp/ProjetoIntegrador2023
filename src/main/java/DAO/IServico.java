package DAO;

import java.sql.SQLException;
import java.util.List;
import Model.Servico;

public interface IServico {
    // CREATE
    void cadastrarServico(Servico servico) throws SQLException;

    // READ
    Servico buscarServico(int codServico) throws SQLException;
    List<Servico> buscarTodosServicos() throws SQLException;

    // UPDATE
    void atualizarServico(Servico servico) throws SQLException;

    // DELETE
    void removerServico(int codServico) throws SQLException;
}
