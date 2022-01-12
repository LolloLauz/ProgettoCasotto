package com.example.progettocasotto.Model.Chalet.Bar;

import java.util.ArrayList;
import java.util.Objects;

public class DefaultBar implements BarInterface{

    private String nome;
    private ArrayList<DefaultOrdinazione> listaOrdinazioni;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultBar that = (DefaultBar) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean selezionaBevanda(Bevanda bevanda) {
        //TODO :metodo ancora da implementare
        return true;
    }

    private boolean creaOrdinazione(DefaultOrdinazione ordinazione){
        return listaOrdinazioni.add(ordinazione);
    }
}
