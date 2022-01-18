package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FXGestoreChalet {

    @FXML
    private Button creaChaletDiDefault;

    DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController){
        this.masterController=masterController;
    }
    public void handleDefaultChalet(ActionEvent actionEvent) {
        masterController.creaChaletDefault();
    }

}
