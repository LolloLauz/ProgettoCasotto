package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultGestoreController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.Privilegio;
import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class IgestoreChalet {

    DefaultaMasterController masterController;
    DefaultGestoreController gestoreController;

    public IgestoreChalet(DefaultaMasterController masterController) {
        this.masterController = masterController;
        this.gestoreController = new DefaultGestoreController(masterController);
        Scanner scanner = new Scanner(System.in);
        System.out.println("SE E' IL TUO PRIMO ACCESSO PREMI 2");
        System.out.println("CHE COSA VUOI FARE");
        String coseDaFare = "1-inserisci nome spiaggia" +
                "\n2-crea chalet" +
                "\n3-creazione chalet di default" +
                "\n4-crea chalet di default" +
                "\n5-modifica prenotazione" +
                "\n6-modifica periodo prenotazione" +
                "\n7-crea utente" +
                "\n8-convalida pagamento" +
                "\n9-rimuovi un prenotazione" +
                "\n-invio per uscire";
        System.out.println(coseDaFare);
        String operazione = scanner.nextLine();
        while (!operazione.equals("")) {
            switch (operazione) {
                case "1":
                    System.out.println("HAI SELEZIONATO INSERISCI SPIAGGIA");
                    System.out.println("INSERISCI NOME SPIAGGIA");
                    addSpiaggia();
                    break;
                case "2":
                    System.out.println("HAI SELEZIONATO CREA CHALET ");
                    System.out.println("INSERISCI NOME CHALET");
                    creaChalet();
                    break;
                case "3":
                    System.out.println("Hai selezionato crea Bar");
                    creaBar();
                case "4":
                    creaChaletDefault();
                    System.out.println("chalet di default creato");
                    break;
                case "5":
                    modificaPrenotazione();
                    break;
                case "6":
                    modificaPeriodoPrenotazione();
                    break;
                case "7":
                    creaUtente();
                    break;
                case"8":
                    convalidaPagamento();
                    break;
                case"9":
                    removePrenotazione();
                    break;
                default:
                    break;
            }
            System.out.println("CHE COSA VUOI FARE");
            System.out.println(coseDaFare);
            operazione = scanner.nextLine();
        }
    }

    public boolean addSpiaggia() {
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        if (nome == null) {
            return false;
        }
        masterController.creaSpiaggia(nome);
        System.out.println("inserisci un numero di ombrelloni");
        String num= scanner.nextLine();
        setNumOmbrelloni(Integer.parseInt(num));
        System.out.println("inserisci un numero di sdraio");
        num=scanner.nextLine();
        setNumSdraio(Integer.parseInt(num));
        return true;
    }

    boolean creaChalet() {
        Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        if (nome == null) {
            return false;
        }
        masterController.creaChalet(nome);
        return true;
    }

    private void setNumOmbrelloni(int num) {
        masterController.getChalet().getSpiaggia().setNumeroOmbrelloni(num);
    }

    private void setNumSdraio(int num) {
        masterController.getChalet().getSpiaggia().setNumeroSdraio(num);
    }

    public boolean createListinoPrezzi() {
        //TODO: da fare
        return false;
    }

    public boolean creaBar() {
        System.out.println("Inserisci il nome del bar");
        Scanner scanner = new Scanner(System.in);
        String bar = scanner.nextLine();
        if(masterController.getChalet().getBar().getNome().equals(bar)){
            return false;
        }
        masterController.getChalet().addBar(new DefaultBar(bar));
        return false;
    }

    public boolean creaBevanda(String nome, String descrizione, int quantita, double prezzo) {
        if(masterController.getChalet().getBar().getListaBevande().contains(new Bevanda(nome,descrizione,quantita,prezzo))){
            return false;
        }
        masterController.getChalet().getBar().creaBevanda(nome, descrizione, quantita, prezzo);
        return true;
    }

    public boolean inserisciMenu() {
        //TODO: da fare
        return false;
    }

    public boolean creaAttiva(String nome, String luogo, Date dataInizio, Date dataFine, int numMassimoPersone) {
        return false;
    }

    public void creaChaletDefault() {
        masterController.creaChaletDefault();
    }

    public void creaUtente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci nome utente");
        String nome = scanner.nextLine();
        System.out.println("inserisci cognome utente");
        String cognome = scanner.nextLine();
        System.out.println("inserisci email utente");
        String email = scanner.nextLine();
        System.out.println("password di default: password");
        gestoreController.creaUtente(nome, cognome, email, "password", Privilegio.USER);
        scanner.close();
    }

    public void removePrenotazione() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci l'id della prenotazione che vuoi rimuovere");
        String idPrenotazione = scanner.nextLine();
        gestoreController.removePrenotazione(idPrenotazione);
    }


    public void convalidaPagamento() {
        System.out.println("inserisci l'id della prenotazione");
        Scanner scanner = new Scanner(System.in);
        String idPrenotazione = scanner.nextLine();
        gestoreController.convalidaPagamento(idPrenotazione);

    }

    public void modificaPrenotazione() {
        for(DefaultPrenotazione prenotazione:masterController.getChalet().getSpiaggia().getListaPrenotazioni()){
            System.out.println(prenotazione.getID());
        }
        String idPrenotazione;
        ArrayList<String> listaOmbrelloni = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci l'id della prenotazione");
        idPrenotazione = scanner.nextLine();
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            System.out.println("INSERIRE LA DATA di inizio");
            dataInizio = dateFormat.parse(scanner.nextLine());
            System.out.println("Inserire la data di fine");
            dataFine = dateFormat.parse(scanner.nextLine());
            String input = "a";
            while (!input.equals("")) {
                System.out.println("inserisci l'ombrellone da aggiungere" +
                        "\n altrimenti premi invio");
                listaOmbrelloni.add(scanner.nextLine());
            }
            System.out.println("inserisci il numero di sdriao da aggiungere");
            int numSdraio = Integer.parseInt(scanner.nextLine());
            gestoreController.modificaPrenotazione(idPrenotazione, dataInizio, dataFine, listaOmbrelloni, numSdraio);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void modificaPeriodoPrenotazione() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci id prentaione");
        String idPrenotazione = scanner.nextLine();
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            System.out.println("INSERIRE LA DATA di inizio");
            dataInizio = dateFormat.parse(scanner.nextLine());
            System.out.println("Inserire la data di fine");
            dataFine = dateFormat.parse(scanner.nextLine());
            System.out.println("inserisci il numero di sdriao da aggiungere");
            gestoreController.modificaPeriodoPrenotazione(idPrenotazione, dataInizio, dataFine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
