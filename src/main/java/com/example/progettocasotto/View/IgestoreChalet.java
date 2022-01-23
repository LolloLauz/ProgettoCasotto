package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultGestoreController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.Privilegio;
import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;

import java.nio.charset.StandardCharsets;
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
        String coseDaFare = "1-crea chalet" +
                "\n2-crea spiaggia" +
                "\n3-crea bar" +
                "\n4-modifica prenotazione" +
                "\n5-prenotazione manuale" +
                "\n6-pagamento contanti" +
                "\n7-rimuovi un prenotazione" +
                "\n8-creazione attivita" +
                "\n-invio per uscire";
        System.out.println(coseDaFare);
        String operazione = scanner.nextLine();
        while (!operazione.equals("")) {
            switch (operazione) {
                case "1":
                    System.out.println("HAI SELEZIONATO CREA CHALET");
                    System.out.println("INSERISCI NOME CHALET");
                    creaChalet();
                    break;
                case "2":
                    System.out.println("HAI SELEZIONATO CREA SPIAGGIA ");
                    System.out.println("INSERISCI NOME SPIAGGIA");
                    addSpiaggia();
                    break;
                case "3":
                    System.out.println("Hai selezionato crea Bar");
                    creaBar();
                case "4":
                    modificaPrenotazione();
                    break;
                case "5":
                    prenotazioneManuale();
                    break;
                case"6":
                    pagamentoContante();
                    break;
                case"7":
                    removePrenotazione();
                    break;
                case "8":
                    crazioneAttivita();

                default:
                    break;
            }
            System.out.println("CHE COSA VUOI FARE");
            System.out.println(coseDaFare);
            operazione = scanner.nextLine();
        }
    }

    private void pagamentoContante() {
        System.out.println("inserisci il nome del cliente che deve pagare");
        Scanner scanner=new Scanner(System.in);
        String nome=scanner.nextLine();
        gestoreController.gestisciPagamento(nome);
        System.out.println("1 per pagare una prenotazione" +
                "\n2 per pagare un'ordinazione");
        nome=scanner.nextLine();
        if(nome.equals("1")){
            System.out.println("inserisci l'identificativo della prentoazione");
            nome=scanner.nextLine();
            gestoreController.getScontrinoPrenotazione(nome);
            gestoreController.confermaAvvenutoPagamentoPrenotazione(nome);
        }else if(nome.equals("2")){
            System.out.println("inserisci l'identificativo della prenotazione");
            nome=scanner.nextLine();
            gestoreController.getScontrinoOrdinazione(nome);
            gestoreController.confermaAvvenutoPagamentoOrdinazione(nome);
        }
    }

    private void crazioneAttivita() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("inserisci un nume per l'attivita");
        String nome=scanner.nextLine();
        System.out.println("inserisci il luogo in cui verra svolta l'attivita");
        String luogo=scanner.nextLine();
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            System.out.println("inserisci la data di inizio dell'attivita");
            dataInizio = dateFormat.parse(scanner.nextLine());
            System.out.println("inserisci la data di fine attivita");
            dataFine = dateFormat.parse(scanner.nextLine());
            System.out.println("inserisci il numero massimo di persone che possono partecipare");
            int numMassimoPersone=Integer.parseInt(scanner.nextLine());
            masterController.creaAttivita(nome,luogo,dataInizio,dataFine,numMassimoPersone);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        masterController.stampaListaAttivita();
    }

    private void prenotazioneManuale() {
        System.out.println("inserisci il nome del cliente");
        Scanner scanner=new Scanner(System.in);
        String nome=scanner.nextLine();
        System.out.println("inserisci cognome utente");
        String cognome=scanner.nextLine();
        System.out.println("inserisci un email");
        String email=scanner.nextLine();
        System.out.println("inserisci un password");
        String password=scanner.nextLine();
        gestoreController.creaUtente(nome,cognome,email,password,Privilegio.USER);
        ArrayList<String> nomiOmbrelloni=new ArrayList<>();
        int numeroSdraio=0;
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            System.out.println("Inserire la data di inizio della prenotazione");
            dataInizio=dateFormat.parse(scanner.nextLine());
            System.out.println("Inserire la data di fine prenotazione");
            dataFine=dateFormat.parse(scanner.nextLine());
            System.out.println("1 se vuoi inserire degli ombrelloni" +
                    "\n2 se vuoi inserire degli sdraio");
            String input=scanner.nextLine();
            boolean flag=true;
            gestoreController.stampaSdraioLiberi(dataInizio,dataFine);
            while(input.equals("1") && flag){
                if(!gestoreController.getOmbrelloniLiberi(dataInizio,dataFine)){
                    System.out.println("non ci sono ombrelloni disponibili");
                    System.out.println("vuoi rimuovere la prenotazione");
                    input=scanner.nextLine();
                    if(input.equals("si")){
                        flag=false;
                    }
                }else {
                    System.out.println("inserisci il numero di ombrellone che vuoi aggiungere");
                    nomiOmbrelloni.add(scanner.nextLine());
                    System.out.println("1 se vuoi inserire degli ombrelloni" +
                            "\n2 se vuoi inserire degli sdraio");
                    input = scanner.nextLine();
                }
            }
            boolean flag2=true;
            if(input.equals("2")){
                gestoreController.stampaSdraioLiberi(dataInizio,dataFine);
                if(gestoreController.getSdraioLiberi(dataInizio,dataFine)){
                System.out.println("inserisci il numero di sdraio");
                input=scanner.nextLine();
                numeroSdraio = Integer.parseInt(input);
                }else{
                    System.out.println("non ci sono sdriao disponibili");
                    input=scanner.nextLine();
                    if(input.equals("si")){
                        flag2=false;
                    }
                }
            }
            if(!flag && flag2){
                System.out.println("prenotazione rimossa");
//                gestoreController.removePrenotazione();
            }
            gestoreController.prenotazioneManuale(nome,dataInizio,dataFine,nomiOmbrelloni,numeroSdraio);
        } catch (ParseException e) {
            e.printStackTrace();
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


    public boolean creaAttiva() {
        System.out.println("inserisci il nome dell'attivita");
        Scanner scanner=new Scanner(System.in);
        String nome=scanner.nextLine();
        System.out.println("inserisci il luogo dove si svolgera l'attivita");
        String luogo=scanner.nextLine();
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        try {
            System.out.println("Inserire la data di inizio");
            dataInizio=dateFormat.parse(scanner.nextLine());
            System.out.println("Inserire la data di fine");
            dataFine=dateFormat.parse(scanner.nextLine());
            System.out.println("inserisci il numero di sdraio che vuoi pernotare");
            String check=scanner.nextLine();
            System.out.println("inserisci un numero massimo di persone che possono partecipare");
            int numMassimoPersone=Integer.parseInt(scanner.nextLine());
            return masterController.creaAttivita(nome,luogo,dataInizio,dataFine,numMassimoPersone);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
    }

    public void removePrenotazione() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("inserisci nome utente");
        String nome=scanner.nextLine();
        gestoreController.getPrenotazioniCliete(nome);
        System.out.println("inserisci l'id della prenotazione che vuoi rimuovere");
        String idPrenotazione = scanner.nextLine();
        gestoreController.removePrenotazione(idPrenotazione);
    }


    public void modificaPrenotazione() {
        System.out.println("inserisci il nome utente a cui modificare la prenotazione");
        Scanner scanner = new Scanner(System.in);
        String nomeUtente=scanner.nextLine();
        gestoreController.getPrenotazioniCliete(nomeUtente);
        String idPrenotazione;
        ArrayList<String> listaOmbrelloni = new ArrayList<>();
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
        }
//        Date dataInizio;
//        Date dataFine;
//        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
//        try {
//            System.out.println("INSERIRE LA DATA di inizio");
//            dataInizio = dateFormat.parse(scanner.nextLine());
//            System.out.println("Inserire la data di fine");
//            dataFine = dateFormat.parse(scanner.nextLine());
//            String input = "a";
//            while (!input.equals("")) {
//                System.out.println("inserisci l'ombrellone da aggiungere" +
//                        "\n altrimenti premi invio");
//                input=scanner.nextLine();
//                listaOmbrelloni.add(input);
//            }
//            System.out.println("inserisci il numero di sdraio da aggiungere");
//            int numSdraio = Integer.parseInt(scanner.nextLine());
//            gestoreController.modificaPrenotazione(idPrenotazione, dataInizio, dataFine, listaOmbrelloni, numSdraio);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    private void inserimentoSdriao(String idPrenotazione) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("inserisci il numero di sdraio da aggiungere");
        int numSdraio = Integer.parseInt(scanner.nextLine());
        gestoreController.inserimentoSdraio(idPrenotazione,numSdraio);
    }

    private void inserimentoOmbrelloni(String idPrenotazione) {
        ArrayList<String> listaOmbrelloni = new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        String input = "a";
        System.out.println("inserisci l'ombrellone da aggiungere" +
                "\n altrimenti premi invio");
        input=scanner.nextLine();
        while (!input.equals("")) {
            listaOmbrelloni.add(input);
            System.out.println("inserisci l'ombrellone da aggiungere" +
                    "\n altrimenti premi invio");
            input=scanner.nextLine();
        }
        gestoreController.inserimentoOmbrelloni(idPrenotazione,listaOmbrelloni);
    }

    private void modificaPeriodoPrenotazione(String idPrenotazione) {
        Scanner scanner = new Scanner(System.in);
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
