package com.example.progettocasotto.Model.Utenti;

import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;

/**
 * Interfaccia che definisce un cliente.
 */
public interface ClienteInterface {

    /**
     * Metodo usato per aggiungere una prenotazione ad un cliente.
     * @param prenotazione
     * @return true se l'aggiunta è avvenuta con successo, false altrimenti.
     */
    boolean addPrenotazioneToCliente(DefaultPrenotazione prenotazione);
}
