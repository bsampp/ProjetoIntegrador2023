package Controller;

import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.VeiculoDAO;
import Model.Cliente;
import Model.Veiculo;
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

public class TelaVeiculosController {

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Veiculo> tabelaVeiculos;


    @FXML
    private TableColumn<?, ?> tcAno;

    @FXML
    private TableColumn<?, ?> tcCor;

    @FXML
    private TableColumn<?, ?> tcModelo;

    @FXML
    private TableColumn<?, ?> tcPlaca;

    @FXML
    public void initialize() throws SQLException {
        Connection conexao = Conexao.getConnection();
        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tcCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        tcAno.setCellValueFactory(new PropertyValueFactory<>("anoFabricacao"));

        List<Veiculo> veiculos;
        try {
            VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);
            veiculos = veiculoDAO.buscarTodosVeiculos();
        } catch (SQLException e) {
            e.printStackTrace();
            veiculos = new ArrayList<>(); // lista vazia em caso de exceção
        }

        ObservableList<Veiculo> observableList = FXCollections.observableArrayList();
        observableList.addAll(veiculos); // add the veiculos to the observable list
        tabelaVeiculos.setItems(observableList);
    }

    @FXML
    void btnAtualizarAction(ActionEvent event) {
        // Obtém o cliente selecionado na tabela TableView
        Veiculo veiculoSelecionado = tabelaVeiculos.getSelectionModel().getSelectedItem();
        if (veiculoSelecionado == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/cadastroVeiculo.fxml"));
            Parent root = loader.load();

            CadastroVeiculoController controller = loader.getController();
            controller.setVeiculo(veiculoSelecionado);

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
        Veiculo veiculoSelecionado = tabelaVeiculos.getSelectionModel().getSelectedItem();

        if (veiculoSelecionado != null) {
            // Se o cliente selecionado não for nulo, chama o método deletarCliente do ClienteDAO
            Connection conexao = Conexao.getConnection();
            VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);

            try {
                veiculoDAO.removerVeiculo(veiculoSelecionado.getPlaca());
                tabelaVeiculos.getItems().remove(veiculoSelecionado); // Remove o cliente da tabela TableView
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void btnRegistrarAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/cadastroVeiculo.fxml"));
        Parent Root = Loader.load();
        Scene Scene = new Scene(Root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);
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
