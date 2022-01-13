package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.GestoreDB;
import com.example.progettocasotto.DataBase.Privilegio;

public class DefaultView {

    DefaultaMasterController masterController=new DefaultaMasterController();
    GestoreDB gestoreDB =new GestoreDB();

    public DefaultView() {
        provametodi();
    }

    private void provametodi() {
        masterController.creaChalet("nome");
        masterController.creaSpiaggia("spiaggia");
//        prendo la lista dei clienti dal database
        String nomeCliente="";
        //eseguo un login e controllo che privilegio ha l'utente
        if(Privilegio.USER== gestoreDB.getPrivilegio("aaa@gmail.com","aaaa")){
            //TODO : metodo per ottenere l'ID di un cliente
            nomeCliente=gestoreDB.getNomeUtente("aaa@gmail.com","aaaa");
        }
        ICliente iCliente=new ICliente(masterController.getUserController(),nomeCliente,masterController);
        masterController.stampaStatoSpiaggia();
    }
}
