package com.example.progettocasotto.Model.Chalet;

import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;

public class DefaultChalet implements ChaletInterface {

    ArrayList<DefaultSpiaggia> listaSpiaggia=new ArrayList<>();
    ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
    private String nome;

    @Override
    public boolean addSpiaggia(DefaultSpiaggia spiaggia) {
        return listaSpiaggia.add(spiaggia);
    }

    public void setNome(String nome) {
        this.nome=nome;
    }
    public DefaultSpiaggia getSpiaggia(){
        return listaSpiaggia.get(0);
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
}
