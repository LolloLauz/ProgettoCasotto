package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AggiungiOmbrelloniView {

    public TextArea ombrelloniDisponibii;
    public TextField ombrelloneSelezionato;
    private AddettoASController asController;
    private String idPrenotazione;
    private ArrayList<String>listaOmbrelloni=new ArrayList<>();

    public void initialize(AddettoASController asControlle, String identificativoPrenotazione) {
        this.asController=asControlle;
        this.idPrenotazione=identificativoPrenotazione;
        ombrelloniDisponibii.setText(asController.mostraOmbrelloniLiberi(idPrenotazione));
    }

    public void handleAggiungiOmbrellone(ActionEvent actionEvent) {
        if(!ombrelloneSelezionato.getText().equals("")) {
            listaOmbrelloni.add(ombrelloneSelezionato.getText());
            ombrelloneSelezionato.setText("");
        }
    }

    public void handleCOnfermaLeModifiche(ActionEvent actionEvent) {
        if(!listaOmbrelloni.isEmpty()) {
            asController.inserimentoOmbrelloni(idPrenotazione, listaOmbrelloni);
        }
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
