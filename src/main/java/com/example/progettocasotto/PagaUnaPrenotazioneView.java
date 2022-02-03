package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PagaUnaPrenotazioneView {
    @FXML
    public ComboBox<String> sceltaPrenotazione;
    public TextArea mostraScontrino;
    @FXML
    private Label warningLabel;
    private DefaultUserController userController;
    boolean flag=false;
    public void initialize(DefaultUserController userController) {
        this.userController=userController;
        sceltaPrenotazione.getItems().addAll(userController.getPrenotazioneClient());
    }



    public void handlePagaConCarta(ActionEvent actionEvent) {
        if(flag) {
            mostraScontrino.setText(userController.pagaPrenotazione(sceltaPrenotazione.getValue()));
            flag=false;
        }
    }

    public void handlePagaContanti(ActionEvent actionEvent) {
        if(flag){
            warningLabel.setText("Per il pagamento contante rivolgersi al personale dello chalet");
            flag=false;
        }
    }

    public void handlePrenotazioneSelezionata(ActionEvent actionEvent) {
        mostraScontrino.setText(userController.getScontrinoPrenotazione(sceltaPrenotazione.getValue()));
        flag=true;
    }

    public void handleClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }
}
