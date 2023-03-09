package Controller;

import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.ServicoDAO;
import DAO.VeiculoDAO;
import Model.Cliente;
import Model.Servico;
import Model.TipoCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CadastroServicosController {

    @FXML
    private Button btnConfirma;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField tfdNome;

    @FXML
    private TextField tfdValor;

    @FXML
    void btnConfirmaAction(ActionEvent event) throws SQLException {
        Connection conexao = Conexao.getConnection();
        String nome = tfdNome.getText();
        float valor = Float.parseFloat(tfdValor.getText());
        ServicoDAO servicoDAO = new ServicoDAO(conexao);

        Servico novoServico = new Servico(0, nome, valor);
        try {
            servicoDAO.cadastrarServico(novoServico);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cadastro de Serviço");
            alert.setHeaderText(null);
            alert.setContentText("Serviço cadastrado com sucesso!");
            alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/telaServicos.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao cadastrar serviço");
            alert.setHeaderText(null);
            alert.setContentText("Não foi possível cadastrar o serviço. Erro: " + e.getMessage());
            alert.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnVoltarAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/telaServicos.fxml"));
        Parent Root = Loader.load();
        Scene Scene = new Scene(Root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);

    }

}
