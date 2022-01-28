package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
