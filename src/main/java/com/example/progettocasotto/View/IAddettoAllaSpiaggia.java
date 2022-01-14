package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.Privilegio;


import java.util.Scanner;

public class IAddettoAllaSpiaggia implements IAddettoAllaSpiaggiaView {

    AddettoASController addettoASController;

    public IAddettoAllaSpiaggia(DefaultaMasterController masterController) {
        addettoASController=new AddettoASController(masterController);

        creaUtente();
    }

    @Override
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

    @Override
    public void selezionaPeriodo() {

    }

    @Override
    public void slectOmbrellone() {

    }

    @Override
    public void selectSdraio() {

    }

    @Override
    public void insertPeriodo() {

    }

    @Override
    public void removePrenotazione() {

    }

    @Override
    public void convalidaPagamento() {

    }
}
