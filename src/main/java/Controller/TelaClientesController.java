package Controller;

import java.io.IOException;
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
    void btnAtualizarAction(ActionEvent event) {

    }

    @FXML
    void btnDeletarAction(ActionEvent event) {

    }

    @FXML
    void btnRegistrarAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/cadastroCliente.fxml"));
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
