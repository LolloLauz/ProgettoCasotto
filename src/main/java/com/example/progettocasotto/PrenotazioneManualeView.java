package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultGestoreController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PrenotazioneManualeView {
    public DatePicker dataInizioPrenotazione;
    public DatePicker dataFinePrenotazione;

    public TextArea mostraOmbrelloniLiberi;

    public Label numeroSdriao;
    public TextField inserisciOmbrelloni;
    public TextField inserisciNumeroSdraio;
    private int numeroSdrai=0;
    private AddettoASController asController;
    private ArrayList<String> ombrelloniSelzionati=new ArrayList<>();
    private String idCliente="";
    private boolean flagSdraio=true;
    private DefaultGestoreController gestoreController;
    private boolean flagGestore=false;

    public void handleAggiungiOmbrelloni(ActionEvent actionEvent) {
        ombrelloniSelzionati.add(inserisciOmbrelloni.getText());
    }

    public void handleAggiungiSdraio(ActionEvent actionEvent) {
        if(flagSdraio) {
            numeroSdrai = Integer.parseInt(inserisciNumeroSdraio.getText());
            flagSdraio=false;
        }
    }

    public void handleProdottiLiberi(ActionEvent actionEvent) {
        LocalDate dataIn = dataInizioPrenotazione.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(dataIn.getYear(), dataIn.getMonthValue() - 1, dataIn.getDayOfMonth());
        Date dataInizio = c.getTime();
        LocalDate dataFin = dataFinePrenotazione.getValue();
        c.set(dataFin.getYear(), dataFin.getMonthValue() - 1, dataFin.getDayOfMonth());
        Date dataFine = c.getTime();
        if(!flagGestore) {
            mostraOmbrelloniLiberi.setText(asController.getListaOmbrelloniLiberi(dataInizio, dataFine));
            numeroSdriao.setText("sono disponibili :" + asController.getListaSdraio(dataInizio, dataFine) + " sdraio");
        }else{
            mostraOmbrelloniLiberi.setText(gestoreController.getListaOmbrelloniLiberi(dataInizio, dataFine));
            numeroSdriao.setText("sono disponibili :" + gestoreController.getListaSdraio(dataInizio, dataFine) + " sdraio");
        }
    }

    public void initialize(AddettoASController asController, String nome) {
        this.asController=asController;
        this.idCliente=nome;
    }
    public void initialize(DefaultGestoreController gestoreController,String nome){
        this.gestoreController=gestoreController;
        this.idCliente=nome;
        this.flagGestore=true;
    }

    public void handleCreaPernotazione(ActionEvent actionEvent) {
        LocalDate dataIn = dataInizioPrenotazione.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(dataIn.getYear(), dataIn.getMonthValue() - 1, dataIn.getDayOfMonth());
        Date dataInizio = c.getTime();
        LocalDate dataFin = dataFinePrenotazione.getValue();
        c.set(dataFin.getYear(), dataFin.getMonthValue() - 1, dataFin.getDayOfMonth());
        Date dataFine = c.getTime();
        if(!flagGestore) {
            mostraOmbrelloniLiberi.setText(asController.getListaOmbrelloniLiberi(dataInizio, dataFine));
            numeroSdriao.setText("sono disponibili :" + asController.getListaSdraio(dataInizio, dataFine) + " sdraio");
            asController.prenotazioneManuale(idCliente, dataInizio, dataFine, ombrelloniSelzionati, numeroSdrai);
        }else {
            mostraOmbrelloniLiberi.setText(gestoreController.getListaOmbrelloniLiberi(dataInizio, dataFine));
            numeroSdriao.setText("sono disponibili :" + gestoreController.getListaSdraio(dataInizio, dataFine) + " sdraio");
            gestoreController.prenotazioneManuale(idCliente, dataInizio, dataFine, ombrelloniSelzionati, numeroSdrai);
        }
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
