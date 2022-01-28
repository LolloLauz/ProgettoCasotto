package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultGestoreController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PagamentoContanteView {
    public TextArea prenotazioniOrdinazioni;
    public TextField numeroPrenotazione;
    public TextField numeroOrdinazione;
    public TextField nomeUtente;
    public Button pagaPrenotazione;
    private AddettoASController asController;
    boolean flagPreOrd=false;
    boolean flagPagaPrenotazioni=false;
    private boolean flagPagaOrdinazione=false;
    private boolean flagGestore=false;
    private DefaultGestoreController defaultGestoreController;

    public void initialize(AddettoASController asController) {
        this.asController=asController;
    }

    public void handlePagaPrenotazione(ActionEvent actionEvent) {
        if(!flagGestore) {
            if (flagPagaPrenotazioni) {
                asController.confermaAvvenutoPagamentoPrenotazione(numeroPrenotazione.getText());
                flagPreOrd = false;
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
            if (flagPreOrd) {
                prenotazioniOrdinazioni.setText(asController.MostraScontrinoPrenotazione(numeroPrenotazione.getText()));
                flagPagaPrenotazioni = true;
                pagaPrenotazione.setText("Paga");
            }
        }else{
            if (flagPagaPrenotazioni) {
                defaultGestoreController.confermaAvvenutoPagamentoPrenotazione(numeroPrenotazione.getText());
                flagPreOrd = false;
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
            if (flagPreOrd) {
                prenotazioniOrdinazioni.setText(defaultGestoreController.MostraScontrinoPrenotazione(numeroPrenotazione.getText()));
                flagPagaPrenotazioni = true;
                pagaPrenotazione.setText("Paga");
            }
        }
    }

    public void handlePagaOrdinazione(ActionEvent actionEvent) {
        if(!flagGestore) {
            if (flagPagaOrdinazione) {
                asController.confermaAvvenutoPagamentoOrdinazione(numeroOrdinazione.getText());
                flagPreOrd = false;
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
            if (flagPreOrd) {
                prenotazioniOrdinazioni.setText(asController.mostraScontrinoOrdinazione(numeroOrdinazione.getText()));
                flagPagaOrdinazione = true;
                pagaPrenotazione.setText("Paga");
            }
        }else{
            if (flagPagaOrdinazione) {
                defaultGestoreController.confermaAvvenutoPagamentoOrdinazione(numeroOrdinazione.getText());
                flagPreOrd = false;
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
            if (flagPreOrd) {
                prenotazioniOrdinazioni.setText(defaultGestoreController.mostraScontrinoOrdinazione(numeroOrdinazione.getText()));
                flagPagaOrdinazione = true;
                pagaPrenotazione.setText("Paga");
            }
        }
    }

    public void handleMostraPrenotazioniOrdinazioni(ActionEvent actionEvent) {
        if(!flagGestore) {
            prenotazioniOrdinazioni.setText(asController.mostraPrenotazioniOrdinazioni(nomeUtente.getText()));
            flagPreOrd = true;
        }else{
            prenotazioniOrdinazioni.setText(defaultGestoreController.mostraPrenotazioniOrdinazioni(nomeUtente.getText()));
            flagPreOrd = true;
        }
    }

    public void initialize(DefaultGestoreController gestoreController) {
        this.defaultGestoreController=gestoreController;
        flagGestore=true;
    }
}
