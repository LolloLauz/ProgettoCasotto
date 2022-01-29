package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.Privilegio;

import java.util.ArrayList;
import java.util.Date;

/**
 * Interfaccia che definisce il personale della spiaggia.
 */
public interface PersonaleInterface {

    /**
     * Metodo usato per creare un utente nel sistema.
     * @param nome il nome dell'utente.
     * @param cognome il cognome dell'utente.
     * @param email l'email dell'utente.
     * @param password la password dell'utente.
     * @param privilegio il privilegio dell'utente.
     * @return true se
     */
    boolean creaUtente(String nome, String cognome, String email, String password, Privilegio privilegio);

    void modificaPrenotazione(String idPrenotazione, Date dataInizio, Date dataFine, ArrayList<String> ombrelloni,int numSdraio);

    boolean modificaPeriodoPrenotazione(String idPrenotazione, Date dataInizio, Date dataFine);

    void removePrenotazione(String idPrenotazione);

    void gestisciPagamento(String idCliente);

    void getScontrinoPrenotazione(String idPrenotazione);

    int prenotazioneManuale(String idUtente,Date dataInizio,Date dataFine,ArrayList<String> listaOmbrelloni,int numSdraio);

    void confermaAvvenutoPagamentoPrenotazione(String idPrenotazione);
}
