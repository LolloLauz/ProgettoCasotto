package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.View.DefaultView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("DefaultFXView.fxml"));
        stage.setTitle("login");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
//        FXMLLoader gestoreView = new FXMLLoader(getClass().getResource("FXGestoreChalet.fxml"));
//        stage = new Stage(StageStyle.DECORATED);
//        stage.initModality(Modality.APPLICATION_MODAL);
//        stage.setScene(new Scene(gestoreView.load()));
//        FXGestoreChalet gestoreChalet = gestoreView.getController();
//        gestoreChalet.initialize(masterController);
//        stage.show();
    }


    public static void main(String[] args) {
        DefaultView defaultView=new DefaultView();
//        launch();
    }
}