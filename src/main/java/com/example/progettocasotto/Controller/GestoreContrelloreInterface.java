package com.example.progettocasotto.Controller;

import java.util.Date;

public interface GestoreContrelloreInterface {
    boolean creaChalet(String nome);
    boolean creaSpiaggia(String spiaggia);
    boolean creaBar(String bar);
    void setNumOmbrelloni(int numOmbrelloni);
    void setNumSdraio(int numSdraio);
    void creaAttivita(String nome, String luogo, String descrizione, Date dataInizio, Date dataFine,int numeroPosti);
}
