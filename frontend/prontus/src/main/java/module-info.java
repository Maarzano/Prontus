module inodev {
    requires javafx.controls;
    requires javafx.fxml;

    opens inodev to javafx.fxml;
    exports inodev;
}
