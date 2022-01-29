package com.example.progettocasotto.Model.Chalet;

/**
 * Interfaccia che definisce un'attivit&agrave;
 */
public interface AttivitaInterface {

    /**
     * Metodo usato per decrementare i posti di un'attivit&agrave;.
     * @param numPersone il numero di persone da decrementare all'attività.
     * @return true se il numero dei posti è maggiore al numero delle persone, false altrimenti.
     */
    boolean decrementaPosti(int numPersone);
}
