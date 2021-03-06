package com.example.progettocasotto.Controller;


import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultOrdinazione;
import com.example.progettocasotto.Model.Chalet.DefaultAttivita;
import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DefaultUserController implements UserControllerInterface {
    private DefaultCliente currentCliente;
    private ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
    private DefaultPrenotazione prenotazione;
    private DefaultOrdinazione ordinazione;
    private DefaultaMasterController masterController;

    public DefaultUserController(DefaultaMasterController masterController,String nomeCurrentUser) {
        this.masterController=masterController;
        this.currentCliente=new DefaultCliente(nomeCurrentUser);
    }

    private void checkPrenotazioneCurrentCliente() {
        if(!masterController.getChalet().getSpiaggia().getListaPrenotazioni().isEmpty()) {
            if (masterController.getChalet().getSpiaggia().getListaPrenotazioni().containsAll(currentCliente.getPrenotazioniAssociate())) {
                System.out.println("le prenotazioni del cliente sono in pari con quelle della spiaggia");
            } else {
                currentCliente.getPrenotazioniAssociate().removeIf(prenotazione -> !masterController.getChalet().getSpiaggia().getListaPrenotazioni().contains(prenotazione));
            }
        }
    }

    public void prenotaOmbrellone(Date dataInizio, Date dataFine){
        Random random=new Random();
        String nomePrenotazione=String.valueOf(random.nextInt(1000));
        prenotazione=new DefaultPrenotazione(nomePrenotazione,dataInizio,dataFine);
        masterController.getChalet().getSpiaggia().addPrenotazione(prenotazione.getID(),dataInizio,dataFine);
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione.getID()).setIdUtenteAssociato(currentCliente.getID());
    }

    public void mostraOmbrelloniLiberi(Date dataInizio, Date dataFine) {
        masterController.getChalet().getSpiaggia().stampaOmbrelloniLiberi(dataInizio,dataFine);
    }

    public void mostraSdraioLiberi(Date dataInizio, Date dataFine){
        masterController.getChalet().getSpiaggia().stampaSdraioLiberi(dataInizio,dataFine);
    }

    public void selectOmbrellone(String numeroOmbrellone){
        if(numeroOmbrellone!=null) {
            masterController.getChalet().getSpiaggia().addOmbrelloneToPrenotazione(prenotazione.getID(), new Ombrellone(numeroOmbrellone));
        }
    }

    public void ordinaBibita(String bevanda,int quantita){
        for(Bevanda bev:masterController.getChalet().getBar().getListaBevande()){
            if(bev.getNome().equals(bevanda)){
                ordinazione.addBevanda(bev,quantita);
            }
        }
        masterController.getChalet().getBar().selezionaBevanda(ordinazione.getID(),bevanda,quantita);
        masterController.getGestoreDB().decrementaBevandaOrdinata(bevanda,quantita);
    }

    public boolean setCurrentClient(String nomeCliente) {
        for(DefaultCliente defaultCliente: listaClienti){
            if(defaultCliente.getID().equals(nomeCliente)){
                currentCliente=defaultCliente;
                return true;
            }
        }
        return false;
    }

    public void confermaPernotazione() {
        currentCliente.addPrenotazioneToCliente(prenotazione);
    }

    public boolean setlistaClienti(ArrayList<DefaultCliente> listaClienti) {
        return this.listaClienti.addAll(listaClienti);
    }

    public boolean prenotaSdraio(Date dataInizio, Date dataFine){
        Random random=new Random();
        String nomePrenotazione=String.valueOf(random.nextInt(1000));
        prenotazione=new DefaultPrenotazione(nomePrenotazione,dataInizio,dataFine);
        masterController.getChalet().getSpiaggia().addPrenotazione(prenotazione.getID(),dataInizio,dataFine);
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).setIdUtenteAssociato(currentCliente.getID());
        return true;
    }
    public boolean selectNumSdraio(int numSdraio) {
        if(masterController.getChalet().getSpiaggia().getSdraioLiberi(prenotazione.getDataInizio(),prenotazione.getDataFine()).isEmpty()){
            System.out.println("non ci sono sdraio disponibili");
            return false;
        }
        return masterController.getChalet().getSpiaggia().addSdraioToPrenotazione(prenotazione.getID(),numSdraio);
    }

    public DefaultPrenotazione getCurrentPrenotazione() {
        return prenotazione;
    }
    public DefaultSpiaggia getChalet(){
        return masterController.getChalet().getSpiaggia();
    }

    public void stampaMenu() {
        for(Bevanda bevanda:masterController.getChalet().getBar().getListaBevande()) {
            System.out.println("|"+bevanda.getNome()+"     "+bevanda.getDescrizione()+"|");
        }
    }

    public void getOrdinazione() {
        masterController.getChalet().getBar().stampaOrdinazionebyID(ordinazione.getID());
    }

    public boolean prenotaAttivita(String nomeAttivita, int numPersone) {
        masterController.getGestoreDB().prenotaAttivita(nomeAttivita,numPersone,currentCliente.getID());
        return masterController.addPartecipantiToAttivita(nomeAttivita,numPersone);
    }

    public void getListaAttivita() {
        for(DefaultAttivita attivita:masterController.getChalet().getListaAttivita()){
            System.out.println(attivita.getNome()+" data inizio "+ attivita.getDataInizio().toString()+" data fine "+attivita.getDataFine().toString()+" numero posti disponibili "+attivita.getNumeroPosti());
        }
    }

    public boolean prenotaAttivita(String nomeAttivita) {
        for(DefaultAttivita attivita:masterController.getChalet().getListaAttivita()){
            if(attivita.getNome().equals(nomeAttivita)){
                if(attivita.getNumeroPosti()<=0){
                    System.out.println("Posti esauriti");
                    return false;
                }else {
                    System.out.println("il numero di posti rimanenti e'" + attivita.getNumeroPosti());
                    return true;
                }
            }
        }
        return false;
    }

    public void getPrenotazioneCliente() {
       for(String idPrenotazione: masterController.getGestoreDB().getPrenotazioniClientedaPagare(currentCliente.getID())){
            System.out.println("prenotazione id: "+masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).getID());
        }
//        for(DefaultPrenotazione prenotazione:masterController.getChalet().getSpiaggia().getPrenotazioniCliente(currentCliente.getID())){
//            if(prenotazione.getStatoPrenotazione().equals(StatoPreOrd.IN_ATTESA_DI_PAGAMENTO)) {
//                System.out.println("prenotazione id :" + prenotazione.getID());
//            }
//        }

    }

    public String pagaPrenotazione(String idPrenotazione) {
        String result="";
        result=result+masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).stampaOmbrelloni()+"\n";
        result=result+masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).stampaSdraio()+"\n";
        System.out.println("\nil totale della prenotazione e' "+masterController.getChalet().getSpiaggia().getListinoPrezzi().calcolaPrezzo(masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione)));
        result=result+"\nil totale della prenotazione e' "+masterController.getChalet().getSpiaggia().getListinoPrezzi().calcolaPrezzo(masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione));
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).setStatoPrenotazione(StatoPreOrd.PAGATA);
        masterController.gestoreDB.convalidaPagamentoPrenotazione(idPrenotazione);
        return result;
    }

    public void creaOrdinazione() {
        Random random=new Random();
        String nomeOrdinazione=String.valueOf(random.nextInt(1000));
        masterController.getChalet().getBar().creaOrdinazione(nomeOrdinazione);
        this.ordinazione=new DefaultOrdinazione(nomeOrdinazione);
        masterController.getChalet().getBar().getOrdinazioneById(nomeOrdinazione).setUtenteAssociato(currentCliente.getID());
    }
    public DefaultPrenotazione getPrenotazione(){
        return this.prenotazione;
    }
    public void caricaPrenotazione(){
        int numero=masterController.getGestoreDB().prenotaOmbrellone(masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione.getID()),currentCliente);
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione.getID()).setID(String.valueOf(numero));
        prenotazione.setID(String.valueOf(numero));
    }

    public void caricaPrenotazioneSdraio() {
        int numero=masterController.getGestoreDB().prenotaSdraio(masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione.getID()),currentCliente);
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione.getID()).setID(String.valueOf(numero));
        prenotazione.setID(String.valueOf(numero));
    }

    public void caricaOrdinazione() {
        System.out.println(ordinazione.getID()+" utente "+currentCliente.getID());
        int numero=masterController.getGestoreDB().ordinazioneBar(masterController.getChalet().getBar().getOrdinazioneById(ordinazione.getID()),currentCliente);
        masterController.getChalet().getBar().getOrdinazioneById(ordinazione.getID()).setID(String.valueOf(numero));
        ordinazione.setID(String.valueOf(numero));
    }

    public void getQrCodeOmbrelloni() {
        for(Ombrellone ombrellone:masterController.getChalet().getSpiaggia().getListaOmbrelloni()){
            System.out.println("qr_code ombrellone: "+ombrellone.getQRCode());
        }
    }

    public void setqrCode(String input) {
        masterController.getChalet().getBar().getOrdinazioneById(ordinazione.getID()).setQr_code(input);
    }
    public void pagaOrdinazione() {
        masterController.getGestoreDB().convalidaPagamentoOrdinazione(ordinazione.getID());
        masterController.getChalet().getBar().getOrdinazioneById(ordinazione.getID()).setStatoOrdinazione(StatoPreOrd.PAGATA);
    }
    public double getTOtaleOrdinazione(){
        return masterController.getChalet().getBar().getOrdinazioneById(ordinazione.getID()).getTotaleOrdinazione();
    }

    public String getListOmbrelloniLiberi(Date dataInizio, Date dataFine) {
        String result="";
        int i=0;
        for(Ombrellone ombrellone:masterController.getChalet().getSpiaggia().getOmbrelloniLiberi(dataInizio,dataFine)){
            result=result+ombrellone.getID()+"|";
            if(i>=10){
                i=0;
                result=result+"\n";
            }
            i++;
        }
        return result;
    }

    public String getListSdraioLiberi(Date dataInizio, Date dataFine) {
        return String.valueOf(masterController.getChalet().getSpiaggia().getSdraioLiberi(dataInizio,dataFine).size());
    }

    public String mostraMenu() {

        String result="";
        int i=0;
        for(Bevanda bevanda:masterController.getChalet().getBar().getListaBevande()) {
            result=result+"|"+bevanda.getNome()+"     "+bevanda.getDescrizione()+ "prezzo : "+bevanda.getPrezzo()+"|\n";

        }
        return result;
    }

    public ArrayList<String> getlistaQrCode() {
        ArrayList<String>qrCode=new ArrayList<>();
        for(Ombrellone ombrellone:masterController.getChalet().getSpiaggia().getListaOmbrelloni()) {
            qrCode.add(ombrellone.getQRCode());
        }
        return qrCode;
    }

    public String getStringaOrdinazione() {
        String reuslt=ordinazione.getID()+"\n";
        for(Bevanda bevanda:masterController.getChalet().getBar().getOrdinazioneById(ordinazione.getID()).getListaBevande()){
            reuslt=reuslt+"bevanda "+bevanda.getNome()+" quantita ordinata "+ordinazione.getQuantitaOrdinata(bevanda)+" prezzo :"+bevanda.getPrezzo()+"\n";
        }
        return reuslt;
    }

    public ArrayList<String> getPrenotazioneClient() {
        return masterController.getGestoreDB().getPrenotazioniClientedaPagare(currentCliente.getID());
    }

    public String getScontrinoPrenotazione(String value) {
        String result="gli ombrelloni prenotati sono\n";
        for(Ombrellone ombrellone:masterController.getChalet().getSpiaggia().getPrenotaizioneById(value).getListaOmbrelloni()){
            result=result+ombrellone.getID()+"\n";
        }
        if(masterController.getChalet().getSpiaggia().getPrenotaizioneById(value).getListaSdraio().size()>0){
            result="il numero di sdriao prenotate : "+masterController.getChalet().getSpiaggia().getPrenotaizioneById(value).getListaSdraio().size();
        }
        return result;
    }

    public String mostraListaAttivita() {
        String result="";
        for(DefaultAttivita attivita:masterController.getChalet().getListaAttivita()){
            result=result+attivita.getNome()+" data inizio "+ attivita.getDataInizio().toString()+" data fine "+attivita.getDataFine().toString()+" numero posti disponibili "+attivita.getNumeroPosti()+"\n";
        }
        return result;
    }

    public ArrayList<DefaultAttivita> getAttivita() {

        return masterController.getChalet().getListaAttivita();
    }
}
