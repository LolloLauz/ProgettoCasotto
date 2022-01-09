package ProgettoCasotto.Controller;

import ProgettoCasotto.Spiaggia.*;

import java.util.ArrayList;
import java.util.Random;

public class DefaultClienteController implements ClienteController{
    private Spiaggia spiaggia;

    public DefaultClienteController() {

    }

    public void setSpiaggia(Spiaggia spiaggia) {
        this.spiaggia = spiaggia;
    }

    public DefaultClienteController(Spiaggia spiaggia) {
        this.spiaggia=spiaggia;
    }

    @Override
    public void prenotaOmbrellone(int dataInizio, int dataFine) {
        Random random = new Random();
        String numeroPrenotazione=""+random.nextInt();
        if(!(spiaggia.getOmbrelloniLiberi(dataInizio,dataFine).isEmpty())){
            for(Ombrellone ombr:spiaggia.getOmbrelloniLiberi(dataInizio,dataFine)){
                System.out.println("ombrellone :"+ombr.getID());
            }
        }else{
            System.out.println("Non ci sono ombrelloni disponibili per quella data");
        }
        System.out.println("Quanti ombnrelloni si vuole prenotare");
        int numOmbre=2;
        System.out.println("SELEZIONA L'OMBRELLONE");
        spiaggia.addPrenotazione(new Prenotazione("234",dataInizio,dataFine));
        spiaggia.addOmbrelloneToPrenotazione("234","3");
        spiaggia.addOmbrelloneToPrenotazione("234","4");
        System.out.println("HO CREATO UNA PRENOTAZIONE");
    }

    @Override
    public void prenotaSdraio(int dataInizio, int dataFine) {
        if(!(spiaggia.getSdraioLiberi(dataInizio,dataFine).isEmpty())){
            for(Ombrellone ombr:spiaggia.getOmbrelloniLiberi(dataInizio,dataFine)){
                System.out.println("ombrellone :"+ombr.getID());
            }
        }else{
            System.out.println("Non ci sono ombrelloni disponibili per quella data");
        }
        System.out.println("Quanti ombnrelloni si vuole prenotare");
        int numOmbre=2;
        System.out.println("SELEZIONA L'OMBRELLONE");
        String IDsdraio="5";
        spiaggia.addPrenotazione(new Prenotazione("234",dataInizio,dataFine));
        spiaggia.addOmbrelloneToPrenotazione("234",IDsdraio);

        System.out.println("HO CREATO UNA PRENOTAZIONE");
    }

    public void pagaPrenotazione(String idPrenotazione){
        System.out.println("Esito pagamento :"+spiaggia.confermaAvvenutoPagamentoPrenotazione(spiaggia.getPrenotazioneById(idPrenotazione)));
        System.out.println("Prenotazione pagata");
    }

    public Spiaggia getSpiaggia() {
        return spiaggia;
    }
}
