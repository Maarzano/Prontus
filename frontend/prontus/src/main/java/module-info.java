module inodev {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.base;
    requires transitive javafx.graphics;

    opens inodev to javafx.fxml;
    exports inodev;
    opens inodev.controllers to javafx.fxml;
    exports inodev.controllers;
}
