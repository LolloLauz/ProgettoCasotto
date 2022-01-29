package com.example.progettocasotto.Model.Chalet.Bar;

import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;

/**
 * Interfaccia che definisce un'ordinazione.
 */
public interface OrdinazioneInterface {

    /**
     * Metodo usato per aggiungere una bevanda all'ordinazione.
     * @param bevanda la bevanda da aggiungere.
     * @param quantita la quantit&agrave; della bevanda da aggiungere.
     */
    void addBevanda(Bevanda bevanda,int quantita);
}
