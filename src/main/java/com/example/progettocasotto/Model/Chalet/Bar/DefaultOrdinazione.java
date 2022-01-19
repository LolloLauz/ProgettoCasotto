package com.example.progettocasotto.Model.Chalet.Bar;

import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;

import java.util.*;

public class DefaultOrdinazione implements OrdinazioneInterface {

    private String ID;

    private Map<Bevanda,Integer> mappaBevande=new HashMap<>();
    private StatoPreOrd statoOrdinazione;

    public DefaultOrdinazione(String ID) {
        this.ID = ID;
        statoOrdinazione=StatoPreOrd.PAGATA;
    }

    public String getID() {
        return ID;
    }

    public Set<Bevanda> getListaBevande() {
        return mappaBevande.keySet();
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
}
