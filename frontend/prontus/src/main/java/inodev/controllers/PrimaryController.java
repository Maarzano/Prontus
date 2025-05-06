package inodev.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class PrimaryController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private Pane paneStroke;

    @FXML
    private AnchorPane login;

    @FXML
    private Button button;

    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if ("admin".equals(user) && "1234".equals(pass)) {
            System.out.println("Login bem-sucedido!");
        } else {
            errorLabel.setText("Usuário ou senha inválidos");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void handleVoltar() throws IOException {
        inodev.App.setRoot("AdmSupremo/TelaPrincipalAdmSupremo");
    }
}
