package com.example.progettocasotto.Controller;

import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DefaultUserController implements UserContrllerInterface{
    DefaultSpiaggia spiaggia;
    DefaultCliente currentCliente;
    ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
    DefaultPrenotazione prenotazione;

    public DefaultUserController(DefaultSpiaggia spiaggia) {
        this.spiaggia = spiaggia;
    }

    public void prenotaOmbrellone(Date dataInizio, Date dataFine){
        Random random=new Random();
        String nomePrenotazione=String.valueOf(random.nextInt());
        prenotazione=new DefaultPrenotazione(nomePrenotazione,dataInizio,dataFine);
        System.out.println(spiaggia.addPrenotazione(prenotazione.getID(),dataInizio,dataFine));
    }

    public void mostraStatoSpiaggia(Date dataInizio, Date dataFine) {
        spiaggia.stampaOmbrelloniLiberi(dataInizio,dataFine);
    }

    public void selectOmbrellone(String numeroOmbrellone){
        if(numeroOmbrellone!=null) {
            spiaggia.addOmbrelloneToPrenotazione(prenotazione.getID(), new Ombrellone(numeroOmbrellone));
        }
    }


    public boolean setCurrentClient(String nomeCliente) {
        System.out.println(listaClienti.size());
        for(DefaultCliente defaultCliente: listaClienti){
            if(defaultCliente.getID().equals(nomeCliente)){
                currentCliente=defaultCliente;
                return true;
            }
        }
        return false;
    }

    public void confermaPernotazione() {
        currentCliente.addPrenotazioneToCliente(prenotazione);
    }

    public boolean setlistaClienti(ArrayList<DefaultCliente> listaClienti) {
        return this.listaClienti.addAll(listaClienti);
    }
}
