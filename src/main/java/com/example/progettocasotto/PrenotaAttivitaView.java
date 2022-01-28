package com.example.progettocasotto;

import com.example.progettocasotto.Controller.DefaultUserController;
import com.example.progettocasotto.Model.Chalet.DefaultAttivita;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrenotaAttivitaView {
    public TextArea mostraAttivita;
    public ComboBox<DefaultAttivita> idAttivita;
    public TextField numeroPersone;
    private DefaultUserController userController;


    public void handlePrenota(ActionEvent actionEvent) {
        if(userController.prenotaAttivita(idAttivita.getValue().getNome())){
            mostraAttivita.setText("il numero di posti e' :"+idAttivita.getValue().getNumeroPosti());
            if(!userController.prenotaAttivita(idAttivita.getValue().getNome(), Integer.parseInt(numeroPersone.getText()))){
                mostraAttivita.setText("ops... qualcosa sembre essere andato storto");
            }
        }else{
            mostraAttivita.setText("posti esauriti");
        }
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void initialize(DefaultUserController userController) {
        this.userController=userController;
        userController.getListaAttivita();
        mostraAttivita.setText(userController.mostraListaAttivita());
        idAttivita.getItems().addAll(userController.getAttivita());
    }
}
