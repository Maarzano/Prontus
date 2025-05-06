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

public class ListaADMsController {

    @FXML
    private VBox vboxContainer;

    @FXML
    private Button btnVoltar;

    @FXML
    private void initialize() {
        carregarADMs();
    }

    private void carregarADMs() {
        try {
            URL url = new URL("http://localhost:8080/api/users?role=ADM");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonArray adms = JsonParser.parseString(response.toString()).getAsJsonArray();
                for (JsonElement element : adms) {
                    JsonObject adm = element.getAsJsonObject();
                    adicionarCardADM(adm.get("name").getAsString(), adm.get("email").getAsString(), adm.get("cpf").getAsString());
                }
            } else {
                System.out.println("Erro ao buscar ADMs: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarCardADM(String nome, String email, String cpf) {
        HBox card = new HBox();
        card.setSpacing(20);
        card.setStyle("-fx-padding: 10; -fx-border-color: black; -fx-border-radius: 5; -fx-background-radius: 5; -fx-background-color: #f3f3f3;");

        Text nomeText = new Text("Nome: " + nome);
        nomeText.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Text emailText = new Text("Email: " + email);
        emailText.setStyle("-fx-font-size: 14;");

        Text cpfText = new Text("CPF: " + cpf);
        cpfText.setStyle("-fx-font-size: 14;");

        card.getChildren().addAll(nomeText, emailText, cpfText);
        vboxContainer.getChildren().add(card);
    }

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("AdmSupremo/TelaPrincipalAdmSupremo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
