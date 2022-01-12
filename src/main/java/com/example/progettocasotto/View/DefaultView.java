package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.Model.Chalet.DefaultChalet;

public class DefaultView {

    DefaultaMasterController masterController=new DefaultaMasterController();


    public DefaultView() {
        provametodi();
    }

    private void provametodi() {
        masterController.creaChalet("nome");
        masterController.creaSpiaggia("spiaggia");
        UsersView usersView=new UsersView(masterController.getUserController());
    }
}
