package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultUserController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
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

public class SelectOmbrellone {

    @FXML
    private Label displayOmbrelloniLiberi;
    @FXML
    private TextField ombrellone;
    @FXML
    private Button ombrelloneSelezionato;
    @FXML
    private DatePicker dataFine;
    @FXML
    private DatePicker dataInizio;

    DefaultUserController userController;


    public void handleOmbrelloneSelezionato(ActionEvent actionEvent) {
            userController.selectOmbrellone(ombrellone.getText());
    }

    public void initialize(DefaultUserController userController) {
        this.userController=userController;
    }

    public void handleMostraOmbrelloniLiberi(ActionEvent actionEvent) {
        LocalDate dataIn = dataInizio.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(dataIn.getYear(), dataIn.getMonthValue(), dataIn.getDayOfMonth());
        Date dataInizio = c.getTime();
        LocalDate dataFin = dataFine.getValue();
        c.set(dataFin.getYear(), dataFin.getMonthValue(), dataFin.getDayOfMonth());
        Date dataFine = c.getTime();
        userController.mostraOmbrelloniLiberi(dataInizio,dataFine);
        displayOmbrelloniLiberi.setText(userController.getListOmbrelloniLiberi(dataInizio,dataFine));
        userController.prenotaOmbrellone(dataInizio,dataFine);
    }

    public void handleConfermaPrenotazione(ActionEvent actionEvent) {
        userController.confermaPernotazione();
        userController.caricaPrenotazione();
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
