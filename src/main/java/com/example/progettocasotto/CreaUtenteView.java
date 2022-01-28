package com.example.progettocasotto;

import com.example.progettocasotto.Controller.AddettoASController;
import com.example.progettocasotto.Controller.DefaultGestoreController;
import com.example.progettocasotto.DataBase.Privilegio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreaUtenteView {

    @FXML
    private TextField nomeUtente;
    @FXML
    private TextField cognomeUtente;
    @FXML
    private TextField emailUtente;

    private AddettoASController asController;
    private DefaultGestoreController gestoreController;
    private boolean flagGestore;

    public void handelCreaUtente(ActionEvent actionEvent) {
        if(!flagGestore) {
            asController.creaUtente(nomeUtente.getText(), cognomeUtente.getText(), emailUtente.getText(), "password", Privilegio.USER);
        }else{
            gestoreController.creaUtente(nomeUtente.getText(), cognomeUtente.getText(), emailUtente.getText(), "password", Privilegio.USER);
        }
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void initialize(AddettoASController asController){
        this.asController=asController;

    }

    public String getNomeUtente() {
        return nomeUtente.getText();
    }

    public void initialize(DefaultGestoreController gestoreController) {
        this.gestoreController=gestoreController;
        this.flagGestore=true;
    }
}
