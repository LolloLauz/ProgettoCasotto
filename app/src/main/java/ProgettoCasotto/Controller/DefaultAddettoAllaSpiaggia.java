package ProgettoCasotto.Controller;

import ProgettoCasotto.Spiaggia.Prenotazione;
import ProgettoCasotto.Spiaggia.Spiaggia;

public class DefaultAddettoAllaSpiaggia {
    Spiaggia spiaggia;


    public void setSpiaggia(Spiaggia spiaggia){
        this.spiaggia=spiaggia;
    }

    public void confermaPagamento(){
        System.out.println("Inserisco la prenotazione da pagare");
        spiaggia.confermaAvvenutoPagamentoPrenotazione(spiaggia.getPrenotazioneById("234"));
    }
}
