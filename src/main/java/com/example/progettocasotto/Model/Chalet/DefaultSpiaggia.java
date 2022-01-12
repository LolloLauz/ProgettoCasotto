package com.example.progettocasotto.Model.Chalet;

import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Spiaggia.Sdraio;
import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;

import java.util.ArrayList;
import java.util.Date;

public class DefaultSpiaggia implements SpiaggiaInterface{
    private String nomeSpiaggia;
    private ArrayList<DefaultPrenotazione> listaPrenotazioni;
    private ArrayList<Ombrellone> listaOmbrelloni;
    private ArrayList<Sdraio> listaSdraio;
    private ArrayList<DefaultAttivita> listaAttivita;

    public DefaultSpiaggia(String nomeSpiaggia) {
        this.nomeSpiaggia = nomeSpiaggia;
        listaOmbrelloni=new ArrayList<>();
        listaSdraio=new ArrayList<>();
        listaPrenotazioni=new ArrayList<>();
        listaAttivita=new ArrayList<>();
    }

    public String getNomeSpiaggia() {
        return nomeSpiaggia;
    }

    @Override
    public boolean addPrenotazione(String id, Date dataInizio, Date dataFine) {
        return listaPrenotazioni.add(new DefaultPrenotazione(id,dataInizio,dataFine));
    }

    @Override
    public boolean addOmbrelloneToPrenotazione(String idPrenotazione, Ombrellone ombrellone) {

        if(getPrenotaizioneById(idPrenotazione).getListaOmbrelloni().contains(ombrellone)){
            return false;
        }
        if(listaOmbrelloni.contains(ombrellone)) {
            return getPrenotaizioneById(idPrenotazione).addOmbrellone(ombrellone);
        }
        return false;
    }

    @Override
    public boolean addSdraioToPrenotazione(String idPrenotazione, Sdraio sdraio) {
        if(getPrenotaizioneById(idPrenotazione).getListaSdraio().contains(sdraio)){
            return false;
        }
        return getPrenotaizioneById(idPrenotazione).getListaSdraio().add(sdraio);
    }


    @Override
    public ArrayList<Ombrellone> getOmbrelloniOccupati(Date dataInizio, Date dataFine) {
        ArrayList<Ombrellone> ombrelloniOccupati=new ArrayList<>();
        for(DefaultPrenotazione prenotazione:listaPrenotazioni){
            if(prenotazione.getStatoPrenotazione()== StatoPreOrd.PAGATA){
                if((prenotazione.getDataInizio().equals(dataInizio) && prenotazione.getDataFine().equals(dataFine))||
                        ( prenotazione.getDataInizio().before(dataInizio) && dataInizio.before(prenotazione.getDataFine())) ||
                        (prenotazione.getDataInizio().before(dataFine) && dataFine.before(prenotazione.getDataFine()) ||
                                (dataInizio.before(prenotazione.getDataInizio()) && prenotazione.getDataInizio().before(dataFine))||
                                (dataFine.before(prenotazione.getDataFine()) && prenotazione.getDataFine().before(dataFine)))){
                    ombrelloniOccupati.addAll(prenotazione.getListaOmbrelloni());

                }
            }
        }
        return ombrelloniOccupati;
    }

    @Override
    public ArrayList<Ombrellone> getOmbrelloniLiberi(Date dataInizio, Date dataFine) {
        ArrayList<Ombrellone> ombrelloniLiberi=new ArrayList<>();
        ombrelloniLiberi.addAll(listaOmbrelloni);
        for(Ombrellone ombrellone:getOmbrelloniOccupati(dataInizio,dataFine)){
            ombrelloniLiberi.removeIf(ombrellone2 -> ombrellone2.getID().equals(ombrellone.getID()));
        }
        return ombrelloniLiberi;
    }

    @Override
    public ArrayList<Sdraio> getSdraioLiberi(Date dataInizio, Date dataFine) {
        ArrayList<Sdraio> sdraioLiberi=new ArrayList<>();
        sdraioLiberi.addAll(listaSdraio);
        for(Sdraio sdraio:getSdraioOccupato(dataInizio,dataFine)){
            sdraioLiberi.removeIf(sdraio2 -> sdraio2.getID().equals(sdraio.getID()));
        }
        return sdraioLiberi;
    }

    @Override
    public ArrayList<Sdraio> getSdraioOccupato(Date dataInizio, Date dataFine) {
        ArrayList<Sdraio> sdraioOccupati=new ArrayList<>();
        for(DefaultPrenotazione prenotazione:listaPrenotazioni){
            if(prenotazione.getStatoPrenotazione()== StatoPreOrd.PAGATA){
                if((prenotazione.getDataInizio().before(dataInizio) && dataInizio.before(prenotazione.getDataFine())) ||
                        (prenotazione.getDataInizio().before(dataFine) && dataFine.before(prenotazione.getDataFine()) ||
                                (dataInizio.before(prenotazione.getDataInizio()) && prenotazione.getDataInizio().before(dataFine))||
                                (dataFine.before(prenotazione.getDataFine()) && prenotazione.getDataFine().before(dataFine)))){
                    sdraioOccupati.addAll(prenotazione.getListaSdraio());

                }
            }
        }
        return sdraioOccupati;
    }

    public DefaultPrenotazione getPrenotaizioneById(String id){
        for(DefaultPrenotazione prenotazione:listaPrenotazioni){
            if(prenotazione.getID().equals(id)){
                return  prenotazione;
            }
        }
        return null;
    }
    @Override
    public void setNumeroSdraio(int numSdraio) {
        for(int i=0;i<numSdraio;i++){
            listaSdraio.add(new Sdraio());
        }
    }

    @Override
    public void setNumeroOmbrelloni(int numOmbrelloni) {
        for(int i=0;i<numOmbrelloni;i++){
            listaOmbrelloni.add(new Ombrellone(Integer.toString(i)));
        }
    }

    public void stampaPrenotazioni(){
        for(DefaultPrenotazione prenotazione:listaPrenotazioni){
            System.out.println("la prentoazione :"+ prenotazione.getID()+" Data inizio "+prenotazione.getDataInizio().toString()+" DataFine "+prenotazione.getDataFine().toString());
            prenotazione.stampaOmbrelloni();
        }
    }
    public void stampaOmbrelloniLiberi(Date dataInizo, Date dataFine){
        for(Ombrellone ombrellone:getOmbrelloniLiberi(dataInizo,dataFine)){
            System.out.println(ombrellone.getID());
        }
    }
}
