package Controller;

import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.VeiculoDAO;
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
