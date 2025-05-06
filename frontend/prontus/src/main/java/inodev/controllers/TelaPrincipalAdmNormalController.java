package inodev.controllers;

import inodev.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class TelaPrincipalAdmNormalController {

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    public void handleCadastrarMedico(ActionEvent event) throws IOException {
        App.setRoot("AdmNormal/CadastrarMedico");
    }

    @FXML
    public void handleCadastrarRecepcionista(ActionEvent event) throws IOException {
        App.setRoot("AdmNormal/CadastrarRecepcionista");
    }

    @FXML
    public void handleVerListaMedicosRecepcionistas(ActionEvent event) throws IOException {
        App.setRoot("AdmSupremo/listasCadastros");
    }
}
