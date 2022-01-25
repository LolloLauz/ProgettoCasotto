package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FXGestoreChalet {

    @FXML
    private Button creaChaletDiDefault;

    DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController){
        this.masterController=masterController;
    }
    public void handleDefaultChalet(ActionEvent actionEvent) {
        masterController.creaChaletDefault();
    }

    public void handlePagamentoContanti(ActionEvent actionEvent) {

    }

    public void handleEliminaPrenotazione(ActionEvent actionEvent) {
    }

    public void handleModificaPrenotazione(ActionEvent actionEvent) {
    }

    public void handleCreaAttivita(ActionEvent actionEvent) {
    }

    public void handlePrenotazioneManule(ActionEvent actionEvent) {
    }

    public void handleCreaBar(ActionEvent actionEvent) {
    }

    public void handleCreaSpiaggia(ActionEvent actionEvent) {
    }
}
