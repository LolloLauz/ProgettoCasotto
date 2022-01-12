package com.example.progettocasotto.Model.Spiaggia;

import java.util.Objects;

public class Ombrellone {

    private String ID;

    public Ombrellone(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ombrellone that = (Ombrellone) o;
        return Objects.equals(ID, that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
