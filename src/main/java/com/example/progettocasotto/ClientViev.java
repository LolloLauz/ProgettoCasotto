package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultUserController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class ClientViev {
    @FXML
    private Button prenotaSdraio;
    @FXML
    private Button prenotaAttivita;
    @FXML
    private Button prenotaOmbrellone;
    DefaultaMasterController masterController;
    DefaultUserController userController;


    public void handlePrenotaOmbrellone(ActionEvent actionEvent) {
        //apri finestra scelta ombrellone
        try{
            URL url=getClass().getResource("SceltaOmbrellone.fxml");
            System.out.println(url);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SceltaOmbrellone.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(loader.load()));
        SelectOmbrellone controller = loader.getController();
        controller.initialize();
        stage.showAndWait();
        } catch (
        IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize(DefaultaMasterController masterController,String nomeCliente) {
        System.out.println(masterController.getChalet().getSpiaggia().getNomeSpiaggia());
//        this.userController=new DefaultUserController(masterController);
        this.masterController=masterController;
        userController.setlistaClienti(masterController.getListaClienti());
        userController.setCurrentClient(nomeCliente);    }

    public void handlePrenotaSdraio(ActionEvent actionEvent) {

    }

    public void handlePrenotaAttivita(ActionEvent actionEvent) {

    }
}
