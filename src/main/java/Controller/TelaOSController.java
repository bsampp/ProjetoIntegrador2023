package Controller;

import DAO.ClienteDAO;
import DAO.Conexao;
import DAO.OrdemServicoDAO;
import Model.Cliente;
import Model.OrdemServico;
import javafx.beans.property.SimpleStringProperty;
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

public class TelaOSController {

    @FXML
    private TableView<OrdemServico> tabelaOS;

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<OrdemServico, String> tcCliente;

    @FXML
    private TableColumn<?, ?> tcCod;

    @FXML
    private TableColumn<?, ?> tcEntrega;

    @FXML
    private TableColumn<?, ?> tcOrcamento;

    @FXML
    private TableColumn<?, ?> tcPlaca;

    @FXML
    private TableColumn<?, ?> tcSolicitacao;

    @FXML
    private TableColumn<?, ?> tcStatus;

    @FXML
    public void initialize() throws SQLException {
        Connection conexao = Conexao.getConnection();
        // Configurar as colunas da tabela TableView
        tcCod.setCellValueFactory(new PropertyValueFactory<>("codOrdemServico"));
        tcPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        tcCliente.setCellValueFactory(cellData -> {
            OrdemServico ordemServico = cellData.getValue();
            ClienteDAO clienteDAO = new ClienteDAO(conexao);
            try {
                Cliente cliente = clienteDAO.buscarCliente(ordemServico.getCodCliente());
                return new SimpleStringProperty(cliente.getNome());
            } catch (SQLException e) {
                e.printStackTrace();
                return new SimpleStringProperty("");
            }
        });
        tcSolicitacao.setCellValueFactory(new PropertyValueFactory<>("dataSolicitacao"));
        tcEntrega.setCellValueFactory(new PropertyValueFactory<>("previsaoEntrega"));
        tcOrcamento.setCellValueFactory(new PropertyValueFactory<>("orcamentoFinal"));
        tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        OrdemServicoDAO ordemServicoDAO = new OrdemServicoDAO(conexao);

        // Obter a lista de clientes do banco de dados
        List<OrdemServico> ordemServicos;
        try {
            ordemServicos = ordemServicoDAO.buscarTodasOS();
        } catch (SQLException e) {
            e.printStackTrace();
            ordemServicos = new ArrayList<>(); // lista vazia em caso de exceção
        }

        // Preencher a tabela TableView com os dados dos clientes
        ObservableList<OrdemServico> observableList = FXCollections.observableArrayList(ordemServicos);
        tabelaOS.setItems(observableList);
    }

    @FXML
    void btnRegistrarAction(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/View/cadastroOS.fxml"));
            Parent Root = Loader.load();
            Scene Scene = new Scene(Root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(Scene);
        } catch (IOException e) {
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
