package com.example.progettocasotto.Model.Chalet.Bar;

import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;

import java.util.ArrayList;

public class DefaultOrdinazione implements OrdinazioneInterface {

    private String ID;
    private ArrayList<Bevanda> listaBevande;
    private StatoPreOrd statoOrdinazione;

    @Override
    public boolean addBevanda(Bevanda bevanda) {
        return listaBevande.add(bevanda);
    }
}
