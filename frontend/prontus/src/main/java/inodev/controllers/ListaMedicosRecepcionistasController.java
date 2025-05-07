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

public class ListaMedicosRecepcionistasController {

    @FXML
    private VBox vboxContainer;

    @FXML
    private Button btnVoltar;

    @FXML
    private void initialize() {
        carregarMedicosRecepcionistas();
    }

    private void carregarMedicosRecepcionistas() {
        try {
            URL url = new URL("http://localhost:8080/api/users?role=DOCTOR,RECEPTIONIST");
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

                JsonArray users = JsonParser.parseString(response.toString()).getAsJsonArray();
                for (JsonElement element : users) {
                    JsonObject user = element.getAsJsonObject();
                    String role = user.get("role").getAsString();
                    if (role.equals("DOCTOR") || role.equals("RECEPCIONIST")) {
                        adicionarCardUsuario(
                            user.get("name").getAsString(),
                            user.get("email").getAsString(),
                            user.get("cpf").getAsString(),
                            user.get("cellphone").getAsString(),
                            role
                        );
                    }
                }
            } else {
                System.out.println("Erro ao buscar usuários: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarCardUsuario(String nome, String email, String cpf, String celular, String role) {
        HBox card = new HBox();
        card.setSpacing(20);
        card.setStyle("-fx-padding: 15; -fx-border-color: #5A39D2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: #F3F3F3;");

        VBox details = new VBox();
        details.setSpacing(5);

        Text nomeText = new Text("Nome: " + nome);
        nomeText.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Text emailText = new Text("Email: " + email);
        emailText.setStyle("-fx-font-size: 14;");

        Text cpfText = new Text("CPF: " + cpf);
        cpfText.setStyle("-fx-font-size: 14;");

        Text celularText = new Text("Celular: " + celular);
        celularText.setStyle("-fx-font-size: 14;");

        Text roleText = new Text("Perfil: " + role);
        roleText.setStyle("-fx-font-size: 14;");

        details.getChildren().addAll(nomeText, emailText, cpfText, celularText, roleText);

        card.getChildren().add(details);
        vboxContainer.getChildren().add(card);
    }

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("AdmNormal/TelaPrincipalAdmNormal");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
