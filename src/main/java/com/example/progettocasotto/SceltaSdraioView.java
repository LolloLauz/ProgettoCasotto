package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class SceltaSdraioView {
    @FXML
    private DatePicker dataFine;
    @FXML
    private DatePicker dataInizio;
    @FXML
    private TextField sdraio;
    @FXML
    private Label displaySdraioLiberi;
    private DefaultUserController userController;
    boolean flagBottoneNumSdraio=true;
    public void handleMostraSdraioLiberi(ActionEvent actionEvent) {
        LocalDate dataIn = dataInizio.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(dataIn.getYear(), dataIn.getMonthValue() - 1, dataIn.getDayOfMonth());
        Date dataInizio = c.getTime();
        LocalDate dataFin = dataFine.getValue();
        c.set(dataFin.getYear(), dataIn.getMonthValue() - 1, dataIn.getDayOfMonth());
        Date dataFine = c.getTime();
        displaySdraioLiberi.setText(userController.getListSdraioLiberi(dataInizio,dataFine));
        userController.prenotaSdraio(dataInizio,dataFine);
    }

    public void handleSdraioSelezionato(ActionEvent actionEvent) {
        if(flagBottoneNumSdraio) {
            if (!sdraio.getText().equals("")) {
                userController.selectNumSdraio(Integer.parseInt(sdraio.getText()));
                sdraio.setText("");
                flagBottoneNumSdraio=false;
            }
        }
    }

    public void handleConfermaPrenotazione(ActionEvent actionEvent) {
        userController.confermaPernotazione();
        userController.caricaPrenotazione();
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void initialize(DefaultUserController userController) {
        this.userController=userController;
    }
}
