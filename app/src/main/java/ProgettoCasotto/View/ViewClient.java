package ProgettoCasotto.View;


import ProgettoCasotto.Controller.DefaultClienteController;
import ProgettoCasotto.Spiaggia.Prenotazione;
import ProgettoCasotto.Spiaggia.Spiaggia;

import java.util.Random;
import java.util.Scanner;

public class ViewClient {
    DefaultClienteController defaultClienteController;
    Spiaggia spiaggia;
    Scanner scanner;
    int i=1;
    public ViewClient(DefaultClienteController defaultClienteController) {
        this.defaultClienteController=defaultClienteController;
        spiaggia=defaultClienteController.getSpiaggia();
        System.out.println("1-PRENOTA OMBRELLONE");
        System.out.println("2-PRENOTA SDRAIO");
        System.out.println("3-ORDINA BAR");

        switch (i){
            case 1:
                System.out.println("HAI SELEZIONATO PRENOTA OMBRELLONE");
                prenotaOmbrellone();
                break;
            default:
                break;
        }
        pagaPrenotazione("234");
    }
/*
metodo che si attiva tramite la scelta dell'utente
 */
    private void prenotaOmbrellone() {
//        Random random=new Random();
//        String nome=""+random.nextInt(100);
        Scanner scanner=new Scanner(System.in);
        System.out.println("INSERISCI DATA INIZIO");
        int dataInizio=scanner.nextInt();
        System.out.println("INSERISCI DATA FINE");
        int dataFine=scanner.nextInt();
        defaultClienteController.prenotaOmbrellone(dataInizio,dataFine);
//        Prenotazione prenotazione=new Prenotazione(nome,dataInizio,dataFine);
//        spiaggia.addPrenotazione(prenotazione);
//        System.out.println(spiaggia.addOmbrelloneToPrenotazione(nome,"4"));
//        spiaggia.confermaAvvenutoPagamentoPrenotazione(prenotazione);
    }

    private void pagaPrenotazione(String idPrenotazione){
        defaultClienteController.pagaPrenotazione(idPrenotazione);
    }
}
