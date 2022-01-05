package ProgettoCasotto.Spiaggia;

import java.util.Objects;

public class Ombrellone {
    String nome;
    String ID;
    private QRCode QRCode;


    public Ombrellone(String ID){
        this.ID=ID;
        QRCode=new QRCode();
    }

    public String getID() {
        return ID;
    }

    public ProgettoCasotto.Spiaggia.QRCode getQRCode() {
        return QRCode;
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
