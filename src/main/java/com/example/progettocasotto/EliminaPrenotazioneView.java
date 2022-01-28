package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultGestoreController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EliminaPrenotazioneView {
    public TextField nomeUtente;
    public TextField numeroPrenotazione;
    public TextArea prenotazioniUtente;
    private AddettoASController asController;
    boolean flag=false;
    private boolean flagGestore=false;
    private DefaultGestoreController gestoreController;

    public void initialize(AddettoASController asController) {
        this.asController=asController;
    }

    public void handleMostraPrenotazioni(ActionEvent actionEvent) {
        if(!flagGestore) {
            prenotazioniUtente.setText(asController.getPrenotazioniCliente((nomeUtente.getText())));
            flag = true;
        }else{
            prenotazioniUtente.setText(gestoreController.getPrenotazioniCliente((nomeUtente.getText())));
            flag = true;
        }
    }

    public void handleEliminaPrenotazione(ActionEvent actionEvent) {
        if(!flagGestore) {
            if (flag) {
                asController.removePrenotazione(numeroPrenotazione.getText());
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
        }else{
            if (flag) {
                gestoreController.removePrenotazione(numeroPrenotazione.getText());
                ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
            }
        }
    }

    public void initialize(DefaultGestoreController gestoreController) {
        this.gestoreController=gestoreController;
        flagGestore=true;
    }
}
