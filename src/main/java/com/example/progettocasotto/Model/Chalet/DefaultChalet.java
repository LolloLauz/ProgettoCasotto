package com.example.progettocasotto.Model.Chalet;

import com.example.progettocasotto.Model.ChaletInterface;

import java.util.ArrayList;

public class DefaultChalet implements ChaletInterface {

    ArrayList<DefaultSpiaggia> listaSpiaggia=new ArrayList<>();
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
}
