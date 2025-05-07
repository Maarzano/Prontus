package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ListaConsultasController {

    @FXML
    private VBox vboxContainer;

    @FXML
    private Button btnVoltar;

    @FXML
    private void initialize() {
        carregarConsultas();
    }

    private void carregarConsultas() {
        try {
            URL url = new URL("http://localhost:8080/api/schedulings");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = reader.readLine();
                reader.close();

                JsonArray schedulings = JsonParser.parseString(response).getAsJsonArray();
                for (JsonElement element : schedulings) {
                    JsonObject scheduling = element.getAsJsonObject();
                    int patientId = scheduling.get("patientId").getAsInt();
                    int doctorId = scheduling.get("doctorId").getAsInt();
                    String dateTime = scheduling.get("dateTime").getAsString();
                    String status = scheduling.get("statusScheduling").getAsString();

                    JsonObject patient = fetchPatient(patientId);
                    JsonObject doctor = fetchDoctor(doctorId);

                    adicionarCardConsulta(
                        patient.get("name").getAsString(),
                        doctor.get("crm").getAsString(),
                        dateTime,
                        status
                    );
                }
            } else {
                System.out.println("Erro ao buscar consultas: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JsonObject fetchPatient(int patientId) throws Exception {
        URL url = new URL("http://localhost:8080/api/patients/" + patientId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            reader.close();
            return JsonParser.parseString(response).getAsJsonObject();
        } else {
            throw new Exception("Erro ao buscar paciente com ID: " + patientId);
        }
    }

    private JsonObject fetchDoctor(int doctorId) throws Exception {
        URL url = new URL("http://localhost:8080/api/doctors/" + doctorId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = reader.readLine();
            reader.close();
            return JsonParser.parseString(response).getAsJsonObject();
        } else {
            throw new Exception("Erro ao buscar médico com ID: " + doctorId);
        }
    }

    private void adicionarCardConsulta(String patientName, String doctorCrm, String dateTime, String status) {
        HBox card = new HBox();
        card.setSpacing(20);
        card.setStyle("-fx-padding: 15; -fx-border-color: #5A39D2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: #F3F3F3;");

        VBox details = new VBox();
        details.setSpacing(5);

        Text patientText = new Text("Paciente: " + patientName);
        patientText.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Text doctorText = new Text("CRM Médico: " + doctorCrm);
        doctorText.setStyle("-fx-font-size: 14;");

        Text dateTimeText = new Text("Data e Hora: " + formatarDataHora(dateTime));
        dateTimeText.setStyle("-fx-font-size: 14;");

        Text statusText = new Text("Status: " + status);
        statusText.setStyle("-fx-font-size: 14;");

        details.getChildren().addAll(patientText, doctorText, dateTimeText, statusText);

        card.getChildren().add(details);
        vboxContainer.getChildren().add(card);
    }

    private String formatarDataHora(String dateTime) {
        return dateTime.replace("T", " ").substring(0, 16);
    }

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("Recepcionista/TelaPrincipalRecepcionista");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
