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
        System.out.println("1-prenotazione manuale" +
                "\n2-modifica prenotazione" +
                "\n3-rimuovi prenotazione" +
                "\n4-pagamento contante" +
                "\ninvio per uscire");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.nextLine();
        switch (input){
            case "1":
                prenotazioneManuale();
                break;
            case "2":
                modificaPrenotazione();
                break;
            case "3":
                removePrenotazione();
                break;
            case "4":
                pagamentoContante();
                break;
            default:
                break;
        }
    }

    private void prenotazioneManuale() {
        String nome=creaUtente();
        Scanner scanner=new Scanner(System.in);
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
            addettoASController.stampaSdraioOmbrelloni(dataInizio,dataFine);
            while(input.equals("1") && flag){
                if(!addettoASController.getOmbrelloniLiberi(dataInizio,dataFine)){
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
                addettoASController.stampaSdraioLiberi(dataInizio,dataFine);
                if(addettoASController.getSdraioLiberi(dataInizio,dataFine)){
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
            int num=addettoASController.prenotazioneManuale(nome,dataInizio,dataFine,nomiOmbrelloni,numeroSdraio);
            if(!flag && flag2){
                System.out.println("prenotazione rimossa");
                addettoASController.removePrenotazione(String.valueOf(num));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String creaUtente() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Inserisci nome utente");
        String nome=scanner.nextLine();
        System.out.println("inserisci cognome utente");
        String cognome=scanner.nextLine();
        System.out.println("inserisci email utente");
        String email=scanner.nextLine();
        System.out.println("password di default: password");
        addettoASController.creaUtente(nome,cognome,email,"password", Privilegio.USER);
        return nome;
    }

    public void removePrenotazione() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("inserisci il nome utente");
        String nome=scanner.nextLine();
        addettoASController.getPrenotazioniCliete(nome);
        System.out.println("inserisci l'id della prenotazione che vuoi rimuovere");
        String idPrenotazione=scanner.nextLine();
        addettoASController.removePrenotazione(idPrenotazione);
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
                "\n3-Inserire altri sdraio alla prenotazione" +
                "\ninvio per uscire");
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

    private void pagamentoContante() {
        System.out.println("inserisci il nome del cliente che deve pagare");
        Scanner scanner=new Scanner(System.in);
        String nome=scanner.nextLine();
        addettoASController.gestisciPagamento(nome);
        System.out.println("1 per pagare una prenotazione" +
                "\n2 per pagare un'ordinazione");
        nome=scanner.nextLine();
        if(nome.equals("1")){
            System.out.println("inserisci l'identificativo della prentoazione");
            nome=scanner.nextLine();
            addettoASController.getScontrinoPrenotazione(nome);
            addettoASController.confermaAvvenutoPagamentoPrenotazione(nome);
        }else if(nome.equals("2")){
            System.out.println("inserisci l'identificativo dell'ordinazione");
            nome=scanner.nextLine();
            addettoASController.getScontrinoOrdinazione(nome);
            addettoASController.confermaAvvenutoPagamentoOrdinazione(nome);
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
            addettoASController.modificaPeriodoPrenotazione(idPrenotazione,dataInizio,dataFine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
