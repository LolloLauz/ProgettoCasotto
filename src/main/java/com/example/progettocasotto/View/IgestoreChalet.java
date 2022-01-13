package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;

import java.util.Date;
import java.util.Scanner;

public class IgestoreChalet {

    DefaultaMasterController masterController;

    public IgestoreChalet(DefaultaMasterController masterController) {
        this.masterController = masterController;
        Scanner scanner=new Scanner(System.in);
        System.out.println("SE E' IL TUO PRIMO ACCESSO PREMI 2");
        System.out.println("CHE COSA VUOI FARE");
        String operazione=scanner.nextLine();
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
                default:
                    break;
            }
            System.out.println("CHE COSA VUOI FARE");
            operazione= scanner.nextLine();
        }
        System.out.println("AVANTO");
    }

    public boolean addSpiaggia(){
        Scanner scanner=new Scanner(System.in);
        String nome =scanner.nextLine();
        if(nome==null){
            return false;
        }
        masterController.creaSpiaggia(nome);
        return true;
    }

    boolean creaChalet(){
        Scanner scanner=new Scanner(System.in);
        String nome =scanner.nextLine();
        if(nome==null){
            return false;
        }
        masterController.creaChalet(nome);
        return true;
    }

    void setNumOmbrelloni(int num){
        masterController.getChalet().getSpiaggia().setNumeroOmbrelloni(num);
    }

    void setNumSdraio(int num){
        masterController.getChalet().getSpiaggia().setNumeroSdraio(num);
    }

    public boolean createListinoPrezzi(){
        //TODO: da fare
        return false;
    }

    public boolean creaBar(DefaultBar bar){
        return false;
    }

    public boolean creaMenu(){
        //TODO : da fare
        return false;
    }

    public boolean inserisciMenu(){
        //TODO: da fare
        return false;
    }

    public boolean creaAttiva(String nome, String luogo, Date dataInizio, Date dataFine, int numMassimoPersone){
        return false;
    }
}
