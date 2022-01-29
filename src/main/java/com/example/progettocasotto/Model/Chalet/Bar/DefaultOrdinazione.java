package com.example.progettocasotto.Model.Chalet.Bar;

import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;

import java.util.*;

public class DefaultOrdinazione implements OrdinazioneInterface {

    private String ID;
    private String qr_code;
    private StatoOrdinazione statoConsegna;

    private Map<Bevanda,Integer> mappaBevande=new HashMap<>();
    private StatoPreOrd statoOrdinazione;
    private String utenteAssociato;

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public StatoOrdinazione getStatoConsegna() {
        return statoConsegna;
    }

    public DefaultOrdinazione(String ID) {
        this.ID = ID;
        this.qr_code="";
        this.utenteAssociato="";
        statoOrdinazione=StatoPreOrd.IN_ATTESA_DI_PAGAMENTO;
        this.statoConsegna=StatoOrdinazione.NON_CONSEGNATO;
    }

    public String getID() {
        return ID;
    }

    public void setStatoOrdinazione(StatoPreOrd statoOrdinazione) {
        this.statoOrdinazione = statoOrdinazione;
    }

    public Set<Bevanda> getListaBevande() {
        return mappaBevande.keySet();
    }

    public void setUtenteAssociato(String utenteAssociato) {
        this.utenteAssociato = utenteAssociato;
    }

    public String getUtenteAssociato() {
        return utenteAssociato;
    }

    public StatoPreOrd getStatoOrdinazione() {
        return statoOrdinazione;
    }

    @Override
    public void addBevanda(Bevanda bevanda,int quantitaOrdinata) {
        this.mappaBevande.put(bevanda,quantitaOrdinata);

    }

    public Bevanda getBevandaById(String nome){
        for(Bevanda bevanda: mappaBevande.keySet()){
            if(bevanda.getNome().equals(nome)){
                return bevanda;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultOrdinazione that = (DefaultOrdinazione) o;
        return Objects.equals(ID, that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public Map<Bevanda, Integer> getMappaBevande() {
        return mappaBevande;
    }

    public void stampaOrdinazione() {
        for(Bevanda bevanda:getListaBevande()){
            System.out.println(bevanda.getNome()+" quantita "+ getMappaBevande().get(bevanda));
        }
    }
    public double getTotaleOrdinazione(){
        double totale=0;
        System.out.println("identificativo della prenotazione: "+getID());
        for(Bevanda bevanda:mappaBevande.keySet()){
            totale+=(mappaBevande.get(bevanda)* bevanda.getPrezzo());
            System.out.println("bevande :"+bevanda.getNome()+" quantita selezionata: "+mappaBevande.get(bevanda)+" prezzp :"+bevanda.getPrezzo());
        }
        System.out.println("il totale: "+ totale);
        return totale;
    }
    public int getQuantitaOrdinata(Bevanda bevanda){
        return mappaBevande.get(bevanda);
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
