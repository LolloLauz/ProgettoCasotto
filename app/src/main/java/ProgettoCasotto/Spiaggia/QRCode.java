package ProgettoCasotto.Spiaggia;

import java.util.Random;

public class QRCode {
    private long codiceUnivoco;

    public QRCode() {
        Random random=new Random();
        this.codiceUnivoco = random.nextInt();
    }

    public long getCodiceUnivoco() {
        return codiceUnivoco;
    }
}
