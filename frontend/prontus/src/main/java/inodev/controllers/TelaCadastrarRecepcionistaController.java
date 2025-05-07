package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TelaCadastrarRecepcionistaController {

    @FXML
    private TextField nomeField, cpfField, emailField, celularField;
    @FXML
    private PasswordField senhaField;

    @FXML
    private void handleCancelar() {
        try {
            App.setRoot("AdmNormal/TelaPrincipalAdmNormal");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar para a tela inicial.");
        }
    }

    @FXML
    private void handleSalvar() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String celular = celularField.getText();
        String senha = senhaField.getText();

        try {
            URL url = new URL("http://localhost:8080/api/users");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = String.format(
                "{\"name\": \"%s\", \"cpf\": \"%s\", \"email\": \"%s\", \"cellphone\": \"%s\", \"password\": \"%s\", \"role\": \"RECEPCIONIST\"}",
                nome, cpf, email, celular, senha
            );

            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
                writer.write(jsonInputString);
            }

            if (conn.getResponseCode() == 201) {
                showAlert("Sucesso", "Recepcionista cadastrado com sucesso!");
                App.setRoot("AdmNormal/TelaPrincipalAdmNormal");
            } else {
                showAlert("Erro", "Erro ao cadastrar recepcionista. Verifique os dados e tente novamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Ocorreu um erro ao tentar cadastrar o recepcionista.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
