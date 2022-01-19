package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.Privilegio;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class IAddettoAllaSpiaggia{

    AddettoASController addettoASController;

    public IAddettoAllaSpiaggia(DefaultaMasterController masterController) {
        addettoASController=new AddettoASController(masterController);

        System.out.println("1-Inserisci utente" +
                "\n2-modifica prenotazione" +
                "\n3-modifica periodo prenotazione" +
                "\n4-rimuovi prenotazione" +
                "\5-conferma pagamento");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.nextLine();
        switch (input){
            case "1":
                creaUtente();
                break;
            case "2":
                modificaPrenotazione();
                break;
            case "3":
                modificaPeriodoPrenotazione();
                break;
            case "4":
                removePrenotazione();
                break;
            case "5":
                convalidaPagamento();
                break;
            default:
                break;
        }
        creaUtente();
    }

    public void creaUtente() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Inserisci nome utente");
        String nome=scanner.nextLine();
        System.out.println("inserisci cognome utente");
        String cognome=scanner.nextLine();
        System.out.println("inserisci email utente");
        String email=scanner.nextLine();
        System.out.println("password di default: password");
        addettoASController.creaUtente(nome,cognome,email,"password", Privilegio.USER);
        scanner.close();
    }

    public void removePrenotazione() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("inserisci l'id della prenotazione che vuoi rimuovere");
        String idPrenotazione=scanner.nextLine();
        addettoASController.removePrenotazione(idPrenotazione);
    }


    public void convalidaPagamento() {
        System.out.println("inserisci l'id della prenotazione");
        Scanner scanner=new Scanner(System.in);
        String idPrenotazione=scanner.nextLine();
        addettoASController.convalidaPagamento(idPrenotazione);

    }

    public void modificaPrenotazione(){
        System.out.println("inserisci il nome utente a cui si vuole modificare una prentoazione");
        Scanner scanner=new Scanner(System.in);
        String nomeCliente=scanner.nextLine();
        addettoASController.getPrenotazioniCliete(nomeCliente);
        String idPrenotazione;
        ArrayList<String> listaOmbrelloni=new ArrayList<>();
        System.out.println("inserisci l'id della prenotazione");
        idPrenotazione=scanner.nextLine();
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            System.out.println("INSERIRE LA DATA di inizio");
            dataInizio=dateFormat.parse(scanner.nextLine());
            System.out.println("Inserire la data di fine");
            dataFine=dateFormat.parse(scanner.nextLine());
            String input="a";
            while(!input.equals("")){
                System.out.println("inserisci l'ombrellone da aggiungere" +
                        "\n altrimenti premi invio");
                listaOmbrelloni.add(scanner.nextLine());
            }
            System.out.println("inserisci il numero di sdriao da aggiungere");
            int numSdraio=Integer.parseInt(scanner.nextLine());

            addettoASController.modificaPrenotazione(idPrenotazione,dataInizio,dataFine,listaOmbrelloni,numSdraio);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void modificaPeriodoPrenotazione(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("inserisci id prentaione");
        String idPrenotazione=scanner.nextLine();
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            System.out.println("INSERIRE LA DATA di inizio");
            dataInizio=dateFormat.parse(scanner.nextLine());
            System.out.println("Inserire la data di fine");
            dataFine=dateFormat.parse(scanner.nextLine());
            System.out.println("inserisci il numero di sdriao da aggiungere");
            addettoASController.modificaPeriodoPrenotazione(idPrenotazione,dataInizio,dataFine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
