package com.example.progettocasotto.Model.Chalet;

import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;

public class DefaultChalet implements ChaletInterface {

    ArrayList<DefaultSpiaggia> listaSpiaggia=new ArrayList<>();
    ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
    DefaultSpiaggia spiaggia;
    DefaultBar bar;

    private String nome;

    public DefaultChalet() {

    }

    @Override
    public void addSpiaggia(DefaultSpiaggia spiaggia) {
        this.spiaggia=spiaggia;
    }

    @Override
    public void addBar(DefaultBar bar) {
        this.bar=bar;
    }


    public void setNome(String nome) {
        this.nome=nome;
    }
    public DefaultSpiaggia getSpiaggia(){
        return spiaggia;
    }

    public ArrayList<DefaultCliente> getListaClienti() {
        return listaClienti;
    }

    public DefaultCliente getClienteById(String idCliente){
        for(DefaultCliente cliente:listaClienti){
            if(cliente.getID().equals(idCliente)){
                return cliente;
            }
        }
        return null;
    }

    public DefaultBar getBar() {
        return bar;
    }
}
