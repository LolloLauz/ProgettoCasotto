package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.GestoreDB;
import com.example.progettocasotto.DataBase.Privilegio;
import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class DefaultView {

    DefaultaMasterController masterController=new DefaultaMasterController();
    GestoreDB gestoreDB =new GestoreDB();

    public DefaultView() {
        provametodi();
    }

    private void provametodi() {
        System.out.println("BENVENUTO AL PROGETTO CASOTTO");
        String accessoRieseguito="continua";
        while(accessoRieseguito!="") {
            Scanner scanner = new Scanner(System.in);
            System.out.println("INSERISCI EMAIL UTENTE");
            String email = scanner.nextLine();
            System.out.println("INSERISCI PASSWORD");
            String password = scanner.nextLine();
            String nomeCliente = "";
            //eseguo un login e controllo che privilegio ha l'utente
            Privilegio privilegio = gestoreDB.getPrivilegio(email, password);
            switch (privilegio) {
                case SUPERADMIN:
                    nomeCliente = gestoreDB.getNomeUtente(email, password);
                    IgestoreChalet gestoreChalet = new IgestoreChalet(masterController);
                    break;
                case ADMIN:
                    IAddettoAllaSpiaggia addettoAllaSpiaggia = new IAddettoAllaSpiaggia(masterController);
                    break;
                case USER:
                    nomeCliente = gestoreDB.getNomeUtente(email, password);
                    ICliente iCliente = new ICliente(masterController.getUserController(), nomeCliente, masterController);
            }
            System.out.println("che cosa vuoi fare" );
            accessoRieseguito=scanner.nextLine();
        }
        /*masterController.stampaStatoSpiaggia();
        Date dataInizio;
        Date dataFine;
        DateFormat dateFormat=DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
        DefaultSpiaggia spiaggia=new DefaultSpiaggia("spiaggia");
        try {
            dataInizio=dateFormat.parse("10/06/20");
            dataFine=dateFormat.parse("10/06/20");
            spiaggia.addPrenotazione("123",dataInizio,dataFine);
            spiaggia.addPrenotazione("143",dataInizio,dataFine);
            spiaggia.setNumeroSdraio(10);
//            spiaggia.stampaSdraioLiberi(dataInizio,dataFine);
            spiaggia.addSdraioToPrenotazione("123",2);
            spiaggia.stampaSdraioLiberi(dataInizio,dataFine);
            spiaggia.addSdraioToPrenotazione("143",2);
            spiaggia.stampaPrenotazioni();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

    }
}