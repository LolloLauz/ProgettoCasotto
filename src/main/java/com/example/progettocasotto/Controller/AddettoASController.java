package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.Privilegio;
import javafx.css.StyleConverter;

import java.util.Date;

public class AddettoASController implements AddettoAllaSpiaggiaControllerInterface {

    DefaultaMasterController masterController;

    public AddettoASController(DefaultaMasterController masterController) {
        this.masterController = masterController;
    }

    @Override
    public boolean creaUtente(String nome, String cognome, String email, String password, Privilegio pri) {
        String privilegio = pri.name();
        return masterController.getGestoreDB().insertUtente(nome, cognome, email, password, privilegio);
    }

    @Override
    public void selezionaPeriodo(Date dataInizio, Date dataFine) {

    }

    @Override
    public void slectOmbrellone(String ombrellone) {

    }

    @Override
    public void selectSdraio(int numSdraio) {

    }

    @Override
    public void insertPeriodo(Date dateInizio, Date dataFine) {

    }

    @Override
    public void removePrenotazione(String idPrenotazione) {

    }

    @Override
    public void convalidaPagamento(String idPrenotazione) {

    }
}
