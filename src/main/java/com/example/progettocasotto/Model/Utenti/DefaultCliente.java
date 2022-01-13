package com.example.progettocasotto.Model.Utenti;

import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;

import java.util.ArrayList;
import java.util.Objects;

public class DefaultCliente {
    ArrayList<DefaultPrenotazione> prenotazioniAssociate=new ArrayList<>();
    private String ID;

    public DefaultCliente(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public boolean addPrenotazioneToCliente(DefaultPrenotazione prenotazione){
        return prenotazioniAssociate.add(prenotazione);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultCliente that = (DefaultCliente) o;
        return Objects.equals(ID, that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
