package ProgettoCasotto.Spiaggia;

import ProgettoCasotto.Spiaggia.Ombrellone;
import ProgettoCasotto.Spiaggia.Sdraio;

public interface PrenotazioneInterface {

    /**
     * metodo per aggiungere un ombrellone alla prenotazione
     * @param ombrellone
     * @return
     */
    public boolean addOmbrellone(Ombrellone ombrellone);

    /**
     * metodo per aggiungere uno sdraio alla prenotazione
     * @param sdraio
     * @return
     */
    public boolean addSdraio(Sdraio sdraio);

    /**
     * metodo per modificare il periodo di una prenotazione
     * @param dataInizio
     * @param dataFine
     */
    public void setPeriodo(int dataInizio, int dataFine);
}
