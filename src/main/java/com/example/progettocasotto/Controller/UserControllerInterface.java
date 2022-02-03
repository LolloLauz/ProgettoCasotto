package com.example.progettocasotto.Controller;

import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;
import java.util.Date;

/**
 * Interfaccia che definisce il controller per l'utente.
 */
public interface UserControllerInterface {

    /**
     * Metodo usato per prenotare un ombrellone.
     * @param dataInizio la data di inizio della prenotazione.
     * @param dataFine la data di fine della prenotazione.
     */
    void prenotaOmbrellone(Date dataInizio, Date dataFine);

    /**
     * Metodo usato per selezionare gli ombrelloni.
     * @param numeroOmbrellone il numero degli ombrelloni.
     */
    void selectOmbrellone(String numeroOmbrellone);

    /**
     * Metodo usato per ordinare una bibita.
     * @param bevanda l'identificativo della bibita.
     * @param quantita la quantit&agrave; desiderata.
     */
    void ordinaBibita(String bevanda,int quantita);

    /**
     * Metodo usato per confermare una prenotazione.
     */
    void confermaPernotazione();

    /**
     * Metodo usato per prenotare uno sdraio.
     * @param dataInizio la data di inizio della prenotazione.
     * @param dataFine la data di fine della prenotazione.
     * @return true se la prenotazione &egrave; avvenuta con successo, false altrimenti.
     */
    boolean prenotaSdraio(Date dataInizio, Date dataFine);

    /**
     * Metodo usato per selezionare le sdraio.
     * @param numSdraio il numero delle sdraio.
     * @return true se le sdraio sono state selezionate, false altrimenti.
     */
    boolean selectNumSdraio(int numSdraio);

    /**
     * Metodo usato per prenotare un'attivit&agrave;.
     * @param nomeAttivita nome dell'attivit&agrave;
     * @param numPersone numero delle persone da iscrivere all'attivit&agrave;
     * @return true se la prenotazione &egrave; avvenuta con successo, false altrimenti.
     */
    boolean prenotaAttivita(String nomeAttivita, int numPersone);

    /**
     * Metodo usato per pagare una prenotazione.
     * @param idPrenotazione l'id della prenotazione da pagare.
     */
    String pagaPrenotazione(String idPrenotazione);

    /**
     * Metodo usato per creare un'ordinazione.
     */
    void creaOrdinazione();

    /**
     * Metodo usato per calcolare il costo totale dell'ordinazione.
     * @return il costo totale dell'ordinazione.
     */
    double getTOtaleOrdinazione();
}
