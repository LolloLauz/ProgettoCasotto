package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class AggiungiBevandeBarView {

    public TextField nomeBevanda;
    public TextField quantitaBevanda;
    public TextField prezzoBevanda;
    public TextField descizioneBevanda;
    private DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController) {
        this.masterController=masterController;
    }


    public void handleAggiugniBevanda(ActionEvent actionEvent) {
        if(!nomeBevanda.getText().isBlank() && quantitaBevanda.getText().isBlank() && prezzoBevanda.getText().isBlank()){
            masterController.getChalet().getBar().creaBevanda(nomeBevanda.getText(),descizioneBevanda.getText(),Integer.parseInt(quantitaBevanda.getText()),Double.parseDouble(prezzoBevanda.getText()));
            nomeBevanda.setText("");
            quantitaBevanda.setText("");
            prezzoBevanda.setText("");
        }
    }

    public void handleEsci(ActionEvent actionEvent) {

    }
}
