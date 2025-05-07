package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class AgendarConsultaController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> doctorComboBox;

    @FXML
    private ComboBox<String> patientComboBox;

    @FXML
    private TextField timeField;

    @FXML
    private Button btnAgendar, btnVoltar;

    @FXML
    private VBox vboxContainer;

    @FXML
    private void initialize() {
        carregarMedicos();
        carregarPacientes();
    }

    private void carregarMedicos() {
        try {
            URL url = new URL("http://localhost:8080/api/users?role=DOCTOR");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();

                com.google.gson.JsonArray doctors = com.google.gson.JsonParser.parseString(response).getAsJsonArray();
                for (com.google.gson.JsonElement element : doctors) {
                    com.google.gson.JsonObject doctor = element.getAsJsonObject();
                    doctorComboBox.getItems().add(doctor.get("id").getAsString() + " - " + doctor.get("name").getAsString());
                }
            } else {
                System.out.println("Erro ao buscar médicos: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarPacientes() {
        try {
            URL url = new URL("http://localhost:8080/api/patients");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();

                com.google.gson.JsonArray patients = com.google.gson.JsonParser.parseString(response).getAsJsonArray();
                for (com.google.gson.JsonElement element : patients) {
                    com.google.gson.JsonObject patient = element.getAsJsonObject();
                    patientComboBox.getItems().add(patient.get("id").getAsString() + " - " + patient.get("name").getAsString());
                }
            } else {
                System.out.println("Erro ao buscar pacientes: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAgendar() {
        try {
            String selectedDoctor = doctorComboBox.getValue();
            String selectedPatient = patientComboBox.getValue();
            LocalDate date = datePicker.getValue();
            String time = timeField.getText();

            if (selectedDoctor == null || selectedPatient == null || date == null || time == null || time.isEmpty()) {
                showAlert("Erro", "Todos os campos devem ser preenchidos.");
                return;
            }

            int doctorId = Integer.parseInt(selectedDoctor.split(" - ")[0]);
            int patientId = Integer.parseInt(selectedPatient.split(" - ")[0]);
            LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.parse(time));

            URL url = new URL("http://localhost:8080/api/schedulings");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String schedulingJson = String.format(
                "{\"patientId\": %d, \"doctorId\": %d, \"dateTime\": \"%s\", \"statusScheduling\": \"SCHEDULED\"}",
                patientId, doctorId, dateTime
            );

            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream())) {
                writer.write(schedulingJson);
            }

            if (conn.getResponseCode() == 201) {
                showAlert("Sucesso", "Consulta agendada com sucesso!");
                App.setRoot("Recepcionista/TelaPrincipalRecepcionista");
            } else {
                showAlert("Erro", "Erro ao agendar consulta. Verifique os dados e tente novamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Ocorreu um erro ao tentar agendar a consulta.");
        }
    }

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("Recepcionista/TelaPrincipalRecepcionista");
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
