package Controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import DAO.ClienteDAO;
import DAO.Conexao;
import Model.Cliente;
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

public class TelaClientesController {

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Cliente> tabelaClientes;

    @FXML
    private TableColumn<?, ?> tcCPFCNPJ;

    @FXML
    private TableColumn<?, ?> tcCodCliente;

    @FXML
    private TableColumn<?, ?> tcEmail;

    @FXML
    private TableColumn<?, ?> tcNomeCliente;

    @FXML
    private TableColumn<?, ?> tcTelefoneCliente;

    @FXML
    private TableColumn<?, ?> tcTipoCliente;

    @FXML
    public void initialize() throws SQLException {
        Connection conexao = Conexao.getConnection();
        // Configurar as colunas da tabela TableView
        tcCodCliente.setCellValueFactory(new PropertyValueFactory<>("codCliente"));
        tcTelefoneCliente.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcCPFCNPJ.setCellValueFactory(new PropertyValueFactory<>("cadastro"));
        tcTipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));

        // Criar uma instância do ClienteDAO
        ClienteDAO clienteDAO = new ClienteDAO(conexao);

        // Obter a lista de clientes do banco de dados
        List<Cliente> clientes;
        try {
            clientes = clienteDAO.buscarTodosClientes();
        } catch (SQLException e) {
            e.printStackTrace();
            clientes = new ArrayList<>(); // lista vazia em caso de exceção
        }

        // Preencher a tabela TableView com os dados dos clientes
        ObservableList<Cliente> observableList = FXCollections.observableArrayList(clientes);
        tabelaClientes.setItems(observableList);
    }


    @FXML
    void btnAtualizarAction(ActionEvent event) {
        // Obtém o cliente selecionado na tabela TableView
        Cliente clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();
        if (clienteSelecionado == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/cadastroCliente.fxml"));
            Parent root = loader.load();

            cadastroClienteController controller = loader.getController();
            controller.setCliente(clienteSelecionado);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    void btnDeletarAction(ActionEvent event) throws SQLException {
        // Obtém o cliente selecionado na tabela TableView
        Cliente clienteSelecionado = tabelaClientes.getSelectionModel().getSelectedItem();

        if (clienteSelecionado != null) {
            // Se o cliente selecionado não for nulo, chama o método deletarCliente do ClienteDAO
            Connection conexao = Conexao.getConnection();
            ClienteDAO clienteDAO = new ClienteDAO(conexao);

            try {
                clienteDAO.removerCliente(clienteSelecionado.getCodCliente());
                tabelaClientes.getItems().remove(clienteSelecionado); // Remove o cliente da tabela TableView
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void btnRegistrarAction(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/cadastroCliente.fxml"));
            Parent Root = Loader.load();
            Scene Scene = new Scene(Root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(Scene);
        } catch (IOException e) {
            // Tratamento da exceção
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
