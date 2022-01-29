package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.GestoreDB;
import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultOrdinazione;
import com.example.progettocasotto.Model.Chalet.DefaultAttivita;
import com.example.progettocasotto.Model.Chalet.DefaultChalet;
import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class DefaultaMasterController implements MasterController{


    DefaultChalet chalet;
    ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
    GestoreDB gestoreDB=new GestoreDB();


    public DefaultaMasterController() {
        getListaClientiFromDb();

    }

    @Override
    public boolean creaChalet(String nome) {
        chalet=new DefaultChalet(nome);
        return true;
    }

    @Override
    public boolean creaSpiaggia(String nome) {
        chalet.addSpiaggia(nome);
        return true;

    }

    @Override
    public boolean creaBar(String nome) {
        chalet.addBar(new DefaultBar(nome));
        return true;
    }

    private void getListaClientiFromDb(){
        listaClienti.addAll(gestoreDB.getListaClienti());
    }

    public ArrayList<DefaultCliente> getListaClienti() {
        return listaClienti;
    }

    public void stampaStatoSpiaggia(){
        chalet.getSpiaggia().stampaPrenotazioni();
    }

    public DefaultChalet getChalet() {
        return chalet;
    }

    public GestoreDB getGestoreDB() {
        return gestoreDB;
    }

    public void creaChaletDefault(){
        chalet=new DefaultChalet("chalet");
        chalet.addSpiaggia(gestoreDB.getNomeSpiaggia());
        chalet.getSpiaggia().setNumeroSdraio(gestoreDB.getNumeroSdraio());
        chalet.getSpiaggia().setNumeroOmbrelloni(gestoreDB.getNumeroOmbrelloni());
        chalet.getSpiaggia().getListaPrenotazioni().addAll(gestoreDB.getPrenotazioniFromDB());
        chalet.addBar(new DefaultBar("bar"));
        for(Bevanda bevanda:gestoreDB.getBevande()){
            chalet.getBar().creaBevanda(bevanda.getNome(),bevanda.getDescrizione(),bevanda.getQuantita(), bevanda.getPrezzo());
        }
        setQrCode();
        chalet.getBar().getListaOrdinazioni().addAll(getGestoreDB().getOrdinazioniDb());
        chalet.getListaAttivita().addAll(getGestoreDB().getAttivitaFromDb());
//        for(DefaultOrdinazione ordinazione:chalet.getBar().getListaOrdinazioni()){
//            System.out.println(ordinazione.getID());
//            ordinazione.stampaOrdinazione();
//        }
//        chalet.getBar().getListaBevande().addAll(getGestoreDB().getBevandeFromDB());
//        getGestoreDB().caricaOmbrelloni();
//        chalet.getSpiaggia().getPrenotaizioneById("3").setStatoPrenotazione(StatoPreOrd.PAGATA);

//        Date dataInizio;
//        Date dataFine;
//        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
//        try {
//            dataInizio=dateFormat.parse("12/10/20");
//            dataFine=dateFormat.parse("13/10/20");
//            chalet.creaAttivita(new DefaultAttivita("beach","campo",dataInizio,dataFine,30));
//            chalet.getSpiaggia().addPrenotazione("1",dataInizio,dataFine);
//            chalet.getSpiaggia().addPrenotazione("2",dataInizio,dataFine);
//            chalet.getSpiaggia().addOmbrelloneToPrenotazione("1",new Ombrellone("4"));
//            chalet.getSpiaggia().addOmbrelloneToPrenotazione("1",new Ombrellone("5"));
//            chalet.getSpiaggia().addOmbrelloneToPrenotazione("2",new Ombrellone("6"));
//            chalet.getSpiaggia().addOmbrelloneToPrenotazione("2",new Ombrellone("7"));
//            chalet.getSpiaggia().getPrenotaizioneById("1").setIdUtenteAssociato("Andrea");
//            chalet.getSpiaggia().getPrenotaizioneById("2").setIdUtenteAssociato("Luigi");
//            chalet.getSpiaggia().getPrenotaizioneById("1").setStatoPrenotazione(StatoPreOrd.PAGATA);
//            chalet.getSpiaggia().getPrenotaizioneById("2").setStatoPrenotazione(StatoPreOrd.IN_ATTESA_DI_PAGAMENTO);
//        }catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    public boolean creaAttivita(String nome, String luogo, Date dataInizio, Date dataFine, int numMassimoPersone) {
        getGestoreDB().creaAttivita(nome,luogo,dataInizio,dataFine,numMassimoPersone);
        return chalet.creaAttivita(new DefaultAttivita(nome,luogo,dataInizio,dataFine,numMassimoPersone));
    }

    public boolean addPartecipantiToAttivita(String nomeAttivita, int numPersone) {

        return chalet.addPartecipantiToAttivta(nomeAttivita,numPersone);
    }

    public void stampaListaAttivita() {
        for(DefaultAttivita attivita:chalet.getListaAttivita()){
            System.out.println("nome attivita: "+attivita.getNome());
        }
    }
    public void setQrCode(){
        for(Ombrellone ombrellone:chalet.getSpiaggia().getListaOmbrelloni()){
            ombrellone.setQRCode(getGestoreDB().getqrCode(ombrellone.getID()));
        }
    }
}

