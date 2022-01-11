package com.example.progettocasotto.Controller;

import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;

import java.util.Date;

public class DefaultUserController implements UserContrllerInterface{
    DefaultSpiaggia spiaggia;

    public DefaultUserController(DefaultSpiaggia spiaggia) {
        this.spiaggia = spiaggia;
    }

    public void prenotaOmbrellone(Date dataInizio, Date dataFine){

        spiaggia.addPrenotazione("prova",dataInizio,dataFine);
        spiaggia.stampaOmbrelloniLiberi(dataInizio,dataFine);
        Ombrellone ombrellone=new Ombrellone("1");
        spiaggia.addOmbrelloneToPrenotazione("prova",ombrellone);
        spiaggia.stampaOmbrelloniLiberi(dataInizio,dataFine);
    }

    public void StampaSpiaggia(){
        spiaggia.stampaPrenotazioni();
    }
}
