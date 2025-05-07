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

public class ListaPacientesController {

    @FXML
    private VBox vboxContainer;

    @FXML
    private Button btnVoltar;

    @FXML
    private void initialize() {
        carregarPacientes();
    }

    private void carregarPacientes() {
        try {
            URL patientsUrl = new URL("http://localhost:8080/api/patients");
            HttpURLConnection patientsConn = (HttpURLConnection) patientsUrl.openConnection();
            patientsConn.setRequestMethod("GET");

            if (patientsConn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(patientsConn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonArray patients = JsonParser.parseString(response.toString()).getAsJsonArray();
                for (JsonElement element : patients) {
                    JsonObject patient = element.getAsJsonObject();

                    int addressId = patient.get("addressId").getAsInt();
                    JsonObject address = fetchAddress(addressId);

                    adicionarCardPaciente(
                        patient.get("name").getAsString(),
                        patient.get("cpf").getAsString(),
                        patient.get("dataNasc").getAsString(),
                        patient.get("cellphone").getAsString(),
                        patient.get("email").getAsString(),
                        address.get("street").getAsString(),
                        address.get("neighborhood").getAsString(),
                        address.get("city").getAsString(),
                        address.get("state").getAsString(),
                        address.get("cep").getAsString()
                    );
                }
            } else {
                System.out.println("Erro ao buscar pacientes: " + patientsConn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JsonObject fetchAddress(int addressId) throws Exception {
        URL addressUrl = new URL("http://localhost:8080/api/addresses/" + addressId);
        HttpURLConnection addressConn = (HttpURLConnection) addressUrl.openConnection();
        addressConn.setRequestMethod("GET");

        if (addressConn.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(addressConn.getInputStream()));
            String response = reader.readLine();
            reader.close();
            return JsonParser.parseString(response).getAsJsonObject();
        } else {
            throw new Exception("Erro ao buscar endereço com ID: " + addressId);
        }
    }

    private void adicionarCardPaciente(String nome, String cpf, String dataNasc, String celular, String email, String rua, String bairro, String cidade, String estado, String cep) {
        HBox card = new HBox();
        card.setSpacing(20);
        card.setStyle("-fx-padding: 15; -fx-border-color: #5A39D2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-background-color: #F3F3F3;");

        VBox details = new VBox();
        details.setSpacing(5);

        Text nomeText = new Text("Nome: " + nome);
        nomeText.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Text cpfText = new Text("CPF: " + cpf);
        cpfText.setStyle("-fx-font-size: 14;");

        Text dataNascText = new Text("Data de Nascimento: " + formatarData(dataNasc));
        dataNascText.setStyle("-fx-font-size: 14;");

        Text celularText = new Text("Celular: " + celular);
        celularText.setStyle("-fx-font-size: 14;");

        Text emailText = new Text("Email: " + email);
        emailText.setStyle("-fx-font-size: 14;");

        Text enderecoText = new Text(String.format("Endereço: %s, %s, %s, %s - CEP: %s", rua, bairro, cidade, estado, cep));
        enderecoText.setStyle("-fx-font-size: 14;");

        details.getChildren().addAll(nomeText, cpfText, dataNascText, celularText, emailText, enderecoText);

        card.getChildren().add(details);
        vboxContainer.getChildren().add(card);
    }

    private String formatarData(String data) {
        return data.split("-")[2] + "/" + data.split("-")[1] + "/" + data.split("-")[0];
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
