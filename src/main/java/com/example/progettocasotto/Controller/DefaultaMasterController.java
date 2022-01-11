package com.example.progettocasotto.Controller;

import com.example.progettocasotto.Model.Chalet.DefaultChalet;
import com.example.progettocasotto.Model.Chalet.DefaultSpiaggia;
import com.example.progettocasotto.Model.ChaletInterface;

public class DefaultaMasterController implements MasterController<DefaultGestoreController,AddettoASController,DefaultPersonaleController,DefaultUserController>{


    DefaultChalet chalet;
    DefaultUserController userController;

    public DefaultUserController getUserController() {
        return userController;
    }

    public DefaultaMasterController() {
        this.chalet=new DefaultChalet();
    }

    @Override
    public boolean creaChalet(String nome) {
        chalet.setNome(nome);
        return true;
    }

    @Override
    public boolean creaSpiaggia(String nome) {
        chalet.addSpiaggia(new DefaultSpiaggia(nome));
        chalet.getSpiaggia().setNumeroOmbrelloni(10);
        userController=new DefaultUserController(chalet.getSpiaggia());
        return true;

    }

    @Override
    public boolean creaBar(String nome) {
        return false;
    }
}
