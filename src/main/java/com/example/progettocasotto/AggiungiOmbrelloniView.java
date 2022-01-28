package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultGestoreController;
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
    private DefaultGestoreController gestoreController;
    private boolean flagGestore=false;

    public void initialize(AddettoASController asControlle, String identificativoPrenotazione) {
        this.asController=asControlle;
        this.idPrenotazione=identificativoPrenotazione;
        ombrelloniDisponibii.setText(asController.mostraOmbrelloniLiberi(idPrenotazione));
    }

    public void initialize(DefaultGestoreController gestoreController,String idPrenotazione){
        this.gestoreController=gestoreController;
        this.idPrenotazione=idPrenotazione;
        this.flagGestore=true;
        ombrelloniDisponibii.setText(gestoreController.mostraOmbrelloniLiberi(idPrenotazione));
    }
    public void handleAggiungiOmbrellone(ActionEvent actionEvent) {
        if(!ombrelloneSelezionato.getText().equals("")) {
            listaOmbrelloni.add(ombrelloneSelezionato.getText());
            ombrelloneSelezionato.setText("");
        }
    }

    public void handleCOnfermaLeModifiche(ActionEvent actionEvent) {
        if(!flagGestore) {
            if (!listaOmbrelloni.isEmpty()) {
                asController.inserimentoOmbrelloni(idPrenotazione, listaOmbrelloni);
            }
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        }else{
            if (!listaOmbrelloni.isEmpty()) {
                gestoreController.inserimentoOmbrelloni(idPrenotazione, listaOmbrelloni);
            }
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        }
    }
}
