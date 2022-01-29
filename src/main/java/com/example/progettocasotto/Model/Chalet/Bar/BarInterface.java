package com.example.progettocasotto.Model.Chalet.Bar;

/**
 * Interfaccia che definisce il bar dello chalet.
 */
public interface BarInterface {

    /**
     * Metodo usato per selezionare una bevanda da un'ordinazione.
     * @param idOrdinazione l'id dell'ordinazione.
     * @param bevanda la bevanda da selezionare.
     * @param quantita la quantit&agrave; della bevanda da selezionare.
     * @return true se la bevanda è stata selezionata correttamente, false altrimenti.
     */
    boolean selezionaBevanda(String idOrdinazione,String bevanda,int quantita);

    /**
     * Metodo usato per creare una nuova ordinazione.
     * @param idOrdinazione l'id dell'ordinazione.
     * @return true se l'ordinazione è stata creata con successo, false altrimenti.
     */
    boolean creaOrdinazione(String idOrdinazione);

}
