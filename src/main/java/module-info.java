module ProjetoIntegrador2023 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;
    requires javafx.base;

    opens App to javafx.fxml;
    opens Controller to javafx.fxml;

    exports App;
    exports Controller;

}