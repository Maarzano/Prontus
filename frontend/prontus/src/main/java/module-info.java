module inodev {
    requires javafx.controls;
    requires javafx.fxml;

    opens inodev to javafx.fxml;
    exports inodev;
    opens inodev.controllers to javafx.fxml;
    exports inodev.controllers;
}
