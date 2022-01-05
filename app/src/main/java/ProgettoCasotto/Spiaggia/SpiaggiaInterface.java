package ProgettoCasotto.Spiaggia;

import java.util.ArrayList;

public interface SpiaggiaInterface {
    /**
     * metodo che serve per aggiungere un ombrellone ad una prenotazione
     * @param IDombrellone l'obrellone che si deve aggiungere
     * @param prenotazioneID L'ID della prenotazione a cui si vuole aggiungere l'ombrellone
     * @return True se e' stato aggiunto con sucesso. False altrimenti
     */
    boolean addOmbrelloneToPrenotazione(String prenotazioneID,String IDombrellone);

    /**
     * metodo che permette di vedere quali ombrelloni sono liberi in un certo lasso di tempo
     * @param dataInizio la data di inizio del periodo
     * @param dataFine la data di fine periodo
     * @return Una lista di ombrelloni
     */
    ArrayList<Ombrellone> getOmbrelloniLiberi(int dataInizio, int dataFine );

    /**
     * metodo che permette di vedere quali ombrelloni sono occupati in un certo periodo
     * @param dataInizio data di inzio periodo
     * @param dataFine data di fine periodo
     * @return lista degli ombrelloni occupati
     */
    ArrayList<Ombrellone> getOmbrelloniOccupati(int dataInizio, int dataFine );

    /**
     * metodo che permette di aggiungere una prenotazione in un certo periodo
     * @param ID l'identificativo della prenotazione
     * @param dataInizo la data di inizio del periodo
     * @param dataFine la data di fine del periodo
     * @return True se la prenotazione e' stata aggiunta con successo. False altrimenti
     */
    boolean addPrenotazione (String ID, int dataInizo,int dataFine);

    /**
     * per vedere le sdraio libere
     * @param dataInizio
     * @param dataFine
     * @return
     */
    ArrayList<Sdraio> getSdraioLiberi(int dataInizio,int dataFine);

    /**
     * per veder le sdraio occupate
     * @param dataInizio
     * @param dataFine
     * @return
     */
    ArrayList<Sdraio> getSdraioOccupati(int dataInizio, int dataFine);

    boolean addSdraioToPrenotazione(String prenotazioneID,String IDSdraio);

    /**
     * metodo che serve per creare un'attivita in una specifica data con un certo numero di posti
     * @param nomeAttivita
     * @param numPosti
     * @param dataInizio
     * @param dataFine
     * @return
     */
    boolean creaAttivita(String nomeAttivita, int numPosti, int dataInizio, int dataFine);


    void scannerizzaQRCode(QRCode QRCode);

    /**
     * metodo che serve per inserire un numero massimo di ombrelloni
     * @param numOmbrelloni
     */
    void setNumOmbrelloni(int numOmbrelloni);

    /**
     * metodo che viene utilizzato per inserire un numero massimo di sdrio
     * @param numSdraio
     */
    void setNumSdraio(int numSdraio);

    /**
     * metodo per modificare una prenotazione
     * @param prenotazione
     * @return
     */
    boolean modificaPrenotazione(Prenotazione prenotazione);

    /**
     * il metodo viene utilizzato solo da chi si occupa di ricevere il pagamento del cliente
     * metodo per confermare l'avvenuto pagamento
     * di una specifica prenotazione
     * @param prenotazione la prenotazione a cui viene confermato il pagamento
     * @return True se la conferma e' avvenuta con successo. False altrimenti
     */
    boolean confermaAvvenutoPagamentoPrenotazione(Prenotazione prenotazione);

    /**
     * metodo per pagare una prenotazione
     * @param prenotazione
     * @return
     */
    boolean pagaPrenotazione(Prenotazione prenotazione);

}
