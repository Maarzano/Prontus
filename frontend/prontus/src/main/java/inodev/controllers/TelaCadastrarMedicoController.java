package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TelaCadastrarMedicoController {

    @FXML
    private TextField nomeField, cpfField, emailField, celularField, crmField, especialidadeField;
    @FXML
    private TextField ruaField, bairroField, cepField, numeroField, cidadeField, estadoField;

    @FXML
    private void handleVoltar() {
        try {
            App.setRoot("AdmNormal/TelaPrincipalAdmNormal");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar para a tela inicial.");
        }
    }

    @FXML
    private void handleSalvar() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String celular = celularField.getText();
        String crm = crmField.getText();
        String especialidade = especialidadeField.getText();
        String rua = ruaField.getText();
        String bairro = bairroField.getText();
        String cep = cepField.getText();
        String numero = numeroField.getText();
        String cidade = cidadeField.getText();
        String estado = estadoField.getText();

        try {
            // Step 1: Create a user
            String userId = createUser(nome, cpf, email, celular);

            // Step 2: Create an address
            String addressId = createAddress(rua, bairro, cep, numero, cidade, estado);

            // Step 3: Create a doctor
            createDoctor(crm, especialidade, userId, addressId);

            showAlert("Sucesso", "Médico cadastrado com sucesso!");
            App.setRoot("AdmNormal/TelaPrincipalAdmNormal");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Ocorreu um erro ao tentar cadastrar o médico.");
        }
    }

    private String createUser(String nome, String cpf, String email, String celular) throws Exception {
        URL userUrl = new URL("http://localhost:8080/api/users");
        HttpURLConnection userConn = (HttpURLConnection) userUrl.openConnection();
        userConn.setRequestMethod("POST");
        userConn.setRequestProperty("Content-Type", "application/json");
        userConn.setDoOutput(true);

        String userJson = String.format(
            "{\"name\": \"%s\", \"cpf\": \"%s\", \"email\": \"%s\", \"cellphone\": \"%s\", \"password\": \"123456\", \"role\": \"DOCTOR\"}",
            nome, cpf, email, celular
        );

        try (OutputStreamWriter writer = new OutputStreamWriter(userConn.getOutputStream())) {
            writer.write(userJson);
        }

        if (userConn.getResponseCode() == 201) {
            Scanner scanner = new Scanner(userConn.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            return parseIdFromResponse(response);
        } else {
            throw new Exception("Erro ao criar usuário.");
        }
    }

    private String createAddress(String rua, String bairro, String cep, String numero, String cidade, String estado) throws Exception {
        URL addressUrl = new URL("http://localhost:8080/api/addresses");
        HttpURLConnection addressConn = (HttpURLConnection) addressUrl.openConnection();
        addressConn.setRequestMethod("POST");
        addressConn.setRequestProperty("Content-Type", "application/json");
        addressConn.setDoOutput(true);

        String addressJson = String.format(
            "{\"street\": \"%s\", \"neighborhood\": \"%s\", \"zipCode\": \"%s\", \"number\": \"%s\", \"city\": \"%s\", \"state\": \"%s\"}",
            rua, bairro, cep, numero, cidade, estado
        );

        try (OutputStreamWriter writer = new OutputStreamWriter(addressConn.getOutputStream())) {
            writer.write(addressJson);
        }

        if (addressConn.getResponseCode() == 201) {
            Scanner scanner = new Scanner(addressConn.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            return parseIdFromResponse(response);
        } else {
            throw new Exception("Erro ao criar endereço.");
        }
    }

    private void createDoctor(String crm, String especialidade, String userId, String addressId) throws Exception {
        URL doctorUrl = new URL("http://localhost:8080/api/doctors");
        HttpURLConnection doctorConn = (HttpURLConnection) doctorUrl.openConnection();
        doctorConn.setRequestMethod("POST");
        doctorConn.setRequestProperty("Content-Type", "application/json");
        doctorConn.setDoOutput(true);

        String doctorJson = String.format(
            "{\"crm\": \"%s\", \"specialty\": \"%s\", \"userId\": \"%s\", \"addressId\": \"%s\"}",
            crm, especialidade, userId, addressId
        );

        try (OutputStreamWriter writer = new OutputStreamWriter(doctorConn.getOutputStream())) {
            writer.write(doctorJson);
        }

        if (doctorConn.getResponseCode() != 201) {
            throw new Exception("Erro ao criar médico.");
        }
    }

    private String parseIdFromResponse(String response) {
        return response.split("\"id\":\"")[1].split("\"")[0];
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
