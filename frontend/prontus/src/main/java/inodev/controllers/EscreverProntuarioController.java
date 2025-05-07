package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class EscreverProntuarioController {

    @FXML
    private TextArea receitaField;

    @FXML
    private TextArea anotacoesField;

    @FXML
    private TextField diagnosticoField;

    @FXML
    private Button btnSalvar, btnVoltar;

    private int schedulingId;

    public void setSchedulingId(int schedulingId) {
        this.schedulingId = schedulingId;
    }

    @FXML
    private void handleSalvar() {
        String receita = receitaField.getText();
        String anotacoes = anotacoesField.getText();
        String diagnostico = diagnosticoField.getText();

        try {
            URL url = new URL("http://localhost:8080/api/medicalRecords");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = String.format(
                "{\"schedulingId\": %d, \"recipe\": \"%s\", \"annotations\": \"%s\", \"diagnosis\": \"%s\"}",
                schedulingId, receita, anotacoes, diagnostico
            );

            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
                writer.write(jsonInputString);
            }

            if (conn.getResponseCode() == 201) {
                showAlert("Sucesso", "Prontuário salvo com sucesso!");
                App.setRoot("Médico/ListaConsultasMedico");
            } else {
                showAlert("Erro", "Erro ao salvar prontuário. Verifique os dados e tente novamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Ocorreu um erro ao tentar salvar o prontuário.");
        }
    }

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("Médico/ListaConsultasMedico");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
