package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    }

    public void handleSalva(ActionEvent actionEvent) {
        masterController.getChalet().getSpiaggia().setListinoPrezzi(Double.parseDouble(scontoUnaSettimana.getText()), Double.parseDouble(scontoUnMese.getText()), Double.parseDouble(scontoDueMesi.getText()));
        masterController.getChalet().getSpiaggia().setNumeroOmbrelloni(Integer.parseInt(numeroOmbrelloni.getText()));
        masterController.getChalet().getSpiaggia().setNumeroSdraio(Integer.parseInt(numeroSdraio.getText()));
    }
}
