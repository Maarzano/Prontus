package inodev.controllers;

import inodev.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class TelaPrincipalRecepcionistaController {

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    public void handleCadastrarPaciente(ActionEvent event) throws IOException {
        App.setRoot("Recepcionista/CadastrarPaciente");
    }

    @FXML
    public void handleVerListaPacientes(ActionEvent event) throws IOException {
        App.setRoot("Recepcionista/ListaPacientes");
    }
}
