module gcm.gcm1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires javafx.graphics;

    opens gcm.gcm1 to javafx.fxml;
    exports gcm.gcm1;
}
