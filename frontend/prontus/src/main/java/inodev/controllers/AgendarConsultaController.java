package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
    private void initialize() {
        carregarMedicos();
        carregarPacientes();
    }

    private void carregarMedicos() {
        try {
            URL doctorsUrl = new URL("http://localhost:8080/api/doctors");
            HttpURLConnection doctorsConn = (HttpURLConnection) doctorsUrl.openConnection();
            doctorsConn.setRequestMethod("GET");

            if (doctorsConn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(doctorsConn.getInputStream()));
                String doctorsResponse = reader.readLine();
                reader.close();

                JsonArray doctors = JsonParser.parseString(doctorsResponse).getAsJsonArray();
                for (JsonElement doctorElement : doctors) {
                    JsonObject doctor = doctorElement.getAsJsonObject();
                    int doctorId = doctor.get("id").getAsInt();
                    int userId = doctor.get("userId").getAsInt();

                    URL userUrl = new URL("http://localhost:8080/api/users/" + userId);
                    HttpURLConnection userConn = (HttpURLConnection) userUrl.openConnection();
                    userConn.setRequestMethod("GET");

                    if (userConn.getResponseCode() == 200) {
                        BufferedReader userReader = new BufferedReader(new InputStreamReader(userConn.getInputStream()));
                        String userResponse = userReader.readLine();
                        userReader.close();

                        JsonObject user = JsonParser.parseString(userResponse).getAsJsonObject();
                        String role = user.get("role").getAsString();

                        if ("DOCTOR".equals(role)) {
                            String doctorName = user.get("name").getAsString();
                            doctorComboBox.getItems().add(doctorId + " - " + doctorName);
                        }
                    } else {
                        System.out.println("Erro ao buscar usuário com ID: " + userId);
                    }
                }
            } else {
                System.out.println("Erro ao buscar médicos: " + doctorsConn.getResponseCode());
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = reader.readLine();
                reader.close();

                JsonArray patients = JsonParser.parseString(response).getAsJsonArray();
                for (JsonElement element : patients) {
                    JsonObject patient = element.getAsJsonObject();
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

            if (!isHorarioDisponivel(doctorId, dateTime)) {
                showAlert("Erro", "Já existe uma consulta marcada nesse horário ou em um intervalo de 30 minutos.");
                return;
            }

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

    private boolean isHorarioDisponivel(int doctorId, LocalDateTime dateTime) {
        try {
            URL url = new URL("http://localhost:8080/api/schedulings?doctorId=" + doctorId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = reader.readLine();
                reader.close();

                JsonArray schedulings = JsonParser.parseString(response).getAsJsonArray();
                for (JsonElement element : schedulings) {
                    JsonObject scheduling = element.getAsJsonObject();
                    LocalDateTime existingDateTime = LocalDateTime.parse(scheduling.get("dateTime").getAsString());

                    if (Math.abs(existingDateTime.until(dateTime, java.time.temporal.ChronoUnit.MINUTES)) < 30) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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
