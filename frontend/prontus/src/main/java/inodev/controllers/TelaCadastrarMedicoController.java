package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TelaCadastrarMedicoController {

    @FXML
    private TextField nomeField, cpfField, emailField, celularField, crmField, senhaField;
    @FXML
    private ComboBox<String> especialidadeComboBox;
    @FXML
    private TextField ruaField, bairroField, cepField, numeroField, cidadeField, estadoField;

    @FXML
    private void initialize() {
        for (Specialties specialty : Specialties.values()) {
            especialidadeComboBox.getItems().add(specialty.name());
        }
    }

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
        String especialidade = especialidadeComboBox.getValue();
        String senha = senhaField.getText();
        String rua = ruaField.getText();
        String bairro = bairroField.getText();
        String cep = cepField.getText();
        String numero = numeroField.getText();
        String cidade = cidadeField.getText();
        String estado = estadoField.getText();

        try {
            String userId = createUser(nome, cpf, email, celular, senha);

            String specialtyId = createSpecialty(especialidade);

            String addressId = createAddress(rua, bairro, cep, numero, cidade, estado);

            createDoctor(crm, specialtyId, userId, addressId);

            showAlert("Sucesso", "Médico cadastrado com sucesso!");
            App.setRoot("AdmNormal/TelaPrincipalAdmNormal");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Ocorreu um erro ao tentar cadastrar o médico.");
        }
    }

    private String createUser(String nome, String cpf, String email, String celular, String senha) throws Exception {
        URL userUrl = new URL("http://localhost:8080/api/users");
        HttpURLConnection userConn = (HttpURLConnection) userUrl.openConnection();
        userConn.setRequestMethod("POST");
        userConn.setRequestProperty("Content-Type", "application/json");
        userConn.setDoOutput(true);

        String userJson = String.format(
            "{\"name\": \"%s\", \"cpf\": \"%s\", \"email\": \"%s\", \"cellphone\": \"%s\", \"password\": \"%s\", \"role\": \"DOCTOR\"}",
            nome, cpf, email, celular, senha
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

    private String createSpecialty(String especialidade) throws Exception {
        if (especialidade == null || especialidade.trim().isEmpty()) {
            throw new Exception("O campo Especialidade não pode estar vazio.");
        }

        URL specialtyUrl = new URL("http://localhost:8080/api/specialties");
        HttpURLConnection specialtyConn = (HttpURLConnection) specialtyUrl.openConnection();
        specialtyConn.setRequestMethod("POST");
        specialtyConn.setRequestProperty("Content-Type", "application/json");
        specialtyConn.setDoOutput(true);

        // Use the correct JSON key "specialty"
        String specialtyJson = String.format("{\"specialty\": \"%s\"}", especialidade);
        System.out.println("Payload enviado para criar especialidade: " + specialtyJson);

        try (OutputStreamWriter writer = new OutputStreamWriter(specialtyConn.getOutputStream())) {
            writer.write(specialtyJson);
        }

        if (specialtyConn.getResponseCode() == 201) {
            Scanner scanner = new Scanner(specialtyConn.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            return parseIdFromResponse(response);
        } else {
            Scanner errorScanner = new Scanner(specialtyConn.getErrorStream());
            String errorResponse = errorScanner.useDelimiter("\\A").next();
            System.err.println("Erro ao criar especialidade: " + errorResponse);
            throw new Exception("Erro ao criar especialidade. Código de resposta: " + specialtyConn.getResponseCode() + ". Detalhes: " + errorResponse);
        }
    }

    private String createAddress(String rua, String bairro, String cep, String numero, String cidade, String estado) throws Exception {
        if (cep == null || cep.isEmpty()) {
            throw new Exception("O campo CEP não pode estar vazio.");
        }

        URL addressUrl = new URL("http://localhost:8080/api/addresses");
        HttpURLConnection addressConn = (HttpURLConnection) addressUrl.openConnection();
        addressConn.setRequestMethod("POST");
        addressConn.setRequestProperty("Content-Type", "application/json");
        addressConn.setDoOutput(true);

        String addressJson = String.format(
            "{\"street\": \"%s\", \"cep\": \"%s\", \"number\": \"%s\", \"neighborhood\": \"%s\", \"city\": \"%s\", \"state\": \"%s\"}",
            rua, cep, numero, bairro, cidade, estado
        );
        System.out.println("Payload enviado para criar endereço: " + addressJson);

        try (OutputStreamWriter writer = new OutputStreamWriter(addressConn.getOutputStream())) {
            writer.write(addressJson);
        }

        if (addressConn.getResponseCode() == 201) {
            Scanner scanner = new Scanner(addressConn.getInputStream());
            String response = scanner.useDelimiter("\\A").next();
            return parseIdFromResponse(response);
        } else {
            Scanner errorScanner = new Scanner(addressConn.getErrorStream());
            String errorResponse = errorScanner.useDelimiter("\\A").next();
            System.err.println("Erro ao criar endereço: " + errorResponse);
            throw new Exception("Erro ao criar endereço. Código de resposta: " + addressConn.getResponseCode() + ". Detalhes: " + errorResponse);
        }
    }

    private void createDoctor(String crm, String specialtyId, String userId, String addressId) throws Exception {
        URL doctorUrl = new URL("http://localhost:8080/api/doctors");
        HttpURLConnection doctorConn = (HttpURLConnection) doctorUrl.openConnection();
        doctorConn.setRequestMethod("POST");
        doctorConn.setRequestProperty("Content-Type", "application/json");
        doctorConn.setDoOutput(true);

        String doctorJson = String.format(
            "{\"crm\": \"%s\", \"userId\": %s, \"specialtyId\": %s, \"addressId\": %s}",
            crm, userId, specialtyId, addressId
        );
        System.out.println("Payload enviado para criar médico: " + doctorJson);

        try (OutputStreamWriter writer = new OutputStreamWriter(doctorConn.getOutputStream())) {
            writer.write(doctorJson);
        }

        if (doctorConn.getResponseCode() != 201) {
            Scanner errorScanner = new Scanner(doctorConn.getErrorStream());
            String errorResponse = errorScanner.useDelimiter("\\A").next();
            System.err.println("Erro ao criar médico: " + errorResponse);
            throw new Exception("Erro ao criar médico. Código de resposta: " + doctorConn.getResponseCode() + ". Detalhes: " + errorResponse);
        }
    }

    private String parseIdFromResponse(String response) throws Exception {
        if (response.contains("\"id\":")) {
            return response.split("\"id\":")[1].split(",")[0].trim();
        } else {
            throw new Exception("Response does not contain an ID: " + response);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public enum Specialties {
        ACUPUNTURA,
        ALERGIA_E_IMUNOLOGIA,
        ANESTESIOLOGIA,
        ANGIIOLOGIA,
        CARDIOLOGIA,
        CIRURGIA_CARDIOVASCULAR,
        CIRURGIA_DA_MAO,
        CIRURGIA_DE_CABECA_E_PESCOCO,
        CIRURGIA_DO_APARELHO_DIGESTIVO,
        CIRURGIA_GERAL,
        CIRURGIA_ONCOLOGICA,
        CIRURGIA_PEDIATRICA,
        CIRURGIA_PLASTICA,
        CIRURGIA_TORACICA,
        CIRURGIA_VASCULAR,
        CLINICA_MEDICA,
        COLOPROCTOLOGIA,
        DERMATOLOGIA,
        ENDCRINOLOGIA_E_METABOLOGIA,
        ENDOSCOPIA,
        GASTROENTEROLOGIA,
        GENETICA_MEDICA,
        GERIATRIA,
        GINECOLOGIA_E_OBSTETRICIA,
        HEMATOLOGIA_E_HEMOTERAPIA,
        HOMEOPATIA,
        INFECTOLOGIA,
        MASTOLOGIA,
        MEDICINA_DE_EMERGENCIA,
        MEDICINA_DE_FAMILIA_E_COMUNIDADE,
        MEDICINA_DO_TRABALHO,
        MEDICINA_DO_TRAFEGO,
        MEDICINA_ESPORTIVA,
        MEDICINA_FISICA_E_REABILITACAO,
        MEDICINA_INTENSIVA,
        MEDICINA_LEGAL_E_PERICIA_MEDICA,
        MEDICINA_NUCLEAR,
        MEDICINA_PREVENTIVA_E_SOCIAL,
        NEFROLOGIA,
        NEUROCIRURGIA,
        NEUROLOGIA,
        NUTROLOGIA,
        OFTALMOLOGIA,
        ONCOLOGIA_CLINICA,
        ORTOPEDIA_E_TRAUMATOLOGIA,
        OTORRINOLARINGOLOGIA,
        PATOLOGIA,
        PATOLOGIA_CLINICA_MEDICINA_LABORATORIAL,
        PEDIATRIA,
        PNEUMOLOGIA,
        PSIQUIATRIA,
        RADIOLOGIA_E_DIAGNOSTICO_POR_IMAGEM,
        RADIOTERAPIA,
        REUMATOLOGIA,
        UROLOGIA;
    }
}
