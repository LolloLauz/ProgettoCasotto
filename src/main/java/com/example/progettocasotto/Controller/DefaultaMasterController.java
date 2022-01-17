package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.GestoreDB;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;
import com.example.progettocasotto.Model.Chalet.DefaultChalet;
import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;

public class DefaultaMasterController implements MasterController<DefaultGestoreController,AddettoASController,DefaultPersonaleController,DefaultUserController>{


    DefaultChalet chalet;
    DefaultUserController userController;
    DefaultGestoreController gestoreController;
    ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
    GestoreDB gestoreDB=new GestoreDB();

    public DefaultUserController getUserController() {
        return userController;
    }

    public DefaultaMasterController() {
        this.chalet=new DefaultChalet();
        getListaClientiFromDb();

    }

    @Override
    public boolean creaChalet(String nome) {
        chalet.setNome(nome);
        return true;
    }

    @Override
    public boolean creaSpiaggia(String nome) {
        chalet.addSpiaggia(new DefaultSpiaggia(nome));
        return true;

    }

    @Override
    public boolean creaBar(String nome) {
        chalet.addBar(new DefaultBar(nome));
        return false;
    }

    private void getListaClientiFromDb(){
        System.out.println("lista clienti caricate");
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
        chalet.setNome("chalet");
        chalet.addSpiaggia(new DefaultSpiaggia("spiaggia"));
        chalet.getSpiaggia().setNumeroSdraio(50);
        chalet.getSpiaggia().setNumeroOmbrelloni(50);
        chalet.addBar(new DefaultBar("bar"));
        chalet.getBar().creaBevanda("acqua","bottiglia da 0,5lt",10,1.00);
        chalet.getBar().creaBevanda("birra","bottiglia da 0,5lt",10,3.00);
        chalet.getBar().creaBevanda("coca-cola","bottiglia da 0,5lt",10,2.50);
    }
}

