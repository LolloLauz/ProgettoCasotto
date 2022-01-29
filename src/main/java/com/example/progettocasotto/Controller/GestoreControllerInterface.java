package com.example.progettocasotto.Controller;

import java.util.Date;

/**
 * Interfaccia che definisce il controller per il gestore dello chalet.
 */
public interface GestoreControllerInterface {

    /**
     * Metodo usato per creare uno chalet.
     * @param nome il nome dello chalet.
     * @return true se lo chalet è stato creato correttamente, false altrimenti.
     */
    boolean creaChalet(String nome);

    /**
     * Metodo usato per la creazione di una spiaggia.
     * @param spiaggia il nome della spiaggia.
     * @return true se la spiaggia è stata creata correttamente, false altrimenti.
     */
    boolean creaSpiaggia(String spiaggia);

    /**
     * Metodo usato per la creazione di un bar.
     * @param bar il nome del bar
     * @return true se il bar è stato creato correttamente, false altrimenti.
     */
    boolean creaBar(String bar);

    /**
     * Metodo usato per impostare il numero di ombrelloni di una spiaggia.
     * @param numOmbrelloni il numero di ombrelloni.
     */
    void setNumOmbrelloni(int numOmbrelloni);

    /**
     * Metodo usato per impostare il numero di sdraio di una spiaggia.
     * @param numSdraio il numero di sdraio.
     */
    void setNumSdraio(int numSdraio);
    void creaAttivita(String nome, String luogo, String descrizione, Date dataInizio, Date dataFine,int numeroPosti);
}
