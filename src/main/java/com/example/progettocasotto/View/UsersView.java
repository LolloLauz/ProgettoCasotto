package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultUserController;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import static java.text.DateFormat.getDateInstance;

public class UsersView {
   DefaultUserController userController;

    public UsersView(DefaultUserController userController) {
        this.userController = userController;
        provaMetodi();
    }
    

    public void provaMetodi(){
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            dataInizio= dateFormat.parse("10/06/2020");
            dataFine=dateFormat.parse("12/06/2020");
            userController.prenotaOmbrellone(dataInizio,dataFine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userController.StampaSpiaggia();
    }
}
