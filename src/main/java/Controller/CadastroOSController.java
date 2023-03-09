package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroOSController {

    @FXML
    private Button btnVoltar;

    @FXML
    private ChoiceBox<?> cbCliente;

    @FXML
    private ChoiceBox<?> cbVeiculo;

    @FXML
    private DatePicker dpPrevisao;

    @FXML
    private DatePicker dpSolicitacao;

    @FXML
    void btnVoltarAction(ActionEvent event) throws IOException {
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/telaOS.fxml"));
        Parent Root = Loader.load();
        Scene Scene = new Scene(Root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);
    }

}
