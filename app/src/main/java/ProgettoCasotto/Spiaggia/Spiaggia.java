package ProgettoCasotto.Spiaggia;




import java.util.*;


/**
 * Ombrelloni rappresente l'hashmap chiave valore dove la chiave e' l'ombrellone
 * mentre il valore rappresenta se l'ombrellone e' occupato o no. True se e' occupato false se e' libero
 */
public class Spiaggia implements SpiaggiaInterface{
    private String nome;
    private ArrayList<Prenotazione> prenotazioni;
    private ArrayList<Ombrellone> listaOmbrelloni;
    private ArrayList<Sdraio> listaSdraio;
    private ArrayList<Attivita> listaAttivita;

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spiaggia spiaggia = (Spiaggia) o;
        return Objects.equals(nome, spiaggia.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public Spiaggia(String nome) {
        this.nome = nome;
        listaOmbrelloni=new ArrayList<>();
        listaSdraio=new ArrayList<>();
        prenotazioni=new ArrayList<>();
        listaAttivita=new ArrayList<>();
    }

    public ArrayList<Ombrellone> getOmbrelloniOccupati(int dataInizio, int dataFine){
        ArrayList<Ombrellone> ombrelloniOccupati=new ArrayList<>();
        for(Prenotazione prenotazione:prenotazioni) {
            if (prenotazione.getStatoPrenotazione() == Stato.CONFERMATA) {
                if ((prenotazione.getDataInizo() <= dataInizio && dataInizio <= prenotazione.getDataFine()) ||
                        (prenotazione.getDataInizo() <= dataFine && dataFine <= prenotazione.getDataFine()) ||
                        (dataInizio <= prenotazione.getDataInizo() && prenotazione.getDataInizo() <= dataFine) ||
                        (dataInizio <= prenotazione.getDataFine() && prenotazione.getDataFine() <= dataFine)) {
                    ombrelloniOccupati.addAll(prenotazione.getOmbrelloni());
                }
            }
        }
        return ombrelloniOccupati;
    }

    public ArrayList<Ombrellone> getOmbrelloniLiberi(int dataInizio, int dataFine){
       ArrayList<Ombrellone> ombrelloniLiberi=new ArrayList<>();
       ombrelloniLiberi.addAll(listaOmbrelloni);
       for(Ombrellone ombrellone:getOmbrelloniOccupati(dataInizio,dataFine)){
           ombrelloniLiberi.removeIf(ombrellone2 -> ombrellone2.getID().equals(ombrellone.getID()));
       }
        System.out.println(ombrelloniLiberi.size());
       return ombrelloniLiberi;
    }

    public boolean addPrenotazione(String ID,int dataInizio, int dataFine){
        return prenotazioni.add(new Prenotazione(ID,dataInizio,dataFine));
    }

    @Override
    public ArrayList<Sdraio> getSdraioLiberi(int dataInizio, int dataFine) {
        ArrayList<Sdraio> sdraioLiberi=new ArrayList<>();
        sdraioLiberi.addAll(listaSdraio);
        for(Sdraio sdraio:getSdraioOccupati(dataInizio,dataFine)){
            sdraioLiberi.removeIf(sdraio2 -> sdraio2.getID().equals(sdraio.getID()));
        }
        System.out.println(sdraioLiberi.size());
        return sdraioLiberi;
    }

    @Override
    public ArrayList<Sdraio> getSdraioOccupati(int dataInizio, int dataFine) {
        ArrayList<Sdraio> sdraioOccupati=new ArrayList<>();
        for(Prenotazione prenotazione:prenotazioni) {
            if (prenotazione.getStatoPrenotazione() == Stato.CONFERMATA) {
                if ((prenotazione.getDataInizo() <= dataInizio && dataInizio <= prenotazione.getDataFine()) ||
                        (prenotazione.getDataInizo() <= dataFine && dataFine <= prenotazione.getDataFine()) ||
                        (dataInizio <= prenotazione.getDataInizo() && prenotazione.getDataInizo() <= dataFine) ||
                        (dataInizio <= prenotazione.getDataFine() && prenotazione.getDataFine() <= dataFine)
                ) {
                    sdraioOccupati.addAll(prenotazione.getSdraio());
                }
            }
        }
        return sdraioOccupati;
    }

    @Override
    public boolean addSdraioToPrenotazione(String prenotazioneID, String IDSdraio) {
        if(!listaSdraio.contains(new Sdraio(IDSdraio))){
            System.out.println("Sdraio inesistente");
            return false;
        }
        for (Prenotazione prenotazione:prenotazioni){
            if(prenotazione.getID().equals(prenotazioneID)){
                return prenotazione.addSdraio(new Sdraio(IDSdraio));
            }
        }
        return false;
    }

    @Override
    public boolean creaAttivita(String nomeAttivita, int numPosti, int dataInizio, int dataFine) {
        if(!listaAttivita.contains(new Attivita(nomeAttivita,numPosti,dataInizio,dataFine))){
            return listaAttivita.add(new Attivita(nomeAttivita,numPosti,dataInizio,dataFine));
        }
        return false;
    }

    @Override
    public void scannerizzaQRCode(QRCode QRCode) {
        //l'utente scannerizza il qr code del proprio ombrellone
    }

    @Override
    public void setNumOmbrelloni(int numOmbrelloni) {
        for(int i=0;i<numOmbrelloni;i++){
            listaOmbrelloni.add(new Ombrellone(Integer.toString(i)));
        }
    }

    @Override
    public void setNumSdraio(int numSdraio) {
        for(int i=0;i<numSdraio;i++){
            listaSdraio.add(new Sdraio(Integer.toString(i)));
        }
    }

    @Override
    public boolean modificaPrenotazione(Prenotazione prenotazione) {
        Scanner scanner=new Scanner(System.in);

        int periodo=0;
        int dataInizio=26;
        int dataFine=30;
        int numSdraio=2;
        System.out.println("1- Modifica periodo" +
                "2- Aggiungi Sdraio" +
                "3- Aggiungi Ombrellone");
        periodo=scanner.nextInt();
        if(prenotazioni.contains(prenotazione)){
            switch (periodo){
                case 1:
                    if(!modificaPeriodoPrenotazione(prenotazione,dataInizio,dataFine)){
                        System.out.println("l'ombrellone presente nella prenotazione non e' piu prenotabile");
                        //if cliente vuole comunque modificare il periodo
                        forzaCambioPeriodo(prenotazione,dataInizio,dataFine);
                    }
                    break;
                case 2:
                    modificaNumeroSdraio(prenotazione,numSdraio);
                    break;
                case 3:
                    modificaNumeroOmbrelloni(prenotazione);
                default:
                    break;
            }
        }

        return false;
    }

    @Override
    public boolean confermaAvvenutoPagamentoPrenotazione(Prenotazione prenotazione) {
        if(prenotazioni.contains(prenotazione)){
            prenotazione.cambiaStatoPrenotazione();
            return true;
        }
        return false;

    }

    @Override
    public boolean pagaPrenotazione(Prenotazione prenotazione) {
        System.out.println("IL PAGAMENTO ON-LINE E' STATO EFFETTUATO CON SUCCESSO");
        if(prenotazioni.contains(prenotazione)){
            prenotazione.cambiaStatoPrenotazione();
            return true;
        }

        return false;
    }

    private void forzaCambioPeriodo(Prenotazione prenotazione, int dataInizio, int dataFine) {
        System.out.println("forzo comunque il cambio prenotazione");
        prenotazione.removeAllOmbrellone();
        prenotazione.setPeriodo(dataInizio,dataFine);
    }

    private boolean modificaNumeroOmbrelloni(Prenotazione prenotazione) {
        //l'utente immettera un o piu ombrelloni da aggiungere
        String ombrelloneNuovo="9";
        if(getOmbrelloniLiberi(prenotazione.getDataInizo(), prenotazione.getDataFine()).isEmpty()){
            return false;
        }
        if(getOmbrelloniLiberi(prenotazione.getDataInizo(), prenotazione.getDataFine()).contains(new Ombrellone(ombrelloneNuovo))){
            prenotazione.addOmbrellone(new Ombrellone(ombrelloneNuovo));
            return true;
        }
        System.out.println("l'ombrellone selezionato e' al momento occupato");
        return false;
    }

    private boolean modificaNumeroSdraio(Prenotazione prenotazione, int numSdraio) {
        if(!(getSdraioLiberi(prenotazione.getDataInizo(), prenotazione.getDataFine()).size()>=numSdraio)){
            System.out.println("non ci sono sdraio disponibili");
            return false;
        }
        int i=0;
        for(Sdraio sdraio:getSdraioLiberi(prenotazione.getDataInizo(),prenotazione.getDataFine())){
            if(i<numSdraio){
                prenotazione.addSdraio(sdraio);
                i++;
            }
        }
        return true;
    }

    private boolean modificaPeriodoPrenotazione(Prenotazione prenotazione,int dataInizio,int dataFine) {
        for (Ombrellone ombrellone:prenotazione.getOmbrelloni()){
            if(!(getOmbrelloniLiberi(dataInizio,dataFine).contains(ombrellone))){
                System.out.println("l'ombrellone non e' piu' disponibile in quel periodo");
                return false;
            }
        }
        for(Sdraio sdraio:prenotazione.getSdraio()){
            if(!(getSdraioLiberi(dataInizio,dataFine).contains(sdraio))){
                System.out.println("lo sdraio "+sdraio.getID()+" non e' piu' disponibile per quel periodo");
                return false;
            }
        }
        prenotazione.setPeriodo(dataInizio,dataFine);
        return true;
    }

    public boolean addOmbrelloneToPrenotazione(String prenotazioneID,String IDOmbrellone){
        if(!listaOmbrelloni.contains(new Ombrellone(IDOmbrellone))){
            System.out.println("Ombrellone inesistente");
            return false;
        }
        for (Prenotazione prenotazione:prenotazioni){
            if(prenotazione.getID().equals(prenotazioneID)){
                return prenotazione.addOmbrellone(new Ombrellone(IDOmbrellone));
            }
        }
        return false;
    }


    public void getStatoSpiaggia(){
        System.out.println("il nome della spiaggia: "+nome+" il numero degli ombrelloni: "+listaOmbrelloni.size()+" il numero delle sdraio: "+listaSdraio.size());
    }

    public String getPrenotazione(){
        for (Prenotazione prenotazione:prenotazioni) {
            if (prenotazione.getStatoPrenotazione() == Stato.CONFERMATA) {
                System.out.println(prenotazione.getID() + " Inizio: " + prenotazione.getDataInizo() + " Fine: " + prenotazione.getDataFine());
                System.out.println("gli ombrelloni prentotati sono : ");
                for (Ombrellone ombrellone : prenotazione.getOmbrelloni()) {
                    System.out.println(ombrellone.getID());
                }
                System.out.println("le sdraio prentotati sono : ");
                for (Sdraio sdraio : prenotazione.getSdraio()) {
                    System.out.println(sdraio.getID());
                }
            }
        }
        return "";
        }
        public void getAttivita(){
            for (Attivita attivita:listaAttivita){
                System.out.println(attivita.getNome()+"il numero di posti disponibili e' "+attivita.getNumPosti());
            }
        }
        public boolean addPrenotazione(Prenotazione prenotazione){
            if(prenotazioni.isEmpty()){
                prenotazioni.add(prenotazione);
            }
            if(!(prenotazioni.contains(prenotazione))) {
                return prenotazioni.add(prenotazione);
            }
            return false;
        }
        public void cambiaStatoPrenotazioneInBaseAlTempo(){
            ArrayList<Prenotazione> listaPrenotazioniScadute=new ArrayList<>();
            for(Prenotazione prenotazione:prenotazioni){
                if(prenotazione.getStatoPrenotazione()==Stato.IN_ATTESA_DI_PAGAMENTO){
                    prenotazione.prenotazioneScaduta();
                    listaPrenotazioniScadute.add(prenotazione);
                }
            }
            for(Prenotazione prenotazione:listaPrenotazioniScadute){
                prenotazioni.remove(prenotazione);
            }
        }
}
