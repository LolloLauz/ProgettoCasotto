package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultGestoreController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AggiungiSdraioView {

    public Label numeroSdraioDisponibili;
    public Button aggiungiSdraio;
    public TextField numeroSdraio;
    private AddettoASController asController;
    private String identificativoPrenotazione;
    boolean flag=true;
    private DefaultGestoreController gestoreController;
    private boolean flagGestore=false;

    public void handleAggiungiSdraio(ActionEvent actionEvent) {
        if(!flagGestore) {
            if (flag) {
                flag = false;
                asController.inserimentoSdraio(identificativoPrenotazione, Integer.parseInt(numeroSdraio.getText()));
                numeroSdraio.setText("");
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
        }else{
            if (flag) {
                flag = false;
                gestoreController.inserimentoSdraio(identificativoPrenotazione, Integer.parseInt(numeroSdraio.getText()));
                numeroSdraio.setText("");
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
        }
    }

    public void initialize(AddettoASController asControlle, String idPrenotazione) {
        this.asController=asControlle;
        this.identificativoPrenotazione=idPrenotazione;
        numeroSdraioDisponibili.setText("numero sdraio liberi: "+asController.mostraSdraioDisponibili(identificativoPrenotazione));
    }

    public void initialize(DefaultGestoreController gestoreController,String idPrenotazione){
        this.gestoreController=gestoreController;
        this.identificativoPrenotazione=idPrenotazione;
        this.flagGestore=true;
        numeroSdraioDisponibili.setText("numero sdraio liberi: "+gestoreController.mostraSdraioDisponibili(identificativoPrenotazione));
    }
}
