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

