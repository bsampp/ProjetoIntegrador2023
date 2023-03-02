package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaVeiculosController {

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVoltar;


    @FXML
    private TableColumn<?, ?> tcAno;

    @FXML
    private TableColumn<?, ?> tcCor;

    @FXML
    private TableColumn<?, ?> tcModelo;

    @FXML
    private TableColumn<?, ?> tcPlaca;

    @FXML
    void btnRegistrarAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/application/cadastroVeiculo.fxml"));
        Parent Root = Loader.load();
        Scene Scene = new Scene(Root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);
    }

    @FXML
    void btnVoltarAction(ActionEvent event) throws IOException {
        FXMLLoader principal = new FXMLLoader(getClass().getResource("/application/telaPrincipal.fxml"));
        principal.setRoot(new AnchorPane());
        Parent root = principal.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

}
