package com.example.progettocasotto.Controller;

import com.example.progettocasotto.Model.Chalet.DefaultChalet;

public class DefaultGestoreController extends DefaultPersonaleController implements GestoreContrelloreInterface{



    public DefaultGestoreController(DefaultaMasterController masterController) {
        super(masterController);
    }


    @Override
    public boolean creaChalet(String nome) {
        masterController.creaChalet(nome);
        return false;
    }

    @Override
    public boolean creaSpiaggia(String spiaggia) {
        masterController.creaSpiaggia(spiaggia);
        return false;
    }

    @Override
    public boolean creaBar(String bar) {
        masterController.creaBar(bar);
        return false;
    }

    @Override
    public void setNumOmbrelloni(int numOmbrelloni) {
        masterController.getChalet().getSpiaggia().setNumeroOmbrelloni(numOmbrelloni);
    }

    @Override
    public void setNumSdraio(int numSdraio) {
        masterController.getChalet().getSpiaggia().setNumeroSdraio(numSdraio);
    }
}
