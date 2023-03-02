package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class cadastroClienteController {

    @FXML
    private Button btnVoltar;
    @FXML
    private Button btnConfirma;


    @FXML
    private RadioButton rdbClienteFisico;

    @FXML
    private RadioButton rdbClienteJuridico;

    @FXML
    private TextField tfdCNPJ;

    @FXML
    private TextField tfdCPF;

    @FXML
    private TextField tfdEmail;

    @FXML
    private TextField tfdNome;

    @FXML
    private TextField tfdTelefone;

    @FXML
    private ToggleGroup tipoCliente;

    @FXML
    public void initialize() {
        // Define o mesmo ToggleGroup para os dois RadioButtons
        rdbClienteFisico.setToggleGroup(tipoCliente);
        rdbClienteJuridico.setToggleGroup(tipoCliente);
        tfdCPF.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().matches("\\d{0,11}")) {
                return c;
            } else {
                return null;
            }
        }));

        tipoCliente.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == rdbClienteFisico) {
                tfdCPF.setDisable(false);
                tfdCNPJ.setDisable(true);
                rdbClienteJuridico.setSelected(false);
            } else if (newToggle == rdbClienteJuridico) {
                tfdCPF.setDisable(true);
                tfdCNPJ.setDisable(false);
                rdbClienteFisico.setSelected(false);
            }
        });
    }


    @FXML
    void btnVoltarAction(ActionEvent event) throws IOException {

        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/telaClientes.fxml"));
        Parent Root = Loader.load();
        Scene Scene = new Scene(Root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);

    }

}
