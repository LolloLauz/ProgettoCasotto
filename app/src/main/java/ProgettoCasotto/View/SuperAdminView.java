package ProgettoCasotto.View;

import ProgettoCasotto.Controller.MainController;

import java.util.Scanner;

public class SuperAdminView {
        MainController mainController;

    public SuperAdminView(MainController mainController) {
        this.mainController = mainController;
        System.out.println("1-creaChalet\n" +
                "2-creaSpiaggia");
        creaChalet();
        System.out.println("Chalet creato con SUCESSO");
        creaSpiaggia();
    }

    public void creaChalet(){
        System.out.println("Inserisci nome dello chalet");
        Scanner scanner=new Scanner(System.in);
        String nomeChale=scanner.nextLine();

        mainController.createChalet(nomeChale);
    }

    public void creaSpiaggia(){
        System.out.println("Inserisci nome della spiaggia");
        Scanner scanner=new Scanner(System.in);
        String nomeSpiaggia= scanner.nextLine();
        mainController.addSpiaggia(nomeSpiaggia);
    }

    public void StampaPrenotazioni(){
        mainController.getStatoPrenotazioniSpiaggia();
    }
    //metodo per creare un spiggia in modo automatico con dati gia' pre-inseriti
    public void CreazioneSpiaggiaDefault(){

        mainController.createChalet("Chalet");
        mainController.addSpiaggia("Spiaggia");
        mainController.addBar("Bar");
        mainController.addAreaAttivita("Campo da beachvolley");

    }
}
