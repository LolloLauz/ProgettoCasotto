package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.Privilegio;

import java.util.ArrayList;
import java.util.Date;

public interface PersonaleInterface {
    boolean creaUtente(String nome, String cognome, String email, String password, Privilegio privilegio);



    void modificaPrenotazione(String idPrenotazione, Date dataInizio, Date dataFine, ArrayList<String> ombrelloni,int numSdraio);

    boolean modificaPeriodoPrenotazione(String idPrenotazione, Date dataInizio, Date dataFine);

    void removePrenotazione(String idPrenotazione);

    void gestisciPagamento(String idCliente);

    void getScontrinoPrenotazione(String idPrenotazione);

    void prenotazioneManuale(String idUtente,Date dataInizio,Date dataFine,ArrayList<String> listaOmbrelloni,int numSdraio);

    void confermaAvvenutoPagamentoPrenotazione(String idPrenotazione);
}
