package com.example.progettocasotto.Model.Spiaggia;

import java.util.ArrayList;
import java.util.Date;

public class DefaultPrenotazione implements PrenotazioneInterface{
    private String ID;
    private Date dataInizio;
    private Date dataFine;
    private String idUtenteAssociato;
    private StatoPreOrd statoPrenotazione;
    ArrayList<Ombrellone> listaOmbrelloni=new ArrayList<>();
    ArrayList<Sdraio> listaSdraio;

    public DefaultPrenotazione(String ID, Date dataInizio, Date dataFine) {
        this.idUtenteAssociato="";
        this.ID = ID;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        listaSdraio=new ArrayList<>();
        statoPrenotazione= StatoPreOrd.IN_ATTESA_DI_PAGAMENTO;
    }

    public void setID(String ID) {
        this.ID = ID;
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
        this.dataInizio=dataInizio;
        this.dataFine=dataFine;
    }

    public String getIdUtenteAssociato() {
        return idUtenteAssociato;
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
    public String stampaOmbrelloni(){
        String result="";
        if(!listaOmbrelloni.isEmpty()) {
            System.out.println("\ngli ombrelloni prenotati sono: ");
            result=result+"\ngli ombrelloni prenotati sono:";
            for (Ombrellone ombrellone : listaOmbrelloni) {
                System.out.print("|"+ombrellone.getID()+"|");
                result=result+"\n "+ombrellone.getID();
            }
        }
        return result;
    }

    public String stampaSdraio(){
        String result="";
        if(!listaSdraio.isEmpty()) {
            System.out.println("\nle sdraio prenotati sono:");
            result=result+"\nle sdraio prenotati sono:";
            for (Sdraio sdraio : listaSdraio) {
                result=result+"\n"+sdraio.getID();
                System.out.print("|"+sdraio.getID()+"|");
            }
        }
        return result;
    }
    public void removeOmbrelloni(){
        listaOmbrelloni.clear();
    }

    public void setStatoPrenotazione(StatoPreOrd statoPrenotazione) {
        this.statoPrenotazione = statoPrenotazione;
    }

    public void setIdUtenteAssociato(String nome){
        this.idUtenteAssociato=nome;
    }

    public void removeSdraio() {
        listaSdraio.clear();
    }

}
