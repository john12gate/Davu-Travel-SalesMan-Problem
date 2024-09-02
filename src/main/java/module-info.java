module com.davu.davu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.davu.davu to javafx.fxml;
    exports com.davu.davu;
}
