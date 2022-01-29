package com.example.progettocasotto.Model.Chalet;

import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;
import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;

/**
 * Interfaccia che definisce lo chalet.
 */
public interface ChaletInterface {

    /**
     * Metodo usato per aggiungere una spiaggia.
     * @param spiaggia il nome della spiaggia.
     */
    void addSpiaggia(String spiaggia);

    /**
     * Metodo usato per aggiungere un bar allo chalet.
     * @param bar il bar da aggiungere allo chalet.
     */
    void addBar(DefaultBar bar);
}
