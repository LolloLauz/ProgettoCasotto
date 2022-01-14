package com.example.progettocasotto.Controller;

import com.example.progettocasotto.DataBase.GestoreDB;
import com.example.progettocasotto.Model.Chalet.DefaultChalet;
import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;

public class DefaultaMasterController implements MasterController<DefaultGestoreController,AddettoASController,DefaultPersonaleController,DefaultUserController>{


    DefaultChalet chalet;
    DefaultUserController userController;
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
        chalet.getSpiaggia().setNumeroOmbrelloni(10);
        userController=new DefaultUserController(chalet.getSpiaggia());
        return true;

    }

    @Override
    public boolean creaBar(String nome) {
        return false;
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
}

