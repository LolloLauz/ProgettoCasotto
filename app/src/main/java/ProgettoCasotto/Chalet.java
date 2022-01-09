package ProgettoCasotto;





import ProgettoCasotto.ChaletPackage.Bar;
import ProgettoCasotto.Spiaggia.Attivita;
import ProgettoCasotto.Spiaggia.ChaletInterface;
import ProgettoCasotto.Spiaggia.Spiaggia;

import java.util.ArrayList;

public class Chalet implements ChaletInterface {
    private String ID;
    private ArrayList<Spiaggia> listaSpiaggia;
    private ArrayList<Bar> listaBar;
    private ArrayList<String> listaAreeAttivita;
    private ArrayList<Attivita> listaAttivita;

    public Chalet(String ID) {
        this.ID=ID;
        listaSpiaggia = new ArrayList<>();
        listaBar = new ArrayList<>();
        listaAreeAttivita = new ArrayList<>();
        listaAttivita=new ArrayList<>();
    }

    @Override
    public boolean addSpiaggia(Spiaggia spiaggia) {
        if(listaSpiaggia.isEmpty()){
            return listaSpiaggia.add(spiaggia);
        }
        if(listaSpiaggia.contains(spiaggia)){
            return listaSpiaggia.add(spiaggia);
        }
        return false;
    }

    @Override
    public boolean addBar(Bar bar) {
        if(listaBar.isEmpty()){
            return listaBar.add(bar);
        }
        if(listaBar.contains(bar)){
            return listaBar.add(bar);
        }
        return false;
    }

    @Override
    public boolean addAreaAttivita(String  areaAttivita) {
        if(listaAreeAttivita.isEmpty()){
            return listaAreeAttivita.add(areaAttivita);
        }
        if(listaAreeAttivita.contains(areaAttivita)){
            return listaAreeAttivita.add(areaAttivita);
        }
        return false;
    }

    public boolean prenotaAttivita(String nomeAttivita, int numPosti) {
        for(Attivita attivita:listaAttivita){
            if(attivita.getNome().equals(nomeAttivita)){
                return attivita.decremetaPosti(numPosti);
            }
        }
        return false;
    }

    @Override
    public boolean createAttivita(String nomeAttivita, int numPosti, int dataInizio, int dataFine) {
        Attivita attivita=new Attivita(nomeAttivita,numPosti,dataInizio,dataFine);
        if(listaAttivita.contains(attivita)){
            return false;
        }
        return listaAttivita.add(attivita);
    }

    public Spiaggia getListaSpiaggia() {
        return listaSpiaggia.get(0);
    }

    public Bar getListaBar() {
        return listaBar.get(0);
    }

    public String StampaChalet(){
        System.out.println("-------------------------------");
        for(Spiaggia spiaggia:listaSpiaggia){
            spiaggia.getStatoSpiaggia();
        }
        for(Bar bar:listaBar){
            bar.getInventario();
        }
        System.out.println("-------------------------------");

        for(String area:listaAreeAttivita){
            System.out.println(area);
        }
        System.out.println("-------------------------------");
        for(Attivita attivita: listaAttivita){
            System.out.println(attivita.getNome());
        }
        return "-------------------------------";
    }
}
