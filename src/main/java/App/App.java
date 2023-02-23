package App;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class App extends Application {

    private static AnchorPane root = new AnchorPane();

    /**
     * Cria um método acessador static para retornar o objeto static para o controller usar
     */
    public static AnchorPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(root, 1920, 1080);
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("resources/application/telaPrincipal"));
        stage.setTitle("Tela Principal");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
