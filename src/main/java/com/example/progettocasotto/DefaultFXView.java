package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.DataBase.GestoreDB;
import com.example.progettocasotto.DataBase.Privilegio;
import com.example.progettocasotto.View.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;


public class DefaultFXView {
    @FXML
    private TextField emailLabel;
    @FXML
    private TextField passwordLabel;
    @FXML
    private Button loginButton;
    DefaultaMasterController masterController=new DefaultaMasterController();
    GestoreDB gestoreDB =new GestoreDB();

    public void loginButtonPressed(ActionEvent actionEvent) {
        Privilegio privilegio=gestoreDB.getPrivilegio(emailLabel.getText(),passwordLabel.getText());
        String nomeCliente;

        switch (privilegio) {
            case SUPERADMIN:
                nomeCliente = gestoreDB.getNomeUtente(emailLabel.getText(), passwordLabel.getText());
                try {
                    URL url=getClass().getResource("FXGestoreChalet.fxml");
                    System.out.println(url);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXGestoreChalet.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(loader.load()));
                    FXGestoreChalet controller = loader.getController();
                    controller.initialize(masterController);
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ADMIN:
                IAddettoAllaSpiaggia addettoAllaSpiaggia = new IAddettoAllaSpiaggia(masterController);
                break;
            case USER:
                masterController.creaChaletDefault();
                nomeCliente = gestoreDB.getNomeUtente(emailLabel.getText(), passwordLabel.getText());
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientView.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(loader.load()));
                    ClientViev controller = loader.getController();
                    controller.initialize(masterController,nomeCliente);
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
