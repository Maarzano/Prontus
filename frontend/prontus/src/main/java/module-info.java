module inodev {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires com.google.gson;

    opens inodev to javafx.fxml;
    exports inodev;
    opens inodev.controllers to javafx.fxml;
    exports inodev.controllers;
}
