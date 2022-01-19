package com.example.progettocasotto.Model.Chalet.Bar;

import java.util.Objects;

public class Bevanda {

    private String nome;
    private String descrizione;
    private int quantita;
    private double prezzo;

    public Bevanda(String nome, String descrizione, int quantita, double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.quantita = quantita;
        this.prezzo=prezzo;
    }


    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getQuantita() {
        return quantita;
    }
    public boolean decrementaQuantita(int quantita){
        if(this.quantita<quantita){
            System.out.println("non e possibile soddisfare la sua richiesta");
            return false;
        }
        this.quantita-=quantita;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bevanda bevanda = (Bevanda) o;
        return Objects.equals(nome, bevanda.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public double getPrezzo() {
        return prezzo;
    }
}
