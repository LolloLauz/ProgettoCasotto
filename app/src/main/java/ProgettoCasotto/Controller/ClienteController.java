package ProgettoCasotto.Controller;

import ProgettoCasotto.Spiaggia.Ombrellone;
import ProgettoCasotto.Spiaggia.Sdraio;

public interface ClienteController {

    /**
     * metodo che serve ad un cliente per prenotare un ombrellone
     * @param dataInizo la data di inizio del periodo in cui vuole prenotare
     * @param dataFine la data di fine del periodo in cui vuole prenotare
     *
     */
    public void prenotaOmbrellone(int dataInizo, int dataFine);

    /**
     * metodo che serve ad un cliente per prenotare uno sdraio
     * @param dataInizio
     * @param dataFine
     */
    public void prenotaSdraio(int dataInizio, int dataFine);

}
