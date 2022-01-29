package com.example.progettocasotto.Model.Chalet;

import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Spiaggia.Sdraio;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

/**
 * Interfaccia che definisce una spiaggia.
 */
public interface SpiaggiaInterface {

    /**
     * Metodo che serve per aggiungere una prenotazione alla spiaggia
     * @param id nome della prenotazione
     * @param dataInizio data inizio del periodo della prenotazione
     * @param dataFine data fine del periodo della fine della prenotazione
     * @return True se la prenotazione e' aggiunta con successo. False altrimenti
     */
    boolean addPrenotazione(String id, Date dataInizio, Date dataFine);

    /**
     * Metodo per aggiungere un ombrellone ad una prenotazione
     * @param idPrenotazione
     * @param ombrellone
     * @return
     */
    boolean addOmbrelloneToPrenotazione(String idPrenotazione,Ombrellone ombrellone);

    /**
     * Metodo usato per aggiungere sdraio ad una prenotazione.
     * @param idPrenotazione l'id della prenotazione.
     * @param numSdraio il numero di sdraio da aggiungere alla prenotazione.
     * @return true se l'aggiunta è avvenuta con successo, false altrimenti.
     */
    boolean addSdraioToPrenotazione(String idPrenotazione,int numSdraio);

    /**
     * Metodo che ritorna una lista di ombrelloni occupati in un certo periodo
     * @param dataInizio data inizio del periodo
     * @param dataFine data fine del periodo
     * @return una lista di ombrelloni che risultano occupati
     */
    ArrayList<Ombrellone> getOmbrelloniOccupati(Date dataInizio, Date dataFine);

    /**
     * Metodo che ritorna una lista di ombrelloni occupati in un determinato periodo
     * @param dataInizio data inizio del periodo
     * @param dataFine data fine del periodo
     * @return una lista di ombrelloni Liberi nel periodo specificato
     */
    ArrayList<Ombrellone> getOmbrelloniLiberi(Date dataInizio, Date dataFine);

    /**
     * Metodo che ritorna un lista di sdraio liberi in un determinato periodo
     * @param dataInizio la data di inizio
     * @param dataFine la data di fine
     * @return l'arraylist degli sdraio liberi
     */
    ArrayList<Sdraio> getSdraioLiberi(Date dataInizio, Date dataFine);

    /**
     * Metodo che ritorna un lista di sdraio occupati in un determinato periodo
     * @param dataInizio
     * @param dataFine
     * @return
     */
    ArrayList<Sdraio> getSdraioOccupato(Date dataInizio, Date dataFine);

    /**
     * Metodo usato per impostare il numero degli sdraio di una spiagia.
     * @param numSdraio il numero degli sdraio.
     */
    void setNumeroSdraio(int numSdraio);

    /**
     * Metodo usato per impostare il numero degli ombrelloni di una spiaggia.
     * @param numOmbrelloni il numero di ombrelloni.
     */
    void setNumeroOmbrelloni(int numOmbrelloni);
}
