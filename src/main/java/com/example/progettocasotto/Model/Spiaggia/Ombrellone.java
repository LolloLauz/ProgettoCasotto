package com.example.progettocasotto.Model.Spiaggia;

import java.util.Objects;

public class Ombrellone {

    private String ID;
    private String QRCode;

    public Ombrellone(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
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
