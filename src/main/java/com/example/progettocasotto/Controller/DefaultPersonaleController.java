package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.Privilegio;
import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Spiaggia.Sdraio;
import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public abstract class DefaultPersonaleController implements PersonaleInterface{
    DefaultaMasterController masterController;

    public DefaultPersonaleController(DefaultaMasterController masterController) {
        this.masterController = masterController;
    }

    @Override
    public boolean creaUtente(String nome, String cognome, String email, String password, Privilegio pri) {
        String privilegio = pri.name();
        masterController.getChalet().getListaClienti().add(new DefaultCliente(nome));
        return masterController.getGestoreDB().insertUtente(nome, cognome, email, password, privilegio);
    }

    private ArrayList<Ombrellone> selezionaPeriodoOmbrelloni(Date dataInizio, Date dataFine) {
        return masterController.getChalet().getSpiaggia().getOmbrelloniLiberi(dataInizio,dataFine);
    }

    private ArrayList<Sdraio> selezionaPeriodoSdraio(Date dataInizio,Date dataFine){
        return masterController.getChalet().getSpiaggia().getSdraioLiberi(dataInizio,dataFine);
    }


    private Ombrellone  selectOmbrellone(String ombrellone) {
        return new Ombrellone(ombrellone);
    }



    @Override
    public void removePrenotazione(String idPrenotazione) {
        masterController.getChalet().getSpiaggia().removePrenotazione(idPrenotazione);
        masterController.getGestoreDB().removePrenotazione(idPrenotazione);
    }

    @Override
    public void gestisciPagamento(String idCliente) {
        for(String nomePrenotazione:masterController.getGestoreDB().getPrenotazioniClientedaPagare(idCliente)){
            System.out.println("id prenotazione da pagare :"+masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getID());
        }
        for(String nomeOrdinazione:masterController.getGestoreDB().getOrdinazioneClientedaPagare(idCliente)){
            System.out.println("id dell'ordinazione da pagare "+masterController.getChalet().getBar().getOrdinazioneById(nomeOrdinazione).getID());
        }
//        for(PrenotazioniCliente(idCliente)){
//            if(prenotazione.getStatoPrenotazione().equals(StatoPreOrd.IN_ATTESA_DI_PAGAMENTO)){
//                System.out.println("id prenotazione da pagare :"+ prenotazione.getID());
//            }
//        }
//        for(DefaultOrdinazione ordinazione:masterController.getChalet().getBar().getOrdinazioniCliente(idCliente)){
//            if(ordinazione.getStatoOrdinazione().equals(StatoPreOrd.IN_ATTESA_DI_PAGAMENTO)){
//                System.out.println("id dell'ordinazione da pagare: "+ordinazione.getID());
//            }
//        }
    }

    public void modificaPrenotazione(String idPrenotazione,Date dataInizio, Date dataFine,ArrayList<String> ombrelloni,int numSdraio){
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).removeOmbrelloni();
        for(String nome:ombrelloni){
            masterController.getChalet().getSpiaggia().addOmbrelloneToPrenotazione(idPrenotazione,selectOmbrellone(nome));
        }
        if(numSdraio==0){
            return;
        }
        masterController.getChalet().getSpiaggia().addSdraioToPrenotazione(idPrenotazione,numSdraio);

    }
    public boolean modificaPeriodoPrenotazione(String prenotazione,Date dataInizio,Date dataFine){
        boolean flag=true;
        if(!selezionaPeriodoOmbrelloni(dataInizio,dataFine).containsAll(masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione).getListaOmbrelloni())){
            masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione).removeOmbrelloni();
            flag= false;
        }
        if(!selezionaPeriodoSdraio(dataInizio,dataFine).containsAll(masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione).getListaSdraio())){
            masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione).removeSdraio();
            flag= false;
        }
           masterController.getChalet().getSpiaggia().getPrenotaizioneById(prenotazione).setPeriodo(dataInizio,dataFine);
        if(!flag){
            masterController.getGestoreDB().removePrenotazioneFromDettagliOrdinazione(prenotazione);
        }
        masterController.getGestoreDB().modificaPeriodoPrenotazione(prenotazione,dataInizio,dataFine);
        return flag;
    }

    public void getPrenotazioniCliete(String nomeCliente) {
        for(String nomePrenotazione:masterController.getGestoreDB().getPrenotazioniCliente(nomeCliente)){
            System.out.println("numero della prenotazione: "+nomePrenotazione+
                    "\n data inizio :"+masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getDataInizio()+
                    " data fine: "+masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getDataFine());
            if(!masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getListaOmbrelloni().isEmpty()){
                for(Ombrellone ombrellone: masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getListaOmbrelloni()){
                    System.out.println("numero ombrellone prenotato: "+ombrellone.getID());
                }
            }
            if(!masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getListaSdraio().isEmpty()){
                System.out.println("numero sdraio affittati: "+ masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getListaSdraio().size());
            }
        }
    }

    public void inserimentoSdraio(String idPrenotazione, int numSdraio) {
        masterController.getChalet().getSpiaggia().addSdraioToPrenotazione(idPrenotazione,numSdraio);
        masterController.getGestoreDB().modificaNumeroSdraio(idPrenotazione,numSdraio);
    }

    public void inserimentoOmbrelloni(String idPrenotazione, ArrayList<String> listaOmbrelloni) {
        for(String numeroOmbrellone:listaOmbrelloni) {
            masterController.getChalet().getSpiaggia().addOmbrelloneToPrenotazione(idPrenotazione,new Ombrellone(numeroOmbrellone));
        }
        masterController.getGestoreDB().modificaOmbrelloniPrenotazione(idPrenotazione,listaOmbrelloni);
    }

    @Override
    public int prenotazioneManuale(String idUtente,Date dataInizio,Date dataFine,ArrayList<String> listaOmbrelloni,int numSdraio) {
        Random random=new Random();
        int numeroPrenotazione=random.nextInt(1000);
        masterController.getChalet().getSpiaggia().addPrenotazione(String.valueOf(numeroPrenotazione),dataInizio,dataFine);
        if(!listaOmbrelloni.isEmpty()) {
            for (String nomeOmbrellone : listaOmbrelloni) {
            masterController.getChalet().getSpiaggia().addOmbrelloneToPrenotazione(String.valueOf(numeroPrenotazione),new Ombrellone((nomeOmbrellone)));
            }
        }
        if(numSdraio!=0){
            masterController.getChalet().getSpiaggia().addSdraioToPrenotazione(String.valueOf(numeroPrenotazione),numSdraio);
        }
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(String.valueOf(numeroPrenotazione)).setIdUtenteAssociato(idUtente);
        int idPrenotazione=masterController.getGestoreDB().addPrenotazioneToDb(idUtente,dataInizio,dataFine,listaOmbrelloni,numSdraio);
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(String.valueOf(numeroPrenotazione)).setID(String.valueOf(idPrenotazione));
        return idPrenotazione;
    }
    public boolean getOmbrelloniLiberi(Date dataInizio,Date dataFine){
        if(masterController.getChalet().getSpiaggia().getOmbrelloniLiberi(dataInizio,dataFine).size()>0){
            masterController.getChalet().getSpiaggia().stampaOmbrelloniLiberi(dataInizio,dataFine);
            return true;
        }else{
            return false;
        }
    }

    public boolean getSdraioLiberi(Date dataInizio,Date dataFine) {
        if (masterController.getChalet().getSpiaggia().getSdraioLiberi(dataInizio, dataFine).size() > 0){
            masterController.getChalet().getSpiaggia().stampaSdraioLiberi(dataInizio, dataFine);
            return true;
        }else{
            return false;
        }

    }

    public void getScontrinoPrenotazione(String idPrenotazione) {
        System.out.println("l'identificativo della prenotazione e' :"+ idPrenotazione);
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).stampaOmbrelloni();
        masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).stampaSdraio();
        System.out.println("\nil totale e':"+masterController.getChalet().getSpiaggia().getListinoPrezzi().calcolaPrezzo(masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione)));
    }

    public void confermaAvvenutoPagamentoPrenotazione(String idPrenotazione){
        masterController.getGestoreDB().convalidaPagamentoPrenotazione(idPrenotazione);
    }

    public void getScontrinoOrdinazione(String nome) {
        masterController.getChalet().getBar().getOrdinazioneById(nome).getTotaleOrdinazione();
    }
    public void confermaAvvenutoPagamentoOrdinazione(String idOrdinazione){
        masterController.getGestoreDB().convalidaPagamentoOrdinazione(idOrdinazione);
        masterController.getChalet().getBar().getOrdinazioneById(idOrdinazione).setStatoOrdinazione(StatoPreOrd.PAGATA);
    }

    public void stampaSdraioLiberi(Date dataInizio, Date dataFine){
        masterController.getChalet().getSpiaggia().stampaSdraioLiberi(dataInizio,dataFine);
    }

    public ArrayList<Ombrellone> getListaOmbrelloni(Date dataInizio, Date dataFine) {
        return masterController.getChalet().getSpiaggia().getOmbrelloniLiberi(dataInizio,dataFine);
    }

    public void stampaSdraioLiberi(String idPrenotazione) {
        masterController.getChalet().getSpiaggia().stampaSdraioLiberi(masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).getDataInizio(),masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).getDataFine());
    }
    public void stampaOmbrelloniLiberi(String idPrenotazione){
        masterController.getChalet().getSpiaggia().stampaOmbrelloniLiberi(masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).getDataInizio(),masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).getDataFine());
    }

    public String getListaOmbrelloniLiberi(Date dataInizio, Date dataFine) {
        String result="";
        int i=0;
        for(Ombrellone ombrellone:masterController.getChalet().getSpiaggia().getOmbrelloniLiberi(dataInizio,dataFine)){
            if(i>=10) {
                i=0;
                result=result+"\n";
            }
            result = result + ombrellone.getID() + "|";
            i++;
        }
        return result;
    }

    public int getListaSdraio(Date dataInizio, Date dataFine) {
        return masterController.getChalet().getSpiaggia().getSdraioLiberi(dataInizio,dataFine).size();
    }

    public String mostraPrenotazioniCliente(String text) {
        String result="";
        for(String nomePrenotazione:masterController.getGestoreDB().getPrenotazioniCliente(text)){
            result=result+"\nnumero della prenotazione: "+nomePrenotazione+
                    "\n data inizio :"+masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getDataInizio()+
                    " data fine: "+masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getDataFine();
            if(!masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getListaOmbrelloni().isEmpty()){
                for(Ombrellone ombrellone: masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getListaOmbrelloni()){
                    result=result+"numero ombrellone prenotato: "+ombrellone.getID();
                }
            }
            if(!masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getListaSdraio().isEmpty()){
                result=result+"\nnumero sdraio affittati: "+ masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getListaSdraio().size();
            }
        }
        return result;
    }
    public String mostraOmbrelloniLiberi(String idPrenotazione){
        return getListaOmbrelloniLiberi(masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).getDataInizio(),masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).getDataFine());
    }

    public int mostraSdraioDisponibili(String identificativoPrenotazione) {
        return getListaSdraio(masterController.getChalet().getSpiaggia().getPrenotaizioneById(identificativoPrenotazione).getDataInizio(),masterController.getChalet().getSpiaggia().getPrenotaizioneById(identificativoPrenotazione).getDataFine());
    }

    public String getPrenotazioniCliente(String text) {
        String result="";

        for(String nome:masterController.getGestoreDB().getPrenotazioniCliente(text)){
            result=result+nome+"\n";
        }
        return result;
    }

    public String mostraPrenotazioniOrdinazioni(String nomeUtente) {
        String result="";
        for(String nomePrenotazione:masterController.getGestoreDB().getPrenotazioniClientedaPagare(nomeUtente)){
            result=result+"id prenotazione da pagare :"+masterController.getChalet().getSpiaggia().getPrenotaizioneById(nomePrenotazione).getID()+"\n";
        }
        for(String nomeOrdinazione:masterController.getGestoreDB().getOrdinazioneClientedaPagare(nomeUtente)){
            result=result+"id dell'ordinazione da pagare "+masterController.getChalet().getBar().getOrdinazioneById(nomeOrdinazione).getID()+"\n";
        }
        return result;
    }

    public String MostraScontrinoPrenotazione(String idPrenotazione) {
        String result="";
        result=result+"l'identificativo della prenotazione e' :"+ idPrenotazione+"\n";
        result=result+masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).stampaOmbrelloni();
        result=result+masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione).stampaSdraio();
        result=result+"\nil totale e':"+masterController.getChalet().getSpiaggia().getListinoPrezzi().calcolaPrezzo(masterController.getChalet().getSpiaggia().getPrenotaizioneById(idPrenotazione));
        return result;

    }

    public String mostraScontrinoOrdinazione(String text) {
        String result="";
        double totale=0;
        result="\nl'identificativo dell'ordinazione e' "+text;
        for(Bevanda bevanda:masterController.getChalet().getBar().getOrdinazioneById(text).getListaBevande()){
            totale+=(masterController.getChalet().getBar().getOrdinazioneById(text).getMappaBevande().get(bevanda)* bevanda.getPrezzo());
            result=result+"\nbevande :"+bevanda.getNome()+" quantita selezionata: "+masterController.getChalet().getBar().getOrdinazioneById(text).getMappaBevande().get(bevanda)+" prezzp :"+bevanda.getPrezzo();
        }
        result=result+"\n il totale e' "+totale;
        return result;
    }
}
