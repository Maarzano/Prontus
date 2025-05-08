package inodev.controllers;

import inodev.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class TelaPrincipalMedicoController {

    private int userId;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    public void handleVerConsultas(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/inodev/Médico/ListaConsultasMedico.fxml"));
        Parent root = loader.load();
        ListaConsultasMedicoController controller = loader.getController();
        controller.setUserId(userId);
        App.setRoot(root);
    }
}
