package Controller;

import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.ServicoDAO;
import Model.Cliente;
import Model.Servico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelaServicosController {

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Servico> tabelaServicos;

    @FXML
    private TableColumn<?, ?> tcCod;

    @FXML
    private TableColumn<?, ?> tcNome;

    @FXML
    private TableColumn<?, ?> tcValor;

    @FXML
    public void initialize() throws SQLException {
        Connection conexao = Conexao.getConnection();
        tcCod.setCellValueFactory(new PropertyValueFactory<>("codServico"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        List<Servico> servicos;
        try {
            ServicoDAO servicoDAO = new ServicoDAO(conexao);
            servicos = servicoDAO.buscarTodosServicos();
        } catch (SQLException e) {
            e.printStackTrace();
            servicos = new ArrayList<>(); // lista vazia em caso de exceção
        }

        ObservableList<Servico> observableList = FXCollections.observableArrayList();
        observableList.addAll(servicos); // add the veiculos to the observable list
        tabelaServicos.setItems(observableList);
    }

    @FXML
    void btnAtualizarAction(ActionEvent event) {

    }

    @FXML
    void btnDeletarActin(ActionEvent event) throws SQLException {
        // Obtém o cliente selecionado na tabela TableView
        Servico servicoSelecionado = tabelaServicos.getSelectionModel().getSelectedItem();

        if (servicoSelecionado != null) {
            // Se o cliente selecionado não for nulo, chama o método deletarCliente do ClienteDAO
            Connection conexao = Conexao.getConnection();
            ClienteDAO clienteDAO = new ClienteDAO(conexao);

            try {
                clienteDAO.removerCliente(servicoSelecionado.getCodServico());
                tabelaServicos.getItems().remove(servicoSelecionado); // Remove o cliente da tabela TableView
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnRegistrarAction(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/cadastroServicos.fxml"));
            Parent Root = Loader.load();
            Scene Scene = new Scene(Root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(Scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnVoltarAction(ActionEvent event) throws IOException {
        FXMLLoader principal = new FXMLLoader(getClass().getResource("/View/telaPrincipal.fxml"));
        principal.setRoot(new AnchorPane());
        Parent root = principal.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);

    }

}
