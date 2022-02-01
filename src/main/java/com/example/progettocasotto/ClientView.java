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

public class ClientView {
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SceltaOmbrellone.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(loader.load()));
        SelectOmbrellone controller = loader.getController();
        controller.initialize(userController);
        stage.showAndWait();
        } catch (
        IOException e) {
            e.printStackTrace();
        }

    }

    public void initialize(DefaultaMasterController masterController,String nomeCliente) {
        this.userController=new DefaultUserController(masterController,nomeCliente);
        this.masterController=masterController;
        userController.setlistaClienti(masterController.getListaClienti());
        userController.setCurrentClient(nomeCliente);
    }

    public void handlePrenotaSdraio(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SceltaSdraio.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            SceltaSdraioView controller = loader.getController();
            controller.initialize(userController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void handlePrenotaAttivita(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrenotaAttivita.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            PrenotaAttivitaView controller = loader.getController();
            controller.initialize(userController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    public void handleOrdinaDalBar(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrdinazioneBar.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            OrdinazioneBarView controller = loader.getController();
            controller.initialize(userController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void handlePagaUnaPrenotazione(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PagaUnaPrenotazione.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            PagaUnaPrenotazioneView controller = loader.getController();
            controller.initialize(userController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
