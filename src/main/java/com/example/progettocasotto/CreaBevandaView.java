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

public class CreaBevandaView {
    public TextField nomeBevanda;
    public TextField prezzoBevanda;
    public TextField quantitaBevanda;
    public TextField descrizione;
    private DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController) {
        this.masterController=masterController;
    }

    public void handleCrea(ActionEvent actionEvent) {
        if(!nomeBevanda.getText().isBlank() && quantitaBevanda.getText().isBlank() && prezzoBevanda.getText().isBlank()){
            masterController.getChalet().getBar().creaBevanda(nomeBevanda.getText(),descrizione.getText(),Integer.parseInt(quantitaBevanda.getText()),Double.parseDouble(prezzoBevanda.getText()));
            nomeBevanda.setText("");
            quantitaBevanda.setText("");
            prezzoBevanda.setText("");
        }
    }

    public void handleEsci(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ImpostaListinoPrezzi.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            ImpostaListinoPrezziView controller = loader.getController();
            controller.initialize(masterController);
            stage.showAndWait();
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
