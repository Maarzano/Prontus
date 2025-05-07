package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TelaCadastrarPacienteController {

    @FXML
    private TextField nomeField, cpfField, celularField, emailField, ruaField, bairroField, cepField, numeroField, cidadeField, estadoField;

    @FXML
    private DatePicker dataNascPicker;

    @FXML
    private Button btnCancelar, btnSalvar;

    @FXML
    private void handleCancelar() {
        try {
            App.setRoot("Recepcionista/TelaPrincipalRecepcionista");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível voltar para a tela inicial.");
        }
    }

    @FXML
    private void handleSalvar() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String celular = celularField.getText();
        String email = emailField.getText();
        String dataNasc = dataNascPicker.getValue().toString();
        String rua = ruaField.getText();
        String bairro = bairroField.getText();
        String cep = cepField.getText();
        String numero = numeroField.getText();
        String cidade = cidadeField.getText();
        String estado = estadoField.getText();

        try {
            // Step 1: Create an address
            String addressId = createAddress(rua, bairro, cep, numero, cidade, estado);

            // Step 2: Create a patient linked to the created address
            createPatient(nome, cpf, dataNasc, celular, email, addressId);

            showAlert("Sucesso", "Paciente cadastrado com sucesso!");
            App.setRoot("Recepcionista/TelaPrincipalRecepcionista");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Ocorreu um erro ao tentar cadastrar o paciente.");
        }
    }

    private String createAddress(String rua, String bairro, String cep, String numero, String cidade, String estado) throws Exception {
        // Validate required fields
        if (rua == null || rua.trim().isEmpty()) {
            throw new Exception("O campo Rua não pode estar vazio.");
        }
        if (bairro == null || bairro.trim().isEmpty()) {
            throw new Exception("O campo Bairro não pode estar vazio.");
        }
        if (cep == null || cep.trim().isEmpty()) {
            throw new Exception("O campo CEP não pode estar vazio.");
        }
        if (numero == null || numero.trim().isEmpty()) {
            throw new Exception("O campo Número não pode estar vazio.");
        }
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new Exception("O campo Cidade não pode estar vazio.");
        }
        if (estado == null || estado.trim().isEmpty()) {
            throw new Exception("O campo Estado não pode estar vazio.");
        }

        URL addressUrl = new URL("http://localhost:8080/api/addresses");
        HttpURLConnection addressConn = (HttpURLConnection) addressUrl.openConnection();
        addressConn.setRequestMethod("POST");
        addressConn.setRequestProperty("Content-Type", "application/json");
        addressConn.setDoOutput(true);

        // Use the correct JSON structure for the address
        String addressJson = String.format(
            "{\"street\": \"%s\", \"cep\": \"%s\", \"number\": \"%s\", \"neighborhood\": \"%s\", \"city\": \"%s\", \"state\": \"%s\"}",
            rua, cep, numero, bairro, cidade, estado
        );
        System.out.println("Payload enviado para criar endereço: " + addressJson);

        try (OutputStreamWriter writer = new OutputStreamWriter(addressConn.getOutputStream())) {
            writer.write(addressJson);
        }

        if (addressConn.getResponseCode() == 201) {
            return parseIdFromResponse(addressConn);
        } else {
            Scanner errorScanner = new Scanner(addressConn.getErrorStream());
            String errorResponse = errorScanner.useDelimiter("\\A").next();
            System.err.println("Erro ao criar endereço: " + errorResponse);
            throw new Exception("Erro ao criar endereço. Código de resposta: " + addressConn.getResponseCode() + ". Detalhes: " + errorResponse);
        }
    }

    private void createPatient(String nome, String cpf, String dataNasc, String celular, String email, String addressId) throws Exception {
        URL patientUrl = new URL("http://localhost:8080/api/patients");
        HttpURLConnection patientConn = (HttpURLConnection) patientUrl.openConnection();
        patientConn.setRequestMethod("POST");
        patientConn.setRequestProperty("Content-Type", "application/json");
        patientConn.setDoOutput(true);

        String patientJson = String.format(
            "{\"name\": \"%s\", \"cpf\": \"%s\", \"dataNasc\": \"%s\", \"cellphone\": \"%s\", \"email\": \"%s\", \"addressId\": %s}",
            nome, cpf, dataNasc, celular, email, addressId
        );

        try (OutputStreamWriter writer = new OutputStreamWriter(patientConn.getOutputStream())) {
            writer.write(patientJson);
        }

        if (patientConn.getResponseCode() != 201) {
            throw new Exception("Erro ao criar paciente.");
        }
    }

    private String parseIdFromResponse(HttpURLConnection conn) throws Exception {
        try (java.util.Scanner scanner = new java.util.Scanner(conn.getInputStream())) {
            String response = scanner.useDelimiter("\\A").next();
            if (response.contains("\"id\":")) {
                return response.split("\"id\":")[1].split(",")[0].trim();
            } else {
                throw new Exception("Response does not contain an ID: " + response);
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
