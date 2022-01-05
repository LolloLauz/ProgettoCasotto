package ProgettoCasotto.ChaletPackage;

import ProgettoCasotto.Spiaggia.Stato;

import java.util.HashMap;
import java.util.Objects;

public class Ordinazione {
    private int ID;
    private HashMap<Bevanda,Integer> bevande;
    Stato statoOrdinazione;

    public Ordinazione(int ID) {
        this.ID = ID;
        bevande=new HashMap<>();
        this.statoOrdinazione=Stato.IN_ATTESA_DI_PAGAMENTO;
    }
    public void addBevanda(Bevanda bevanda,int quantita){
        bevande.put(bevanda,quantita);
    }

    public int getID() {
        return ID;
    }

    public HashMap<Bevanda, Integer> getBevande() {
        return bevande;
    }

    public void cambiaStato(){
        statoOrdinazione=Stato.CONFERMATA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordinazione that = (Ordinazione) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
    public int getQuantita(Bevanda bevanda){
        return bevande.get(bevanda);
    }
}
