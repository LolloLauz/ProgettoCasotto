package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultOrdinazione;
import com.example.progettocasotto.Model.Chalet.Bar.StatoOrdinazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TerminaleOrdinazioniView {

    @FXML
    private TextArea txtOrdiniDaConsegnare;

    @FXML
    private TextField txtIdOrdine;

    private DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController) {
        this.masterController=masterController;
        refreshOrders();
    }

    public void btnContrassegna(ActionEvent actionEvent) {
        if(!txtIdOrdine.getText().isBlank()) {
            if(checkOrdinazione(txtIdOrdine.getText())) {
                masterController.getChalet().getBar().getOrdinazioneById(txtIdOrdine.getText()).setStatoConsegna(StatoOrdinazione.CONSEGNATO);
                masterController.getGestoreDB().setPrenotazioneConsegnata(txtIdOrdine.getText());
                refreshOrders();
                txtIdOrdine.clear();
            }
        }
    }

    boolean checkOrdinazione(String txt){
        for(DefaultOrdinazione ordinazione: masterController.getChalet().getBar().getListaOrdinazioni())
            if(ordinazione.getID().equals(txt))
                return true;
        return false;
    }

    void refreshOrders(){
        txtOrdiniDaConsegnare.setText(masterController.getChalet().getBar().getListaOrdinazioniDaConsegnare());
    }

    public void btnClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
