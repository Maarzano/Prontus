package inodev;

import inodev.controllers.AgendarConsultaController;
import inodev.controllers.TelaPrincipalMedicoController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"), 1920, 1005);
        stage.setScene(scene);
        stage.setTitle("Prontus");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/inodev/imagens/logoProntus.png")));
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/inodev/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }

    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/inodev/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        return root;
    }

    public static void redirectToRoleScreen(String role, int userId) throws IOException {
        switch (role) {
            case "ADM_SUPER":
                setRoot("AdmSupremo/TelaPrincipalAdmSupremo");
                break;
            case "ADM":
                setRoot("AdmNormal/TelaPrincipalAdmNormal");
                break;
            case "RECEPCIONIST":
                setRoot("Recepcionista/TelaPrincipalRecepcionista");
                break;
            case "DOCTOR":
                FXMLLoader loader = new FXMLLoader(App.class.getResource("/inodev/Médico/TelaPrincipalMedico.fxml"));
                Parent root = loader.load();
                TelaPrincipalMedicoController medicoController = loader.getController();
                medicoController.setUserId(userId);
                setRoot(root);
                break;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}