package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultUserController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.SceltaOmbrellone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClientView {
    public Button prenotaSdraio;
    public Button prenotaAttivita;
    @FXML
    private Button prenotaOmbrellone;
    DefaultaMasterController masterController;
    DefaultUserController userController;



    public void handlePrenotaOmbrellone(ActionEvent actionEvent) {
        //apri finestra scelta ombrellone
        SceltaOmbrellone sceltaOmbrellone=new SceltaOmbrellone(userController);
    }

    public void initialize(DefaultaMasterController masterController,String nomeCliente) {
//        this.userController=new DefaultUserController(masterController);
        this.masterController=masterController;
        userController.setlistaClienti(masterController.getListaClienti());
        userController.setCurrentClient(nomeCliente);    }

    public void handlePrenotaSdraio(ActionEvent actionEvent) {

    }

    public void handlePrenotaAttivita(ActionEvent actionEvent) {

    }
}
