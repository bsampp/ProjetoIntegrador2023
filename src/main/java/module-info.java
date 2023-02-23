module com.example.motormanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.motormanager to javafx.fxml;
    exports application;

}