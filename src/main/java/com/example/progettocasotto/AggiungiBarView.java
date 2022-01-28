package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AggiungiBarView {
    public TextField nomeBar;
    private DefaultaMasterController masterController;

    public void initialize(DefaultaMasterController masterController) {
        this.masterController=masterController;
    }

    public void handleAvanti(ActionEvent actionEvent) {
        masterController.creaBar(nomeBar.getText());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreaBevanda.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            CreaBevandaView controller = loader.getController();
            controller.initialize(masterController);
            stage.showAndWait();
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
