package inodev.controllers;

import inodev.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class TelaCadastrarAdmNormalController {

    @FXML
    private void handleVoltar() throws IOException {
        App.setRoot("AdmSupremo/TelaPrincipalAdmSupremo");
    }
}
