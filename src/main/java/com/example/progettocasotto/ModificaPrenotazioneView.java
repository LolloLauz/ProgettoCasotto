package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultGestoreController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class ModificaPrenotazioneView {
    public DatePicker selezioneDataInizio;
    public DatePicker selezioneDataFine;
    public TextField nomeCliente;
    public TextField identificativoPrenotazione;
    public TextArea prenotazioniCliente;
    private AddettoASController asControlle;
    private boolean flagModificaPrenotazione=false;
    private DefaultGestoreController gestoreController;
    private boolean flagGestore=false;

    public void initialize(AddettoASController asController) {
        this.asControlle=asController;
    }

    public void handleModificaPeriodo(ActionEvent actionEvent) {
        if(!flagGestore) {
            Date dataInizio = new Date();
            Date dataFine = new Date();
            if (flagModificaPrenotazione) {
                LocalDate dataIn = selezioneDataInizio.getValue();
                Calendar c = Calendar.getInstance();
                c.set(dataIn.getYear(), dataIn.getMonthValue() - 1, dataIn.getDayOfMonth());
                dataInizio = c.getTime();
                LocalDate dataFin = selezioneDataFine.getValue();
                c.set(dataFin.getYear(), dataFin.getMonthValue() - 1, dataFin.getDayOfMonth());
                dataFine = c.getTime();
                if (!asControlle.modificaPeriodoPrenotazione(identificativoPrenotazione.getText(), dataInizio, dataFine)) {
                    prenotazioniCliente.setText("Gli ombrelloni e le sdriao non erano più disponibili\n" +
                            "quindi sono stati eliminati");
                }
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
        }else{
            Date dataInizio = new Date();
            Date dataFine = new Date();
            if (flagModificaPrenotazione) {
                LocalDate dataIn = selezioneDataInizio.getValue();
                Calendar c = Calendar.getInstance();
                c.set(dataIn.getYear(), dataIn.getMonthValue() - 1, dataIn.getDayOfMonth());
                dataInizio = c.getTime();
                LocalDate dataFin = selezioneDataFine.getValue();
                c.set(dataFin.getYear(), dataFin.getMonthValue() - 1, dataFin.getDayOfMonth());
                dataFine = c.getTime();
                if (!gestoreController.modificaPeriodoPrenotazione(identificativoPrenotazione.getText(), dataInizio, dataFine)) {
                    prenotazioniCliente.setText("Gli ombrelloni e le sdriao non erano più disponibili\n" +
                            "quindi sono stati eliminati");
                }
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
        }
    }


    public void handleNomeClienteSelezionato(ActionEvent actionEvent) {
        if(!flagGestore) {
            prenotazioniCliente.setText(asControlle.mostraPrenotazioniCliente(nomeCliente.getText()));
            flagModificaPrenotazione = true;
        }else{
            prenotazioniCliente.setText(gestoreController.mostraPrenotazioniCliente(nomeCliente.getText()));
            flagModificaPrenotazione = true;
        }
    }

    public void handleAggiungiOmbrelloni(ActionEvent actionEvent) {
        if(!flagGestore) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiOmbrelloni.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(loader.load()));
                AggiungiOmbrelloniView controller = loader.getController();
                controller.initialize(asControlle, identificativoPrenotazione.getText());
                stage.showAndWait();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiOmbrelloni.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(loader.load()));
                AggiungiOmbrelloniView controller = loader.getController();
                controller.initialize(gestoreController, identificativoPrenotazione.getText());
                stage.showAndWait();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void handleAggiungiSdraio(ActionEvent actionEvent) {
        if(!flagGestore) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiSdraioView.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(loader.load()));
                AggiungiSdraioView controller = loader.getController();
                controller.initialize(asControlle, identificativoPrenotazione.getText());
                stage.showAndWait();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AggiungiSdraioView.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(loader.load()));
                AggiungiSdraioView controller = loader.getController();
                controller.initialize(gestoreController, identificativoPrenotazione.getText());
                stage.showAndWait();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize(DefaultGestoreController gestoreController) {
        this.gestoreController=gestoreController;
        this.flagGestore=true;
    }

    public void handleClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
