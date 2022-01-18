package com.example.progettocasotto.Model.Chalet.Bar;

import java.util.ArrayList;
import java.util.Objects;

public class DefaultBar implements BarInterface{

    private String nome;
    private ArrayList<DefaultOrdinazione> listaOrdinazioni;
    private ArrayList<Bevanda> listaBevande;

    public DefaultBar(String nome) {
        this.nome = nome;
        listaOrdinazioni=new ArrayList<>();
        listaBevande=new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultBar that = (DefaultBar) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public String getNome() {
        return nome;
    }

//    public boolean selezionaBevanda(Bevanda bevanda) {
//        //TODO :metodo ancora da implementare
//        return true;
//    }

    @Override
    public boolean creaOrdinazione(String idOrdinazione) {
        if(listaOrdinazioni.contains(getOrdinazioneById(idOrdinazione))){
            return false;
        }
        return listaOrdinazioni.add(new DefaultOrdinazione(idOrdinazione));
    }

    @Override
    public boolean selezionaBevanda(String idOrdinazione, String bevanda, int quantita) {
        if(getOrdinazioneById(idOrdinazione).getListaBevande().contains(bevanda)){
            return false;
        }
        if(getBevandaById(bevanda).decrementaQuantita(quantita)) {
            getOrdinazioneById(idOrdinazione).addBevanda(getBevandaById(bevanda), quantita);
            return true;
        }
        return false;
    }

    public void creaBevanda(String nome,String descrizione,int quantita,double prezzo){
        listaBevande.add(new Bevanda(nome,descrizione,quantita,prezzo));
    }

    private  DefaultOrdinazione getOrdinazioneById(String idOridnazione){
        for(DefaultOrdinazione ordinazione:listaOrdinazioni){
            if(ordinazione.getID().equals(idOridnazione)){
                return  ordinazione;
            }
        }
        return null;
    }
    private Bevanda getBevandaById(String idBevanda){
        for(Bevanda bevanda:listaBevande){
            if(bevanda.getNome().equals(idBevanda)){
                return bevanda;
            }
        }
        return null;
    }

    public void stampaListaPrenotazioni(){
        for(DefaultOrdinazione ordinazione:listaOrdinazioni){
            System.out.println(ordinazione.getID()+":");
            for (Bevanda bevanda:ordinazione.getListaBevande()){
                System.out.println(bevanda.getNome()+" quantita "+ ordinazione.getListaBevandaMappa().get(bevanda));
            }
        }
    }

    public ArrayList<Bevanda> getListaBevande() {
        return listaBevande;
    }
}
