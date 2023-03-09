package Controller;

import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.VeiculoDAO;
import Model.Cliente;
import Model.Veiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CadastroVeiculoController {


    @FXML
    private Button btnConfirma;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<Cliente> cboxCliente;

    @FXML
    private TextField tfdAno;

    @FXML
    private TextField tfdCor;

    @FXML
    private TextField tfdModelo;

    @FXML
    private TextField tfdPlaca;

    private Veiculo veiculoEditando;

    public void setVeiculo(Veiculo veiculo) throws SQLException {
        Connection conexao = Conexao.getConnection();
        VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);

        // Busca o cliente pelo clientecod
        Veiculo veiculoSelecionado = veiculoDAO.buscarVeiculo(veiculo.getPlaca());

        tfdAno.setText(Integer.toString(veiculoSelecionado.getAnoFabricacao()));
        tfdModelo.setText(veiculoSelecionado.getModelo());
        tfdCor.setText(veiculoSelecionado.getCor());
        tfdPlaca.setText(veiculoSelecionado.getPlaca());
        for (Cliente cliente : cboxCliente.getItems()) {
            if (cliente.getCodCliente() == veiculoSelecionado.getCodCliente()) {
                cboxCliente.getSelectionModel().select(cliente);
                break;
            }
        }
        if (veiculo != null) {
            // Se o cliente não for nulo, estamos no modo de edição
            veiculoEditando = veiculo; // Armazena o cliente que está sendo editado na variável clienteEditando
            btnConfirma.setText("Editar"); // Altera o texto do botão de confirmação para "Editar"
        } else {
            // Se o cliente for nulo, estamos no modo de criação
            veiculoEditando = null; // Define a variável clienteEditando como nula
            btnConfirma.setText("Cadastrar"); // Altera o texto do botão de confirmação para "Cadastrar"
        }
    }

    @FXML
    public void initialize() throws SQLException {
        Connection conexao = Conexao.getConnection();
        try {
            // criar uma instância da classe responsável pelo acesso aos dados
            ClienteDAO clienteDAO = new ClienteDAO(conexao);

            // chamar o método para obter a lista de clientes
            List<Cliente> listaClientes = clienteDAO.buscarTodosClientes();

            // preencher a combobox com a lista de clientes
            cboxCliente.getItems().addAll(listaClientes);

            // configurar o conversor de string para exibir o nome do cliente

        } catch (SQLException e) {
            // em caso de erro, exibir uma mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Ocorreu um erro ao obter a lista de clientes.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }



        @FXML
    void btnConfirmaAction(ActionEvent event) throws SQLException {
        Connection conexao = Conexao.getConnection();
        VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);

        String placa = tfdPlaca.getText();
        String modelo = tfdModelo.getText();
        String cor = tfdCor.getText();
        int anoFabricacao = Integer.parseInt(tfdAno.getText());
        int codCliente = -1;
        Cliente clienteSelecionado = cboxCliente.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            codCliente = clienteSelecionado.getCodCliente();
        } else {
            // exibir uma mensagem de erro se nenhum cliente foi selecionado
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um cliente.");
            alert.showAndWait();
            return; // retorna sem cadastrar o veículo
        }

        if (veiculoEditando == null) {
            if (codCliente != -1) {
                Veiculo veiculo = new Veiculo(placa, modelo, cor, anoFabricacao, codCliente);

                try {
                    veiculoDAO.cadastrarVeiculo(veiculo);

                    // exibir uma mensagem de sucesso
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cadastro de veículo");
                    alert.setHeaderText(null);
                    alert.setContentText("Veículo cadastrado com sucesso.");
                    alert.showAndWait();

                    // voltar para a tela de veículos
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/telaVeiculos.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                } catch (IOException | SQLException e) {
                    // em caso de erro, exibir uma mensagem de erro
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Ocorreu um erro ao cadastrar o veículo.");
                    alert.showAndWait();
                    e.printStackTrace();
                }
            } else {
                // exibir uma mensagem de erro se nenhum cliente foi selecionado
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, selecione um cliente.");
                alert.showAndWait();
            }
        }else{
            veiculoEditando.setPlaca(placa);
            veiculoEditando.setModelo(modelo);
            veiculoEditando.setCor(cor);
            veiculoEditando.setAnoFabricacao(anoFabricacao);
            try{
                veiculoDAO.atualizarVeiculo(veiculoEditando);
                // Mostra mensagem de sucesso
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Edição de Veiculo");
                alert.setHeaderText(null);
                alert.setContentText("Veiculo editado com sucesso!");
                alert.showAndWait();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/telaVeiculos.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            }catch (SQLException e) {
                // Mostra mensagem de erro
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao editar cliente");
                alert.setHeaderText(null);
                alert.setContentText("Não foi possível editar o veiculo. Erro: " + e.getMessage());
                alert.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @FXML
    void btnVoltarAction(ActionEvent event) throws IOException {

        FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/telaVeiculos.fxml"));
        Parent Root = Loader.load();
        Scene Scene = new Scene(Root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(Scene);

    }

}
