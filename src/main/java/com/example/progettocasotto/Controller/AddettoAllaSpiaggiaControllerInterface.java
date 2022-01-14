package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.Privilegio;

import java.util.Date;

public interface AddettoAllaSpiaggiaControllerInterface {
    boolean creaUtente(String nome, String cognome, String email, String password, Privilegio privilegio);

    void selezionaPeriodo(Date dataInizio, Date dataFine);

    void slectOmbrellone(String ombrellone);

    void selectSdraio(int numSdraio);

    void insertPeriodo(Date dateInizio, Date dataFine);

    void removePrenotazione(String idPrenotazione);

    void convalidaPagamento(String idPrenotazione);

}
