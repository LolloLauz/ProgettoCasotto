package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.Privilegio;

import java.util.ArrayList;
import java.util.Date;

/**
 * Interfaccia che definisce il controller per il personale della spiaggia.
 */
public interface PersonaleInterface {

    /**
     * Metodo usato per creare un utente nel sistema.
     * @param nome il nome dell'utente.
     * @param cognome il cognome dell'utente.
     * @param email l'email dell'utente.
     * @param password la password dell'utente.
     * @param privilegio il privilegio dell'utente.
     * @return true se l'utente &egrave; stato creato correttamente, false altrimenti.
     */
    boolean creaUtente(String nome, String cognome, String email, String password, Privilegio privilegio);

    /**
     * Metodo usato per la modifica di una prenotazione all'interno del sistema.
     * @param idPrenotazione l'id della prenotazione.
     * @param dataInizio la data di inizio della prenotazione.
     * @param dataFine la data di fine della prenotazione.
     * @param ombrelloni la lista di ombrelloni aggiunti alla prenotazione.
     * @param numSdraio il numero delle sdraio aggiunte alla prenotazione.
     */
    void modificaPrenotazione(String idPrenotazione, Date dataInizio, Date dataFine, ArrayList<String> ombrelloni,int numSdraio);

    /**
     * Metodo usato per modificarfe il periodo della prenotazione.
     * @param idPrenotazione l'id della prenotazione.
     * @param dataInizio la data di inizio della prenotazione.
     * @param dataFine la data di fine della prenotazione.
     * @return true se la modifica &egrave; avvenuta con successo, false altrimenti.
     */
    boolean modificaPeriodoPrenotazione(String idPrenotazione, Date dataInizio, Date dataFine);

    /**
     * Metodo usato per la rimozione di una prenotazione dal sistema.
     * @param idPrenotazione l'id della prenotazione da rimuovere.
     */
    void removePrenotazione(String idPrenotazione);

    /**
     * Metodo usato per la gestione di un pagamento in contanti.
     * @param idCliente l'id del cliente che deve effettuare il pagamento.
     */
    void gestisciPagamento(String idCliente);

    /**
     * Metodo usato per stampare lo scontrino di una prenotazione.
     * @param idPrenotazione l'id della prenotazione da cui ricavare lo scontrino.
     */
    void getScontrinoPrenotazione(String idPrenotazione);

    /**
     * Metodo usato per effettuare una prenotazione manuale.
     * @param idUtente nome dell'utente.
     * @param dataInizio data inizio della prneotazione.
     * @param dataFine data di fine della prenotazione.
     * @param listaOmbrelloni la lista degli ombrelloni.
     * @param numSdraio il numero di sdraio.
     * @return il numero della prenotazione.
     */
    int prenotazioneManuale(String idUtente,Date dataInizio,Date dataFine,ArrayList<String> listaOmbrelloni,int numSdraio);

    /**
     * Metodo usato per confermare l'avvenuto pagamento di una prenotazione.
     * @param idPrenotazione l'id della prenotazione a cui bisogna confermare il pagamento.
     */
    void confermaAvvenutoPagamentoPrenotazione(String idPrenotazione);
}
