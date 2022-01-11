package com.example.progettocasotto.Model.Spiaggia;

import java.util.Date;

public interface PrenotazioneInterface {

    boolean addOmbrellone(Ombrellone ombrellone);

    boolean addSdraio(Sdraio sdraio);

    void setPeriodo(Date dataInizio, Date dataFine);

    StatoPreOrd getStatoPrenotazione();
}
