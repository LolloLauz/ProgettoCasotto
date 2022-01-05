package ProgettoCasotto.Spiaggia;

import java.util.Objects;

public class Sdraio {
    String ID;
    public Sdraio(String ID){
        this.ID=ID;
    }

    public String getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sdraio sdraio = (Sdraio) o;
        return Objects.equals(ID, sdraio.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
