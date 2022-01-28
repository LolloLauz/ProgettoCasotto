package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultGestoreController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CreaAttivitaView {

    public TextField nomeAttivita;
    public TextField numeroPosti;
    public TextArea descrizioneAttivita;
    public TextField luogo;
    public DatePicker dataInizio;
    public DatePicker dataFine;
    private DefaultGestoreController gestoreController;

    public void initialize(DefaultGestoreController gestoreController) {
            this.gestoreController=gestoreController;
    }

    public void handleCreaAttivita(ActionEvent actionEvent) {
        LocalDate dataIn = dataInizio.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(dataIn.getYear(), dataIn.getMonthValue()-1, dataIn.getDayOfMonth());
        Date dataInizio = c.getTime();
        LocalDate dataFin = dataFine.getValue();
        c.set(dataFin.getYear(), dataFin.getMonthValue()-1, dataFin.getDayOfMonth());
        Date dataFine = c.getTime();
        gestoreController.creaAttivita(nomeAttivita.getText(),luogo.getText(),descrizioneAttivita.getText(),dataInizio,dataFine,Integer.parseInt(numeroPosti.getText()));
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
