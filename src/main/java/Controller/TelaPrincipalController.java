package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaPrincipalController {

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnOrdemDeServicos;

    @FXML
    private Button btnServicos;

    @FXML
    private Button btnVeiculos;

    private Stage stage;

    private Scene scene;
    private Parent root;

    public void btnClientesAction (ActionEvent event) throws IOException {
        FXMLLoader clientesLoader = new FXMLLoader(getClass().getResource("/application/telaClientes.fxml"));
        Parent clientesRoot = clientesLoader.load();
        Scene clientesScene = new Scene(clientesRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(clientesScene);

    }

    @FXML
    void btnOSAction(ActionEvent event) throws IOException {
        FXMLLoader osLoader = new FXMLLoader(getClass().getResource("/application/telaOS.fxml"));
        Parent osRoot = osLoader.load();
        Scene osScene = new Scene(osRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(osScene);
    }

    @FXML
    void btnServicosAction(ActionEvent event) throws IOException {
        FXMLLoader servicosLoader = new FXMLLoader(getClass().getResource("/application/telaServicos.fxml"));
        Parent servicosRoot = servicosLoader.load();
        Scene servicosScene = new Scene(servicosRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(servicosScene);
    }

    @FXML
    void btnVeiculosAction(ActionEvent event) throws IOException {
        FXMLLoader veiculosLoader = new FXMLLoader(getClass().getResource("/application/telaVeiculos.fxml"));
        Parent veiculosRoot = veiculosLoader.load();
        Scene veiculosScene = new Scene(veiculosRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(veiculosScene);
    }


}




