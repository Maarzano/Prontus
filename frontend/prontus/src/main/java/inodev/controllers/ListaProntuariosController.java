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

public class ListaProntuariosController {

    @FXML
    private VBox vboxContainer;

    @FXML
    private Button btnVoltar;

    @FXML
    private void initialize() {
        carregarProntuarios();
    }

    private void carregarProntuarios() {
        try {
            URL url = new URL("http://localhost:8080/api/medical-records");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = reader.readLine();
                reader.close();

                JsonArray prontuarios = JsonParser.parseString(response).getAsJsonArray();
                for (JsonElement element : prontuarios) {
                    JsonObject prontuario = element.getAsJsonObject();

                    long id = prontuario.get("id").getAsLong();
                    long schedulingId = prontuario.get("schedulingId").getAsLong();
                    String dateRegister = prontuario.get("dateRegister").getAsString();
                    String diagnostic = prontuario.get("diagnostic").getAsString();
                    String recepie = prontuario.get("recepie").getAsString();
                    String anotation = prontuario.get("anotation").getAsString();

                    adicionarCardProntuario(id, schedulingId, dateRegister, diagnostic, recepie, anotation);
                }
            } else {
                System.out.println("Erro ao buscar prontuários: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarCardProntuario(long id, long schedulingId, String dateRegister, String diagnostic, String recepie, String anotation) {
        HBox card = new HBox();
        card.setSpacing(20);
        card.setStyle("-fx-padding: 15; -fx-border-color: #5A39D2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: #F3F3F3;");

        VBox details = new VBox();
        details.setSpacing(5);

        Text idText = new Text("ID Prontuário: " + id);
        idText.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Text schedulingText = new Text("ID Consulta: " + schedulingId);
        schedulingText.setStyle("-fx-font-size: 14;");

        Text dateText = new Text("Data Registro: " + formatarDataHora(dateRegister));
        dateText.setStyle("-fx-font-size: 14;");

        Text diagnosticText = new Text("Diagnóstico: " + diagnostic);
        diagnosticText.setStyle("-fx-font-size: 14;");

        Text recepieText = new Text("Receita: " + recepie);
        recepieText.setStyle("-fx-font-size: 14;");

        Text anotationText = new Text("Anotação: " + anotation);
        anotationText.setStyle("-fx-font-size: 14;");

        details.getChildren().addAll(idText, schedulingText, dateText, diagnosticText, recepieText, anotationText);

        card.getChildren().add(details);
        vboxContainer.getChildren().add(card);
    }

    private String formatarDataHora(String dateTime) {
        return dateTime.replace("T", " ").substring(0, 16);
    }

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("Medico/TelaPrincipalMedico");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUserId(int userId) {
    }
}
