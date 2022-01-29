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
        while(!accessoRieseguito.equals("")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("INSERISCI EMAIL UTENTE");
            String email = scanner.nextLine();
            System.out.println("INSERISCI PASSWORD");
            String password = scanner.nextLine();
            String nomeCliente = "";
            Privilegio privilegio = gestoreDB.getPrivilegio(email, password);
            switch (privilegio) {
                case SUPERADMIN:
                    IgestoreChalet gestoreChalet = new IgestoreChalet(masterController);
                    break;
                case ADMIN:
                    IAddettoAllaSpiaggia addettoAllaSpiaggia = new IAddettoAllaSpiaggia(masterController);
                    break;
                case USER:
                    nomeCliente = gestoreDB.getNomeUtente(email, password);
                    ICliente iCliente = new ICliente(masterController, nomeCliente);
            }
            System.out.println("1 per un nuovo accesso " +
                    "\nINVIO per uscire");
            accessoRieseguito=scanner.nextLine();
        }
        masterController.stampaStatoSpiaggia();
    }

    public void testMetodi(){
        DefaultBar bar=new DefaultBar("bar");
        bar.creaBevanda("acqua","bottiglia d'acqua da 0,5lt",10,1.00);
        bar.creaOrdinazione("123");
        bar.selezionaBevanda("123","acqua",40);
    }
}