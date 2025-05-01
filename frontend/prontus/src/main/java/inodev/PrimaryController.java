package inodev;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.*;
public class PrimaryController {

        @FXML private TextField usernameField;
        @FXML private PasswordField passwordField;
        @FXML private Label errorLabel;

        @FXML
        private void handleLogin() {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            if ("admin".equals(user) && "1234".equals(pass)) {
                System.out.println("Login bem-sucedido!");
                // Redirecionar para a próxima tela
            } else {
                errorLabel.setText("Usuário ou senha inválidos");
                errorLabel.setVisible(true);
            }
    }
}
