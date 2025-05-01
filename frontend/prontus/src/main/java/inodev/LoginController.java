package inodev;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorMessage;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorMessage.setText("Por favor, preencha todos os campos.");
        } else if (username.equals("admin") && password.equals("1234")) {
            errorMessage.setText("Login bem-sucedido!");
            errorMessage.setStyle("-fx-text-fill: green;");
        } else {
            errorMessage.setText("Usuário ou senha inválidos.");
        }
    }
}