package com.example.progettocasotto;

import com.example.progettocasotto.*;
import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultGestoreController;
import com.example.progettocasotto.Controller.DefaultPersonaleController;
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

public class FXGestoreChalet {


    DefaultaMasterController masterController;
    DefaultGestoreController gestoreController;
    DefaultPersonaleController personaleController;

    public void initialize(DefaultaMasterController masterController){
        this.masterController=masterController;
        this.personaleController=new DefaultGestoreController(masterController);
        this.gestoreController=new DefaultGestoreController(masterController);
    }
//    public void handleDefaultChalet(ActionEvent actionEvent) {
//        masterController.creaChaletDefault();
//    }

    public void handlePagamentoContanti(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PagamentoContante.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            PagamentoContanteView controller = loader.getController();
            controller.initialize(gestoreController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void handleEliminaPrenotazione(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EliminaPrenotazione.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            EliminaPrenotazioneView controller = loader.getController();
            controller.initialize(gestoreController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void handleModificaPrenotazione(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificaPrenotazione.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            ModificaPrenotazioneView controller = loader.getController();
            controller.initialize(gestoreController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCreaAttivita(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreaAttivita.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            CreaAttivitaView controller = loader.getController();
            controller.initialize(gestoreController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void handlePrenotazioneManule(ActionEvent actionEvent) {
        String nome=creaUtente();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrenotazioneManuale.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            PrenotazioneManualeView controller = loader.getController();
            controller.initialize( gestoreController,nome);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private String creaUtente() {
        String nome="";
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreaUtente.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            CreaUtenteView controller = loader.getController();
            controller.initialize(gestoreController);
            stage.showAndWait();
            nome=controller.getNomeUtente();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return nome;
    }

    public void handleCreaChalet(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InserimentoDati.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            InserimentoDatiView controller = loader.getController();
            controller.initialize(masterController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }
}
