package com.example.progettocasotto.Model.Spiaggia;

import java.util.Date;

/**
 * Interfaccia che definisce una prenotazione.
 */
public interface PrenotazioneInterface {

    /**
     * Metodo usato per aggiungere un ombrellone alla prenotazione.
     * @param ombrellone l'ombrellone da aggiungere alla prenotazione.
     * @return true se l'ombrellone &egrave; stato aggiunto con successo, false altrimenti.
     */
    boolean addOmbrellone(Ombrellone ombrellone);

    /**
     * Metodo usato per aggiungere una sdraio alla prenotazione.
     * @param sdraio la sdraio da aggiungere alla prenotazione.
     * @return true se la sdraio &egrave; stata aggiunta con successo, false altrimenti.
     */
    boolean addSdraio(Sdraio sdraio);

    /**
     * Metodo usato per impostare il periodo di una prenotazione.
     * @param dataInizio la data di inizio della prenotazione.
     * @param dataFine la data di fine della prenotazione.
     */
    void setPeriodo(Date dataInizio, Date dataFine);

    /**
     * Metodo usato per ottenere lo stato della prenotazione.
     * @return lo stato della prenotazione.
     */
    StatoPreOrd getStatoPrenotazione();
}
