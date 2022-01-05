package ProgettoCasotto.ChaletPackage;

import java.util.ArrayList;
import java.util.Objects;

public class Bar {
    private String ID;
    private Menu menu;
    private ArrayList<Ordinazione> listaOrdinazioni;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bar bar = (Bar) o;
        return Objects.equals(ID, bar.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    public Bar(String ID) {
        this.ID = ID;
        this.listaOrdinazioni=new ArrayList<>();
    }

    public Bar(String ID, Menu menu) {
        this.ID = ID;
        this.menu=menu;
        this.listaOrdinazioni=new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    private boolean creaOrdinazione(int ID){
        return listaOrdinazioni.add(new Ordinazione(ID));
    }

    public boolean selectBevanda(int IDOrdinazione, Bevanda bevanda, int quantita){
        if(!listaOrdinazioni.contains(new Ordinazione(IDOrdinazione))){
            creaOrdinazione(IDOrdinazione);
        }
        if(menu.getListaBevande().containsKey(bevanda)){
                if (menu.decrementaBevanda(bevanda, quantita)) {
                    return addBevandaToOrdinazione(IDOrdinazione, bevanda, quantita);
                }
            System.out.println("non e' possibile soddisfare la richiesta");
            return false;
        }
        System.out.println("la bevanda selezionata non è prensente nel catalogo");
        return false;
    }
    private boolean addBevandaToOrdinazione(int IDOrdinazione, Bevanda bevanda,int quantita){
        for(Ordinazione ordinazione:listaOrdinazioni){
            if(ordinazione.getID()==IDOrdinazione){
                ordinazione.addBevanda(bevanda,quantita);
                return  true;
            }
        }
        return false;
    }

    public void stampaListaOrdinazioni(){
        System.out.println("la lista delle ordina e' ");
        for(Ordinazione ordinazione:listaOrdinazioni){
            System.out.println("ID ordinazione "+ordinazione.getID());
            for(Bevanda bevanda:ordinazione.getBevande().keySet()){
                System.out.println(bevanda.getID()+" "+bevanda.getDescrizione()+"quantita oridnata "+ordinazione.getQuantita(bevanda));
            }

        }
    }
    public void getInventario(){
        for(Bevanda bevanda:menu.getListaBevande().keySet()){
            System.out.println(bevanda.getDescrizione()+" "+menu.getListaBevande().get(bevanda));
        }
    }
    public boolean confermaPagamnetoOrdinazione(Ordinazione ordinazione){
        if(listaOrdinazioni.contains(ordinazione)){
            ordinazione.cambiaStato();
            return true;
        }
        return false;
    }
}
