package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CreaSpiaggiaView {
    public TextField nomeSpiaggia;
    public TextField numeroOmbrelloni;
    public TextField numeroSdraio;
    private DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController) {
        this.masterController=masterController;
    }

    public void handleAvanti(ActionEvent actionEvent) {
        masterController.creaSpiaggia(nomeSpiaggia.getText());
        masterController.getChalet().getSpiaggia().setNumeroOmbrelloni(Integer.parseInt(numeroOmbrelloni.getText()));
        masterController.getChalet().getSpiaggia().setNumeroSdraio(Integer.parseInt(numeroSdraio.getText()));
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiBar.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            AggiungiBarView controller = loader.getController();
            controller.initialize(masterController);
            stage.showAndWait();
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
