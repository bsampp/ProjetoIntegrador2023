package App;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.security.Principal;

import static javafx.fxml.FXMLLoader.load;

public class App extends Application {



    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader principal = new FXMLLoader(getClass().getResource("/application/telaPrincipal.fxml"));
        principal.setRoot(new AnchorPane());
        Parent root = principal.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
