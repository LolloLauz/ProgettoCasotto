package ProgettoCasotto.Controller;

import ProgettoCasotto.Chalet;
import ProgettoCasotto.ChaletPackage.Bar;
import ProgettoCasotto.Spiaggia.Spiaggia;

import java.util.Scanner;
import java.util.SortedMap;


public class MainController implements MainControllerInterface<DefaultGestoreController,DefaultAddettoAllaSpiaggia,DefaultPersonaleController,DefaultClienteController>{
    DefaultClienteController defaultClienteController;
    DefaultPersonaleController defaultPersonaleController=new DefaultPersonaleController();
    DefaultAddettoAllaSpiaggia defaultAddettoAllaSpiaggia=new DefaultAddettoAllaSpiaggia();
    DefaultGestoreController defaultGestoreController=new DefaultGestoreController();
    Chalet chalet;

    @Override
    public void createChalet(String nome) {
        System.out.println("Inserisci nome Chalet");
        this.chalet=new Chalet(nome);

    }

    @Override
    public void addSpiaggia(String nome) {
        System.out.println("INSERIMENTO nome spiaggia");
        chalet.addSpiaggia(new Spiaggia(nome));
        int dati[]=new int[2];
        dati=impostaDatiSpiaggia();
        chalet.getListaSpiaggia().setNumOmbrelloni(dati[0]);
        chalet.getListaSpiaggia().setNumSdraio(dati[1]);
        this.defaultClienteController=new DefaultClienteController(chalet.getListaSpiaggia());
        defaultGestoreController.setSpiaggia(chalet.getListaSpiaggia());
        defaultAddettoAllaSpiaggia.setSpiaggia(chalet.getListaSpiaggia());
        defaultPersonaleController.setSpiaggia(chalet.getListaSpiaggia());
    }

    private int[] impostaDatiSpiaggia() {
        System.out.println("INSERIMENTO DATI RELATIVI SPIAGGIA");
        int dati[]=new int[2];
        dati[0]=50;
        dati[1]=10;
        return dati;
    }

    @Override
    public void addBar(String nome) {
        System.out.println("inserimento bar");
        chalet.addBar(new Bar(nome));
    }

    @Override
    public void addAreaAttivita(String nome) {
        System.out.println("Inserimento area attivita");
        chalet.addAreaAttivita(nome);
    }

    public DefaultClienteController getDefaultClienteController() {
        return defaultClienteController;
    }

    public DefaultPersonaleController getDefaultPersonaleController() {
        return defaultPersonaleController;
    }

    public DefaultAddettoAllaSpiaggia getDefaultAddettoAllaSpiaggia() {
        return defaultAddettoAllaSpiaggia;
    }

    public DefaultGestoreController getDefaultGestoreController() {
        return defaultGestoreController;
    }

    public void getStatoPrenotazioniSpiaggia(){
        System.out.println("Stampo lo stato della spiaggia");
        chalet.getListaSpiaggia().getPrenotazione();
    }
}
