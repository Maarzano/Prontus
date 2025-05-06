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
}
