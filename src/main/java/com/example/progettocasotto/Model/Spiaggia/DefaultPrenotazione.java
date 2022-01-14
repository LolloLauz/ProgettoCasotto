package com.example.progettocasotto.Model.Spiaggia;

import java.util.ArrayList;
import java.util.Date;

public class DefaultPrenotazione implements PrenotazioneInterface{
    private String ID;
    private Date dataInizio;
    private Date dataFine;
    private StatoPreOrd statoPrenotazione;
    ArrayList<Ombrellone> listaOmbrelloni=new ArrayList<>();
    ArrayList<Sdraio> listaSdraio;

    public DefaultPrenotazione(String ID, Date dataInizio, Date dataFine) {
        this.ID = ID;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        listaOmbrelloni=new ArrayList<>();
        listaSdraio=new ArrayList<>();
        statoPrenotazione= StatoPreOrd.PAGATA;
    }

    @Override
    public boolean addOmbrellone(Ombrellone ombrellone) {
        return listaOmbrelloni.add(ombrellone);
    }

    @Override
    public boolean addSdraio(Sdraio sdraio) {
        return false;
    }

    @Override
    public void setPeriodo(Date dataInizio, Date dataFine) {

    }

    @Override
    public StatoPreOrd getStatoPrenotazione() {
        return statoPrenotazione;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public ArrayList<Ombrellone> getListaOmbrelloni() {
        return listaOmbrelloni;
    }

    public ArrayList<Sdraio> getListaSdraio() {
        return listaSdraio;
    }

    public String getID() {
        return ID;
    }
    public void stampaOmbrelloni(){
        for(Ombrellone ombrellone:listaOmbrelloni){
            System.out.println(ombrellone.getID());
        }
    }

    public void stampaSdraio(){
        for(Sdraio sdraio:listaSdraio){
            System.out.println(sdraio.getID());
        }
    }

}
