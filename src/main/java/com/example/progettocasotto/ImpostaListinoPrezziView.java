package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ImpostaListinoPrezziView {

    @FXML
    private TextField scontoDueMesi;
    @FXML
    private TextField scontoUnMese;
    @FXML
    private TextField scontoUnaSettimana;

    private DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController) {
        this.masterController=masterController;
        scontoUnaSettimana.setText(String.valueOf(masterController.getChalet().getSpiaggia().getListinoPrezzi().getScontoUnaSettimana()));
        scontoUnMese.setText(String.valueOf(masterController.getChalet().getSpiaggia().getListinoPrezzi().getScontoUnMese()));
        scontoDueMesi.setText(String.valueOf(masterController.getChalet().getSpiaggia().getListinoPrezzi().getScontoDueMesi()));
    }

    public void handleSalva(ActionEvent actionEvent) {
        if (!scontoDueMesi.getText().isBlank() && !scontoUnMese.getText().isBlank() && !scontoUnaSettimana.getText().isBlank()) {
            masterController.getChalet().getSpiaggia().setListinoPrezzi(Double.parseDouble(scontoUnaSettimana.getText()), Double.parseDouble(scontoUnMese.getText()), Double.parseDouble(scontoDueMesi.getText()));
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        }
    }
}
