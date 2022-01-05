package ProgettoCasotto.ChaletPackage;

import java.util.HashMap;

public class Menu {
    private int ID;
    private HashMap<Bevanda,Integer> listaBevande;

    public Menu(int ID) {
        listaBevande=new HashMap<>();
        this.ID = ID;
    }
    public HashMap<Bevanda, Integer> getListaBevande() {
        return listaBevande;
    }
    public boolean inserisciBevandaNelCatalogo(Bevanda bevanda,int quantita){
        if(listaBevande.keySet().contains(bevanda)){
            if(quantita>listaBevande.get(bevanda)) {
                return listaBevande.replace(bevanda, listaBevande.get(bevanda), quantita);
            }
            return false;
        }
        listaBevande.put(bevanda,quantita);
        return false;
    }
    public boolean decrementaBevanda(Bevanda bevanda, int quantita){
        if(listaBevande.get(bevanda)-quantita==0){
            return listaBevande.remove(bevanda,quantita);
        }
        if(!(listaBevande.get(bevanda)-quantita<0)) {
            return listaBevande.replace(bevanda, getListaBevande().get(bevanda), listaBevande.get(bevanda) - quantita);
        }
        return false;
    }
    public void stampaListaBevande(){
        for(Bevanda bevanda :listaBevande.keySet()){
            System.out.println(bevanda.getID()+" "+bevanda.getDescrizione());
        }
    }

    public int getID() {
        return ID;
    }
}
