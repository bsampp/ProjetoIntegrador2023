package DAO;

import java.sql.SQLException;
import java.util.List;
import Model.Veiculo;

public interface IVeiculo {

    // CREATE
    void cadastrarVeiculo(Veiculo veiculo) throws SQLException;

    // READ
    Veiculo buscarVeiculo(String placa) throws SQLException;
    List<Veiculo> buscarTodosVeiculos() throws SQLException;

    // UPDATE
    void atualizarVeiculo(Veiculo veiculo) throws SQLException;

    // DELETE
    void removerVeiculo(String placa) throws SQLException;
}
