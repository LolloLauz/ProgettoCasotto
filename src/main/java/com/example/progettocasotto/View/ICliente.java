package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultUserController;
import com.example.progettocasotto.Controller.DefaultaMasterController;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class ICliente {
   DefaultUserController userController;
   DefaultaMasterController masterController;


    public ICliente(DefaultUserController userController, String currentUser, DefaultaMasterController masterController) {
        this.userController = userController;
        this.masterController=masterController;
        userController.setlistaClienti(masterController.getListaClienti());
        userController.setCurrentClient(currentUser);
        Scanner scanner=new Scanner(System.in);
        int i=scanner.nextInt();
        switch (i){
            case 1:
                prenotaOmbrellone();
                break;
            case 2:
                prenotaSdraio();
                break;
            case 3:
                prenotaAttivita();
                break;
            case 4:
                ordinaBibita();
                break;
            default:
                break;
        }
    }

    private boolean ordinaBibita() {
        return false;
    }

    private boolean prenotaAttivita() {
        return false;
    }

    private boolean prenotaSdraio() {
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
            userController.mostraStatoSpiaggia(dataInizio,dataFine);
            userController.prenotaOmbrellone(dataInizio,dataFine);
            System.out.println("Seleziona ombrellone");
            String check=scanner.nextLine();
            while(check!=""){
                System.out.println("Seleziona ombrellone");
                userController.selectOmbrellone(check);
                check=scanner.nextLine();
            }
            userController.confermaPernotazione();
            return true;
//            userController.prenotaOmbrellone(dataInizio,dataFine);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

}
