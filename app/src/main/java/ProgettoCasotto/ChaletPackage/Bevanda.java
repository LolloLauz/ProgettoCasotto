package ProgettoCasotto.ChaletPackage;

import java.util.Objects;

public class Bevanda {
    private int ID;
    private String descrizione;

    public Bevanda(int ID, String descrizione) {
        this.ID = ID;
        this.descrizione = descrizione;
    }

    public int getID() {
        return ID;
    }

    public String getDescrizione() {
        return descrizione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bevanda bevanda = (Bevanda) o;
        return ID == bevanda.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
