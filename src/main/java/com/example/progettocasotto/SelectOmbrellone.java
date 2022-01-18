package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class SelectOmbrellone {
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
        LocalDate dataIn = dataInizio.getValue();
        Calendar c =  Calendar.getInstance();
        c.set(dataIn.getYear(), dataIn.getMonthValue() - 1, dataIn.getDayOfMonth());
        Date dataInizio = c.getTime();

        LocalDate dataFin = dataFine.getValue();
        c.set(dataIn.getYear(), dataIn.getMonthValue() - 1, dataIn.getDayOfMonth());
        Date dataFine = c.getTime();

        System.out.println(dataInizio.getTime()+" data fine "+ dataFine.getTime());

    }

    public void initialize() {


    }

}
