package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PagaOrdinazioneView {

    @FXML
    private Label warningLabel;
    @FXML
    private Label mostraOrdinazione;
    private DefaultUserController userController;
    boolean flag=true;



    public void handleClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }


    public void initialize(DefaultUserController userController){
        this.userController=userController;
        mostraOrdinazione.setText("\n"+userController.getStringaOrdinazione()+"\n li totale :"+userController.getTOtaleOrdinazione());
    }

    public void handlePagaConCarta(ActionEvent actionEvent) {
        if(flag) {
            userController.pagaOrdinazione();
            flag=false;
        }
    }

    public void handlePagaInContate(ActionEvent actionEvent) {
        if(flag) {
            warningLabel.setText("Per il pagamento in contante rivolgersi\nal personale della spiaggia");
            flag=false;
        }
    }
}
