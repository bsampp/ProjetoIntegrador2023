package Controller;

import Model.Cliente;

import DAO.ClienteDAO;
import DAO.Conexao;
import Model.TipoCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class CadastroClienteController {

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

    private Cliente clienteEditando;

    public void setCliente(Cliente cliente) throws SQLException {
        Connection conexao = Conexao.getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(conexao);

        // Busca o cliente pelo clientecod
        Cliente clienteExistente = clienteDAO.buscarCliente(cliente.getCodCliente());

        tfdNome.setText(clienteExistente.getNome());
        tfdEmail.setText(clienteExistente.getEmail());
        tfdTelefone.setText(clienteExistente.getTelefone());

        if (clienteExistente.getTipoCliente() == TipoCliente.F) {
            rdbClienteFisico.setSelected(true);
            tfdCPF.setText(clienteExistente.getCadastro());
        } else {
            rdbClienteJuridico.setSelected(true);
            tfdCNPJ.setText(clienteExistente.getCadastro());
        }
        if (cliente != null) {
            // Se o cliente não for nulo, estamos no modo de edição
            clienteEditando = cliente; // Armazena o cliente que está sendo editado na variável clienteEditando
            btnConfirma.setText("Editar"); // Altera o texto do botão de confirmação para "Editar"
        } else {
            // Se o cliente for nulo, estamos no modo de criação
            clienteEditando = null; // Define a variável clienteEditando como nula
            btnConfirma.setText("Cadastrar"); // Altera o texto do botão de confirmação para "Cadastrar"
        }
    }


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
    void btnConfirmaAction(ActionEvent event) throws SQLException {
        Connection conexao = Conexao.getConnection();
        // Obter valores dos campos de entrada do usuário
        String nome = tfdNome.getText();
        String telefone = tfdTelefone.getText();
        String email = tfdEmail.getText();
        String cadastro = rdbClienteFisico.isSelected() ? tfdCPF.getText() : tfdCNPJ.getText();
        TipoCliente tipoCliente = rdbClienteFisico.isSelected() ? TipoCliente.F : TipoCliente.J;

        // Verificar se o cliente já existe no banco de dados
        ClienteDAO clienteDAO = new ClienteDAO(conexao);

        // Criar objeto Cliente com os valores obtidos
        if (clienteEditando == null) {
            Cliente novoCliente = new Cliente(0, telefone, nome, email, cadastro, tipoCliente);
            // Chamar o método cadastrarCliente() da classe ClienteDAO
            try {
                clienteDAO.cadastrarCliente(novoCliente);
                // Mostra mensagem de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cadastro de Cliente");
                alert.setHeaderText(null);
                alert.setContentText("Cliente cadastrado com sucesso!");
                alert.showAndWait();
            } catch (SQLException e) {
                // Mostra mensagem de erro
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao cadastrar cliente");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível cadastrar o cliente. Erro: " + e.getMessage());
                alert.showAndWait();
            }

        }else{
            clienteEditando.setTelefone(telefone);
            clienteEditando.setNome(nome);
            clienteEditando.setEmail(email);
            clienteEditando.setCadastro(cadastro);
            clienteEditando.setTipoCliente(tipoCliente);
            try{
                clienteDAO.atualizarCliente(clienteEditando);
                // Mostra mensagem de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edição de Cliente");
                alert.setHeaderText(null);
                alert.setContentText("Cliente editado com sucesso!");
                alert.showAndWait();
            }catch (SQLException e) {
                // Mostra mensagem de erro
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao editar cliente");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível editar o cliente. Erro: " + e.getMessage());
                alert.showAndWait();
            }
        }
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
