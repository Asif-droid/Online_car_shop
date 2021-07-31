module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;
    opens sample to javafx.graphics, javafx.fxml;
}