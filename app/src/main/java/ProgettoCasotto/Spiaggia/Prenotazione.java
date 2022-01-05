package ProgettoCasotto.Spiaggia;


import java.util.ArrayList;

public class Prenotazione implements PrenotazioneInterface {
    String ID;
    private int dataInizo;
    private int dataFine;
    private Stato statoPrenotazione;
    ArrayList<Ombrellone> ombrelloni;
    ArrayList<Sdraio> sdraios;

    public Prenotazione(String ID, int dataInizo, int dataFine){
        this.ID=ID;
        this.dataFine=dataFine;
        this.dataInizo=dataInizo;
        ombrelloni=new ArrayList<>();
        sdraios=new ArrayList<>();
        this.statoPrenotazione=Stato.IN_ATTESA_DI_PAGAMENTO;
    }
    public boolean addOmbrellone(Ombrellone ombrellone){
        return ombrelloni.add(ombrellone);
    }

    public boolean addSdraio(Sdraio sdraio){ return sdraios.add(sdraio);}

    @Override
    public void setPeriodo(int dataInizio, int dataFine) {
        this.dataInizo=dataInizio;
        this.dataFine=dataFine;
    }
    public void removeAllOmbrellone(){
        ombrelloni.clear();
    }

    public int getDataFine() {
        return dataFine;
    }

    public String getID() {
        return ID;
    }

    public int getDataInizo() {
        return dataInizo;
    }
    public ArrayList<Ombrellone> getOmbrelloni(){
        return ombrelloni;
    }

    public ArrayList<Sdraio> getSdraio(){ return sdraios; }

    public void cambiaStatoPrenotazione() {
        statoPrenotazione=Stato.CONFERMATA;
    }

    public Stato getStatoPrenotazione() {
        return statoPrenotazione;
    }

    public void prenotazioneScaduta(){
        statoPrenotazione=Stato.SCADUTA;
    }
}
