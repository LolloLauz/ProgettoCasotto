package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class OrdinazioneBarView {

    @FXML
    private ComboBox<String> qrCode;
    @FXML
    private TextField bevanda;
    @FXML
    private TextField bevandaQuantita;
    private DefaultUserController userController;
    @FXML
    private Label menu;

    public void initialize(DefaultUserController userController) {
        this.userController=userController;
        menu.setText(userController.mostraMenu());
        userController.creaOrdinazione();
    }

    public void handleAggiungiAllOrdine(ActionEvent actionEvent) {
        userController.ordinaBibita(bevanda.getText(), Integer.parseInt(bevandaQuantita.getText()));
        qrCode.getItems().addAll(userController.getlistaQrCode());
    }

    public void handleConfermaOrdinazione(ActionEvent actionEvent) {
        userController.setqrCode(qrCode.getValue());
        userController.caricaOrdinazione();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PagaOrdinazione.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            PagaOrdinazioneView controller = loader.getController();
            controller.initialize(userController);
            stage.showAndWait();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
