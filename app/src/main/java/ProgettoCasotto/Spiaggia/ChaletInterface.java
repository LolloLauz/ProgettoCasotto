package ProgettoCasotto.Spiaggia;

import ProgettoCasotto.ChaletPackage.Bar;

public interface ChaletInterface {

    /**
     * metodo per aggiungere una spiaggia allo chalet
     * @param nome il nome della spiaggia
     * @return
     */
    boolean addSpiaggia(Spiaggia nome);

    /**
     * metodo per aggiungere un bar alla spiaggia
     * @param nome il nome del bar
     * @return
     */
    boolean addBar(Bar nome);

    /**
     * metodo per aggiungere una o piu' aree attivita'
     * @param areaAttivita il nome dell'area
     * @return
     */
    boolean addAreaAttivita(String areaAttivita);

    /**
     * metodo per prenotarsi ad una attivita
     * @param nomeAttivita
     * @param numPosti
     * @return
     */
    boolean prenotaAttivita(String nomeAttivita, int numPosti);

    /**
     * metodo per creare un'attivita
     * @param nomeAttivita nome dell'attivita
     * @param numPosti numero posti
     * @param dataInizio data inizio dell'attivita
     * @param dataFine data fine dell'attivita
     * @return true se l'attivita e' stata creata. False altrimenti
     */
    boolean createAttivita(String nomeAttivita, int numPosti, int dataInizio,int dataFine);
}
