package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AddettoAllaSpiaggiaView {
    private AddettoASController asController;
    public void initialize(DefaultaMasterController masterController){
        this.asController=new AddettoASController(masterController);
    }

    public void handlePrenotazioneManuale(ActionEvent actionEvent) {
        String nome=creaUtente();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrenotazioneManuale.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            PrenotazioneManualeView controller = loader.getController();
            controller.initialize(asController,nome);
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
            controller.initialize(asController);
            stage.showAndWait();
            nome=controller.getNomeUtente();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return nome;
    }

    public void handleEliminaPrenotazione(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EliminaPrenotazione.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            EliminaPrenotazioneView controller = loader.getController();
            controller.initialize(asController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void handlePagamentoContante(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PagamentoContante.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            PagamentoContanteView controller = loader.getController();
            controller.initialize(asController);
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
            controller.initialize(asController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
