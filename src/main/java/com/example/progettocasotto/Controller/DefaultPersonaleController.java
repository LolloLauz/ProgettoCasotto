package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.Privilegio;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Spiaggia.Sdraio;
import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;

import java.util.ArrayList;
import java.util.Date;

public abstract class DefaultPersonaleController implements PersonaleInterface{
    DefaultaMasterController masterController;

    public DefaultPersonaleController(DefaultaMasterController masterController) {
        this.masterController = masterController;
    }

    @Override
    public boolean creaUtente(String nome, String cognome, String email, String password, Privilegio pri) {
        String privilegio = pri.name();
        return masterController.getGestoreDB().insertUtente(nome, cognome, email, password, privilegio);
    }

    private ArrayList<Ombrellone> selezionaPeriodoOmbrelloni(Date dataInizio, Date dataFine) {

        return masterController.getChalet().getSpiaggia().getOmbrelloniLiberi(dataInizio,dataFine);
    }

    private ArrayList<Sdraio> selezionaPeriodoSdraio(Date dataInizio,Date dataFine){
        return masterController.getChalet().getSpiaggia().getSdraioLiberi(dataInizio,dataFine);
    }


    private Ombrellone  selectOmbrellone(String ombrellone) {
        return new Ombrellone(ombrellone);
    }



    @Override
    public void removePrenotazione(String idPrenotazione) {
        masterController.getChalet().getSpiaggia().removePrenotazione(idPrenotazione);
    }

    @Override
    public void convalidaPagamento(String idPrenotazione) {
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).setStatoPrenotazione(StatoPreOrd.PAGATA);
    }

    public void modificaPrenotazione(String idPrenotazione,Date dataInizio, Date dataFine,ArrayList<String> ombrelloni,int numSdraio){
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).removeOmbrelloni();
        for(String nome:ombrelloni){
            masterController.getChalet().getSpiaggia().addOmbrelloneToPrenotazione(idPrenotazione,selectOmbrellone(nome));
        }
        if(numSdraio==0){
            return;
        }
        masterController.getChalet().getSpiaggia().addSdraioToPrenotazione(idPrenotazione,numSdraio);

    }
    public boolean modificaPeriodoPrenotazione(String prenotazione,Date dataInizio,Date dataFine){
        for(Ombrellone ombrellone:masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione).getListaOmbrelloni()){
            if(!selezionaPeriodoOmbrelloni(dataInizio,dataFine).contains(ombrellone)){
                return false;
            }
        }
        for(Sdraio sdraio:masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione).getListaSdraio()){
            if(!selezionaPeriodoSdraio(dataInizio,dataFine).contains(sdraio)){
                return false;
            }
        }
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione).setPeriodo(dataInizio,dataFine);
        return true;
    }

    public void getPrenotazioniCliete(String nomeCliente) {
        for(DefaultPrenotazione prenotazione:masterController.getChalet().getSpiaggia().getPrenotazioniCliente(nomeCliente)){
            System.out.println("numero della prenotazione :"+prenotazione.getID()+"" +
                    "\ndata inizio: "+ prenotazione.getDataInizio()+" dataFine:"+prenotazione.getDataFine());
            if(!prenotazione.getListaOmbrelloni().isEmpty()){
                for(Ombrellone ombrellone:prenotazione.getListaOmbrelloni()){
                    System.out.println("numero dell'ombrellone prenotato: "+ ombrellone.getID());
                }
            }
            if(!prenotazione.getListaSdraio().isEmpty()){
                System.out.println("numero sdraio affittati : "+prenotazione.getListaSdraio().size());
            }
        }

    }

    public void inserimentoSdraio(String idPrenotazione, int numSdraio) {
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).removeSdraio();
        masterController.getChalet().getSpiaggia().addSdraioToPrenotazione(idPrenotazione,numSdraio);
    }

    public void inserimentoOmbrelloni(String idPrenotazione, ArrayList<String> listaOmbrelloni) {
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).removeOmbrelloni();
        for(String numeroOmbrellone:listaOmbrelloni) {
            masterController.getChalet().getSpiaggia().addOmbrelloneToPrenotazione(idPrenotazione,new Ombrellone(numeroOmbrellone));
        }
    }
}
