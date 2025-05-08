package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

public class ListaConsultasMedicoController {

    @FXML
    private VBox vboxContainer;

    @FXML
    private Button btnVoltar;

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
        carregarConsultas();
    }

    private void carregarConsultas() {
        try {
            URL doctorUrl = new URL("http://localhost:8080/api/doctors");
            HttpURLConnection doctorConn = (HttpURLConnection) doctorUrl.openConnection();
            doctorConn.setRequestMethod("GET");

            if (doctorConn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(doctorConn.getInputStream()));
                String response = reader.readLine();
                reader.close();

                JsonArray doctors = JsonParser.parseString(response).getAsJsonArray();
                int doctorId = findDoctorIdByUserId(doctors, userId);

                if (doctorId != -1) {
                    fetchConsultations(doctorId);
                } else {
                    System.out.println("Nenhum médico encontrado para o userId: " + userId);
                }
            } else {
                System.out.println("Erro ao buscar médicos: " + doctorConn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int findDoctorIdByUserId(JsonArray doctors, int userId) {
        for (JsonElement element : doctors) {
            JsonObject doctor = element.getAsJsonObject();
            if (doctor.get("userId").getAsInt() == userId) {
                return doctor.get("id").getAsInt();
            }
        }
        return -1;
    }

    private void fetchConsultations(int doctorId) {
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
                    int patientId = scheduling.get("patientId").getAsInt();
                    String dateTime = scheduling.get("dateTime").getAsString();
                    String status = scheduling.get("statusScheduling").getAsString();

                    JsonObject patient = fetchPatient(patientId);

                    adicionarCardConsulta(
                        scheduling.get("id").getAsInt(),
                        patient.get("name").getAsString(),
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

    private void adicionarCardConsulta(int schedulingId, String patientName, String dateTime, String status) {
        HBox card = new HBox();
        card.setSpacing(20);
        card.setStyle("-fx-padding: 15; -fx-border-color: #5A39D2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: #F3F3F3;");
        card.setOnMouseClicked(event -> handleEscreverProntuario(schedulingId)); // Add click event

        VBox details = new VBox();
        details.setSpacing(5);

        Text patientText = new Text("Paciente: " + patientName);
        patientText.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Text dateTimeText = new Text("Data e Hora: " + dateTime.replace("T", " ").substring(0, 16));
        dateTimeText.setStyle("-fx-font-size: 14;");

        Text statusText = new Text("Status: " + status);
        statusText.setStyle("-fx-font-size: 14;");

        details.getChildren().addAll(patientText, dateTimeText, statusText);

        card.getChildren().add(details);
        vboxContainer.getChildren().add(card);
    }

    private void handleEscreverProntuario(int schedulingId) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/inodev/Médico/EscreverProntuario.fxml"));
            Parent root = loader.load();
            EscreverProntuarioController controller = loader.getController();
            controller.setSchedulingId(schedulingId);
            App.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("Médico/TelaPrincipalMedico");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
