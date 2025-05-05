package inodev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("loginTeste"), 640, 480);
        scene.getStylesheets().add(App.class.getResource("/inodev/styles/styles.css").toExternalForm()); // Fixed path
        stage.setTitle("Prontus - Login");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/inodev/" + fxml + ".fxml")); // Fixed path
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}