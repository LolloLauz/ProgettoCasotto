package com.example.progettocasotto.Model.Chalet.Bar;

import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;
import org.controlsfx.property.BeanProperty;

import java.util.*;

public class DefaultOrdinazione implements OrdinazioneInterface {

    private String ID;

    private HashMap<Bevanda,Integer> listaBevandaMappa;
    private StatoPreOrd statoOrdinazione;

    public DefaultOrdinazione(String ID) {
        this.ID = ID;
        listaBevandaMappa=new HashMap<>();
        statoOrdinazione=StatoPreOrd.PAGATA;
    }

    public String getID() {
        return ID;
    }

    public Set<Bevanda> getListaBevande() {
        return listaBevandaMappa.keySet();
    }

    @Override
    public void addBevanda(Bevanda bevanda,int quantitaOrdinata) {
        listaBevandaMappa.put(bevanda,quantitaOrdinata);
    }

    public Bevanda getBevandaById(String nome){
        for(Bevanda bevanda:listaBevandaMappa.keySet()){
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

    public HashMap<Bevanda, Integer> getListaBevandaMappa() {
        return listaBevandaMappa;
    }
}
