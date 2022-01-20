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
            case "4":
                removePrenotazione();
                break;
            case "5":
                convalidaPagamento();
                break;
            default:
                break;
        }
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
        System.out.println("inserisci il nome utente a cui modificare la prenotazione");
        Scanner scanner = new Scanner(System.in);
        String nomeUtente=scanner.nextLine();
        addettoASController.getPrenotazioniCliete(nomeUtente);
        String idPrenotazione;
        System.out.println("inserisci l'id della prenotazione");
        idPrenotazione = scanner.nextLine();
        System.out.println("che cosa vuoi fare " +
                "\n1-Modificare il periodo della prenotazione" +
                "\n2-Inserire altri ombrelloni alla prenotazione" +
                "\n3-Inserire altri sdraio alla prenotazione");
        String scelta=scanner.nextLine();
        switch (scelta){
            case "1":
                modificaPeriodoPrenotazione(idPrenotazione);
                break;
            case "2":
                inserimentoOmbrelloni(idPrenotazione);
                break;
            case "3":
                inserimentoSdriao(idPrenotazione);
                break;
            default:
                break;
        }
    }

    private void inserimentoSdriao(String idPrenotazione) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("inserisci il numero di sdraio da aggiungere");
        int numSdraio = Integer.parseInt(scanner.nextLine());
        addettoASController.inserimentoSdraio(idPrenotazione,numSdraio);
    }

    private void inserimentoOmbrelloni(String idPrenotazione) {
        ArrayList<String> listaOmbrelloni = new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        String input = "a";
        while (!input.equals("")) {
            System.out.println("inserisci l'ombrellone da aggiungere" +
                    "\n altrimenti premi invio");
            input=scanner.nextLine();
            listaOmbrelloni.add(input);
        }
        addettoASController.inserimentoOmbrelloni(idPrenotazione,listaOmbrelloni);
    }

    public void modificaPeriodoPrenotazione(String idPrenotazione){
        Scanner scanner=new Scanner(System.in);
        System.out.println("inserisci id prentaione");
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
