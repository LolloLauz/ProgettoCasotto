package com.example.progettocasotto.Model.Chalet;

import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.util.ArrayList;

public class DefaultChalet implements ChaletInterface {

    ArrayList<DefaultSpiaggia> listaSpiaggia=new ArrayList<>();
    ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
    ArrayList<DefaultAttivita> listaAttivita=new ArrayList<>();
    DefaultSpiaggia spiaggia;
    DefaultBar bar;

    private String nome;

    public DefaultChalet(String nome) {
        this.nome=nome;
    }

    @Override
    public void addSpiaggia(String nome) {
        spiaggia=new DefaultSpiaggia(nome);
    }

    @Override
    public void addBar(DefaultBar bar) {
        this.bar=bar;
    }


    public void setNome(String nome) {
        this.nome=nome;
    }

    public DefaultSpiaggia getSpiaggia(){
        return spiaggia;
    }

    public ArrayList<DefaultCliente> getListaClienti() {
        return listaClienti;
    }

    public boolean addCliente(DefaultCliente cliente){
        return listaClienti.add(cliente);
    }

    public DefaultCliente getClienteById(String idCliente){
        for(DefaultCliente cliente:listaClienti){
            if(cliente.getID().equals(idCliente)){
                return cliente;
            }
        }
        return null;
    }

    public DefaultBar getBar() {
        return bar;
    }

    public String getNome() {
        return nome;
    }

    public  boolean creaAttivita(DefaultAttivita attivita) {
        if(listaAttivita.contains(attivita)){
            return false;
        }
        return listaAttivita.add(attivita);
    }

    public boolean addPartecipantiToAttivta(String nomeAttivita, int numPersone) {
        return getAttivitaById(nomeAttivita).decrementaPosti(numPersone);
    }
    private DefaultAttivita getAttivitaById(String nomeAttivita){
        for(DefaultAttivita attivita:listaAttivita){
            if(attivita.getNome().equals(nomeAttivita))
                return attivita;
        }
        return null;
    }

    public ArrayList<DefaultAttivita> getListaAttivita() {
        return listaAttivita;
    }
}
