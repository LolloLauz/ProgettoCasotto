package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.GestoreDB;
import com.example.progettocasotto.DataBase.Privilegio;

import java.util.Scanner;

public class DefaultView {

    DefaultaMasterController masterController=new DefaultaMasterController();
    GestoreDB gestoreDB =new GestoreDB();

    public DefaultView() {
        provametodi();
    }

    private void provametodi() {
        masterController.creaChalet("nome");
        masterController.creaSpiaggia("spiaggia");
        Scanner scanner=new Scanner(System.in);
        System.out.println("INSERISCI EMAIL UTENTE");
        String email=scanner.nextLine();
        System.out.println("INSERISCI PASSWORD");
        String password=scanner.nextLine();
//        prendo la lista dei clienti dal database
        String nomeCliente="";
        //eseguo un login e controllo che privilegio ha l'utente
        Privilegio privilegio=gestoreDB.getPrivilegio(email,password);
        if(Privilegio.SUPERADMIN==privilegio){
            nomeCliente=gestoreDB.getNomeUtente(email,password);
            IgestoreChalet gestoreChalet=new IgestoreChalet(masterController);
        }
        if(Privilegio.USER== privilegio){
            //TODO : metodo per ottenere l'ID di un cliente
            nomeCliente=gestoreDB.getNomeUtente(email,password);
            ICliente iCliente=new ICliente(masterController.getUserController(),nomeCliente,masterController);
        }
        masterController.stampaStatoSpiaggia();
    }
}