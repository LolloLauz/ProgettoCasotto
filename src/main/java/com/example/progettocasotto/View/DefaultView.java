package com.example.progettocasotto.View;


import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.GestoreDB;
import com.example.progettocasotto.DataBase.Privilegio;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;
import com.example.progettocasotto.Model.Chalet.DefaultChalet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Scanner;


public class DefaultView {

    DefaultaMasterController masterController=new DefaultaMasterController();
    GestoreDB gestoreDB =new GestoreDB();

    public DefaultView() {

//        testMetodi();
        provametodi();
    }

    private void provametodi() {
        System.out.println("BENVENUTO AL PROGETTO CASOTTO");
        String accessoRieseguito="c";
        masterController.creaChaletDefault();
        masterController.stampaStatoSpiaggia();
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
                    System.out.println(masterController.getChalet().getSpiaggia().getNomeSpiaggia());
                    IgestoreChalet gestoreChalet = new IgestoreChalet(masterController);
                    break;
                case ADMIN:
                    IAddettoAllaSpiaggia addettoAllaSpiaggia = new IAddettoAllaSpiaggia(masterController);
                    break;
                case USER:
                    nomeCliente = gestoreDB.getNomeUtente(email, password);
//                    ClientView clientView=new ClientView(masterController,nomeCliente);
                    ICliente iCliente = new ICliente(masterController, nomeCliente);
            }
            System.out.println("per eseguire un nuovo accesso inserire premi una lettera qualsiasi " +
                    "\n per uscire premi invio" );
            accessoRieseguito=scanner.nextLine();
        }
        masterController.stampaStatoSpiaggia();
    }

    public void testMetodi(){
//        DefaultChalet chalet=new DefaultChalet();
//        chalet.setNome("chalet");
        DefaultBar bar=new DefaultBar("bar");
        bar.creaBevanda("acqua","bottiglia d'acqua da 0,5lt",10,1.00);
        bar.creaOrdinazione("123");
        bar.selezionaBevanda("123","acqua",40);
//        bar.stampaListaPrenotazioni();
    }
}