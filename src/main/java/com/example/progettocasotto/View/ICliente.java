package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultUserController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class ICliente {
   DefaultUserController userController;
   DefaultaMasterController masterController;


    public ICliente(DefaultaMasterController masterController, String currentUser ) {
        this.userController = new DefaultUserController(masterController,currentUser);
        this.masterController=masterController;
        userController.setlistaClienti(masterController.getListaClienti());
        userController.setCurrentClient(currentUser);
        System.out.println("1-Per prenotare l'ombrellone" +
                "\n2-per prenotare uno sdraio" +
                "\n3- prenota attivita" +
                "\n4-ordina bibita" +
                "\n5-paga un prenotazione" +
                "\n-invio per uscire");
        Scanner scanner=new Scanner(System.in);
        String i=scanner.nextLine();
        while(!i.equals("")) {
            switch (i) {
                case "1":
                    prenotaOmbrellone();
                    System.out.println("per aggiungere delle sdraio alla tua prenotazione premi una lettera qualsiasi" +
                            "\n altrimenti premi invio");
                    String input = scanner.nextLine();
                    if (input != "") {
                        System.out.println("entra");
                        prenotaSdraio(userController.getCurrentPrenotazione());
                    }
                    userController.caricaPrenotazione();
                    break;
                case "2":
                    prenotaSdraio();
                    userController.caricaPrenotazioneSdraio();
                    break;
                case "3":
                    prenotaAttivita();
                    break;
                case "4":
                    ordinaBibita();
                    break;
                case "5":
                    pagaUnaPrenotazione();
                default:
                    break;

            }
            System.out.println("1-Per prenotare l'ombrellone" +
                    "\n2-per prenotare uno sdraio" +
                    "\n3- prenota attivita" +
                    "\n4-ordina bibita" +
                    "\n5-paga un prenotazione" +
                    "\n-invio per uscire");
            i=scanner.nextLine();
        }
    }

    private void pagaUnaPrenotazione() {
        userController.getPrenotazioneCliente();
        System.out.println("inserisci l'identificativo della prenotazione che vuoi pagare");
        Scanner scanner =new Scanner(System.in);
        String idPrenotazione=scanner.nextLine();
        System.out.println("come desideri pagare la prenotazione" +
                "\n1-con carta" +
                "\n2-contanti");
        String input=scanner.nextLine();
        if(input.equals("1")){
            userController.pagaPrenotazione(idPrenotazione);
        }else{
            System.out.println("Rivolgersi al personale della spiaggia per il pagamento");
        }
    }

    private void prenotaSdraio(DefaultPrenotazione currentPrenotazione) {
        userController.mostraSdraioLiberi(currentPrenotazione.getDataInizio(),currentPrenotazione.getDataFine());
        System.out.println("inserisci il numero di sdraio che vuoi pernotare");
        Scanner scanner=new Scanner(System.in);
        String check=scanner.nextLine();
        if(check!=""){
            userController.selectNumSdraio(Integer.parseInt(check));
        }

    }

    private boolean ordinaBibita() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("--MENU-- ");
        userController.stampaMenu();
        String input="1";
        userController.creaOrdinazione();
        while(!input.equals("")) {
            System.out.println("Inserisci la bevanda che vuoi ordinare");
            String bevanda = scanner.nextLine();
            System.out.println("Inserisci la quantita");
            String quantita = scanner.nextLine();
            userController.ordinaBibita(bevanda, Integer.parseInt(quantita));
            System.out.println("1-per continuare" +
                    "\ninvio per uscire");
            input=scanner.nextLine();
        }
        userController.getQrCodeOmbrelloni();
        System.out.println("scannerizza il qr_code");
        input=scanner.nextLine();
        userController.setqrCode(input);
        userController.caricaOrdinazione();
        userController.getOrdinazione();
        pagaOrdinazione();
        return true;
    }

    private void pagaOrdinazione() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("STAMPA SCONTRINO ORDINAZIONE");
        userController.getTOtaleOrdinazione();
        System.out.println("1 per pagamento con carta" +
                "\n2 per pagamento contate");
        String input=scanner.nextLine();
        if(input.equals("1")) {
            userController.pagaOrdinazione();
            System.out.println("TRANSAZIONE ESEGUITA CON SUCCESSO");
        }else{
            System.out.println("rivolgersi al personale della spiaggia");
        }
    }

    private boolean prenotaAttivita() {
        userController.getListaAttivita();
        Scanner scanner=new Scanner(System.in);
        System.out.println("inserisci il nome dell'attivita a cui vuoi partecipare");
        String nomeAttivita=scanner.nextLine();
        if(userController.prenotaAttivita(nomeAttivita)){
            System.out.println("inserisci il numero di persone che vuoi iscrivere");
            int numPersone=Integer.parseInt(scanner.nextLine());
            if(userController.prenotaAttivita(nomeAttivita,numPersone)){
                return true;
            }
            System.out.println("ops... qualcosa sembre essere andato storto");
        }
        return false;
    }

    private boolean prenotaSdraio() {
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            Scanner scanner=new Scanner(System.in);
            System.out.println("INSERIRE LA DATA di inizio");
            dataInizio=dateFormat.parse(scanner.nextLine());
            System.out.println("Inserire la data di fine");
            dataFine=dateFormat.parse(scanner.nextLine());
            userController.mostraSdraioLiberi(dataInizio,dataFine);
            userController.prenotaSdraio(dataInizio,dataFine);
            System.out.println("inserisci il numero di sdraio che vuoi pernotare");
            String check=scanner.nextLine();
            if(check!=""){
                userController.selectNumSdraio(Integer.parseInt(check));
            }
            userController.confermaPernotazione();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean prenotaOmbrellone() {
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            Scanner scanner=new Scanner(System.in);
            System.out.println("INSERIRE LA DATA di inizio");
            dataInizio=dateFormat.parse(scanner.nextLine());
            System.out.println("Inserire la data di fine");
            dataFine=dateFormat.parse(scanner.nextLine());
            System.out.println("ecco la lista degli ombrelloni che puoi prenotare");
            userController.mostraOmbrelloniLiberi(dataInizio,dataFine);
            userController.prenotaOmbrellone(dataInizio,dataFine);
            System.out.println("Seleziona ombrellone");
            String check=scanner.nextLine();
            while(!check.equals("")){
                userController.selectOmbrellone(check);
                System.out.println("Seleziona ombrellone" +
                        "\ninvio per uscire");
                check=scanner.nextLine();
            }
            userController.confermaPernotazione();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

}
