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

public class InserimentoDatiView {
    public TextField nomeChalet;
    private DefaultaMasterController masterController;
    public void handleAvanti(ActionEvent actionEvent) {
        masterController.creaChalet(nomeChalet.getText());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreaSpiaggia.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            CreaSpiaggiaView controller = loader.getController();
            controller.initialize(masterController);
            stage.showAndWait();
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize(DefaultaMasterController masterController){
        this.masterController=masterController;
    }
}
