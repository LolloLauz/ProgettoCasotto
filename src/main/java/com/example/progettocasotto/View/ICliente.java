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
        this.userController = new DefaultUserController(masterController);
        this.masterController=masterController;
        userController.setlistaClienti(masterController.getListaClienti());
        userController.setCurrentClient(currentUser);
        System.out.println("1-Per prenotare l'ombrellone" +
                "\n2-per prenotare uno sdraio" +
                "\n3- prenota attivita" +
                "\n4-ordina bibita" +
                "\n-invio per uscire");
        Scanner scanner=new Scanner(System.in);
        String i=scanner.nextLine();
        switch (i){
            case "1":
                prenotaOmbrellone();
                System.out.println("per aggiungere delle sdraio alla tua prenotazione premi una lettera qualsiasi" +
                        "\n altrimenti premi invio");
                String input=scanner.nextLine();
                if(input!=""){
                    System.out.println("entra");
                    prenotaSdraio(userController.getCurrentPrenotazione());
                }
                break;
            case "2":
                prenotaSdraio();
                break;
            case "3":
                prenotaAttivita();
                break;
            case "4":
                ordinaBibita();
                break;
            default:
                break;
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
        userController.confermaPernotazione();
    }

    private boolean ordinaBibita() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("--MENU-- ");
        userController.stampaMenu();
        System.out.println("Inserisci la bevanda che vuoi ordinare");
        String bevanda=scanner.nextLine();
        System.out.println("Inserisci la quantita");
        int quantita=scanner.nextInt();
        userController.ordinaBibita(bevanda,quantita);
        userController.getOrdinazione();
        return true;
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
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
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
            userController.mostraOmbrelloniLiberi(dataInizio,dataFine);
            userController.prenotaOmbrellone(dataInizio,dataInizio);
            System.out.println("Seleziona ombrellone");
            String check=scanner.nextLine();
            while(check!=""){
                userController.selectOmbrellone(check);
                System.out.println("Seleziona ombrellone");
                check=scanner.nextLine();
            }
            userController.confermaPernotazione();
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

}
