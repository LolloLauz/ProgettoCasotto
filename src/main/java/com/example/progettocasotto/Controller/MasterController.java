package com.example.progettocasotto.Controller;

/**
 * Interfaccia che definisce il controller principale della spiaggia.
 */
public interface MasterController {

    /**
     * Metodo usato per creare uno chalet.
     * @param nome il nome dello chalet.
     * @return true se lo chalet &egrave; stato creato correttamente, false altrimenti.
     */
    boolean creaChalet(String nome);

    /**
     * Metodo usato per la creazione di una spiaggia.
     * @param nome il nome della spiaggia.
     * @return true se la spiaggia &egrave; stata creata correttamente, false altrimenti.
     */
    boolean creaSpiaggia(String nome);

    /**
     * Metodo usato per la creazione di un bar.
     * @param nome il nome del bar
     * @return true se il bar &egrave; stato creato correttamente, false altrimenti.
     */
    boolean creaBar(String nome);
}
