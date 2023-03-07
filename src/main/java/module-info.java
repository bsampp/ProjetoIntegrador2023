module ProjetoIntegrador2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires javafx.base;

    opens App to javafx.fxml;
    opens Controller to javafx.fxml;
    opens DAO to javafx.fxml;
    opens Model to javafx.base;

    exports Model;
    exports App;
    exports Controller;
    exports DAO;

}