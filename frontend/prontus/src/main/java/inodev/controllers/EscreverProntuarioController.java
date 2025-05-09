package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class EscreverProntuarioController {

    @FXML
    private ComboBox<Diagnostics> diagnosticField;

    @FXML
    private TextArea recepieArea;

    @FXML
    private TextArea anotationArea;


    private int schedulingId;

    @FXML
    private void initialize() {
        diagnosticField.getItems().addAll(Diagnostics.values());
    }

    public void setSchedulingId(int schedulingId) {
        this.schedulingId = schedulingId;
    }

    @FXML
    private void handleSalvar() {
        Diagnostics diagnostic = diagnosticField.getValue();
        String recepie = recepieArea.getText();
        String anotation = anotationArea.getText();

        try {

            URL medicalRecordUrl = new URL("http://localhost:8080/api/medical-records");
            HttpURLConnection medicalRecordConn = (HttpURLConnection) medicalRecordUrl.openConnection();
            medicalRecordConn.setRequestMethod("POST");
            medicalRecordConn.setRequestProperty("Content-Type", "application/json");
            medicalRecordConn.setDoOutput(true);

            String medicalRecordJson = String.format(
                "{\"schedulingId\": %d, \"diagnostic\": \"%s\", \"recepie\": \"%s\", \"anotation\": \"%s\"}",
                schedulingId, diagnostic.name(), recepie, anotation
            );

            try (OutputStreamWriter writer = new OutputStreamWriter(medicalRecordConn.getOutputStream())) {
                writer.write(medicalRecordJson);
            }

            int responseCode = medicalRecordConn.getResponseCode();
            if (responseCode == 201) {
                showAlert("Sucesso", "Prontuário salvo com sucesso!");
                App.setRoot("Médico/ListaConsultasMedico");
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(medicalRecordConn.getErrorStream()));
                StringBuilder errorMessage = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    errorMessage.append(line);
                }
                reader.close();
                throw new Exception("Erro ao criar prontuário: " + errorMessage.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Ocorreu um erro ao salvar o prontuário.");
        }
    }

    private boolean medicalRecordExists(int schedulingId) throws Exception {
        URL url = new URL("http://localhost:8080/api/medical-records?schedulingId=" + schedulingId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            reader.close();
            return !response.isEmpty();
        } else if (conn.getResponseCode() == 404) {
            return false;
        } else {
            throw new Exception("Erro ao verificar prontuário existente.");
        }
    }

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("Médico/ListaConsultasMedico");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar para a lista de consultas.");
        }
    }

    @FXML
    private void handleLogout() {
        try {
            App.setRoot("login");
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

    public enum Diagnostics {
        NADA,
        NAO_IDENTIFICADO,
        GRIPE,
        RESFRIADO,
        DIABETES,
        HIPERTENSAO,
        ASMA,
        COVID_19,
        PNEUMONIA,
        BRONQUITE,
        ALERGIA,
        INFARTO,
        AVC,
        ANEMIA,
        MIGRANE,
        SINUSITE,
        DEPRESSAO,
        ANSIEDADE,
        CANCER,
        GASTRITE,
        DENGUE,
        ZIKA,
        CHIKUNGUNYA,
        HEPATITE,
        ARTRITE;
    }
}




