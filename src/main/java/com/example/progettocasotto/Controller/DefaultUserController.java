package com.example.progettocasotto.Controller;


import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultOrdinazione;
import com.example.progettocasotto.Model.Chalet.DefaultAttivita;
import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DefaultUserController implements UserContrllerInterface{
    private DefaultCliente currentCliente;
    private ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
    private DefaultPrenotazione prenotazione;
    private DefaultOrdinazione ordinazione;
    private DefaultaMasterController masterController;

    public DefaultUserController(DefaultaMasterController masterController) {
        this.masterController=masterController;
        checkPrenotazioneCurrentCliente();
    }

    private void checkPrenotazioneCurrentCliente() {
        for (DefaultPrenotazione prenotazione:masterController.getChalet().getSpiaggia().getListaPrenotazioni()){
            System.out.println(prenotazione.getID());
        }
        if(!masterController.getChalet().getSpiaggia().getListaPrenotazioni().isEmpty()) {
            if (masterController.getChalet().getSpiaggia().getListaPrenotazioni().containsAll(currentCliente.getPrenotazioniAssociate())) {
                System.out.println("le prenotazioni del cliente sono in pari con quelle della spiaggia");
            } else {
                currentCliente.getPrenotazioniAssociate().removeIf(prenotazione -> !masterController.getChalet().getSpiaggia().getListaPrenotazioni().contains(prenotazione));
            }
        }
    }

    public void prenotaOmbrellone(Date dataInizio, Date dataFine){
        Random random=new Random();
        String nomePrenotazione=String.valueOf(random.nextInt());
        prenotazione=new DefaultPrenotazione(nomePrenotazione,dataInizio,dataFine);
        prenotazione.setIdUtenteAssociato(currentCliente.getID());
        masterController.getChalet().getSpiaggia().addPrenotazione(prenotazione.getID(),dataInizio,dataFine);
    }

    public void mostraOmbrelloniLiberi(Date dataInizio, Date dataFine) {
        masterController.getChalet().getSpiaggia().stampaOmbrelloniLiberi(dataInizio,dataFine);
    }

    public void mostraSdraioLiberi(Date dataInizio, Date dataFine){
        masterController.getChalet().getSpiaggia().stampaSdraioLiberi(dataInizio,dataFine);
    }

    public void selectOmbrellone(String numeroOmbrellone){
        if(numeroOmbrellone!=null) {
            masterController.getChalet().getSpiaggia().addOmbrelloneToPrenotazione(prenotazione.getID(), new Ombrellone(numeroOmbrellone));
        }
    }

    public void ordinaBibita(String bevanda,int quantita){
        Random random=new Random();
        String nomeOrdinazione=String.valueOf(random.nextInt());
        masterController.getChalet().getBar().creaOrdinazione(nomeOrdinazione);
        masterController.getChalet().getBar().selezionaBevanda(nomeOrdinazione,bevanda,quantita);
        this.ordinazione=masterController.getChalet().getBar().getOrdinazioneById(nomeOrdinazione);

    }

    public boolean setCurrentClient(String nomeCliente) {
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

    public boolean prenotaSdraio(Date dataInizio, Date dataFine){
        Random random=new Random();
        String nomePrenotazione=String.valueOf(random.nextInt());
        prenotazione=new DefaultPrenotazione(nomePrenotazione,dataInizio,dataFine);
        masterController.getChalet().getSpiaggia().addPrenotazione(prenotazione.getID(),dataInizio,dataFine);
        return true;
    }
    public boolean selectNumSdraio(int numSdraio) {
        if(masterController.getChalet().getSpiaggia().getSdraioLiberi(prenotazione.getDataInizio(),prenotazione.getDataFine()).isEmpty()){
            System.out.println("non ci sono sdraio disponibili");
            return false;
        }
        return masterController.getChalet().getSpiaggia().addSdraioToPrenotazione(prenotazione.getID(),numSdraio);
    }

    public DefaultPrenotazione getCurrentPrenotazione() {
        return prenotazione;
    }
    public DefaultSpiaggia getChalet(){
        return masterController.getChalet().getSpiaggia();
    }

    public void stampaMenu() {
        for(Bevanda bevanda:masterController.getChalet().getBar().getListaBevande()) {
            System.out.println("|"+bevanda.getNome()+"     "+bevanda.getDescrizione()+"|");
        }
    }

    public void getOrdinazione() {
        ordinazione.stampaOrdinazione();
    }

    public boolean prenotaAttivita(String nomeAttivita, int numPersone) {

        return masterController.addPartecipantiToAttivita(nomeAttivita,numPersone);
    }

    public void getListaAttivita() {
        for(DefaultAttivita attivita:masterController.getChalet().getListaAttivita()){
            System.out.println(attivita.getNome()+" data inizio "+ attivita.getDataInizio().toString()+" data fine "+attivita.getDataFine().toString()+" numero posti disponibili "+attivita.getNumeroPosti());
        }
    }

    public boolean prenotaAttivita(String nomeAttivita) {
        for(DefaultAttivita attivita:masterController.getChalet().getListaAttivita()){
            if(attivita.getNome().equals(nomeAttivita)){
                if(attivita.getNumeroPosti()<=0){
                    System.out.println("Posti esauriti");
                    return false;
                }else {
                    System.out.println("il numero di posti rimanenti e'" + attivita.getNumeroPosti());
                    return true;
                }
            }
        }
        return false;
    }
}
