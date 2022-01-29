package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TerminaleOrdinazioniView {

    @FXML
    public TextArea txtOrdiniDaConsegnare;

    private DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController) {
        this.masterController=masterController;
        txtOrdiniDaConsegnare.setText(masterController.getChalet().getBar().getListaOrdinazioniDaConsegnare());
    }

    public void btnClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
