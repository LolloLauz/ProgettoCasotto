package com.example.progettocasotto.Model.Chalet;

import java.util.Date;
import java.util.Objects;

public class DefaultAttivita implements AttivitaInterface{
    String nome;
    int numeroPosti;
    Date dataInizio;
    Date dataFine;
    String luogo;

    public DefaultAttivita(String nome, String luogo, Date dataInizio, Date dataFine, int numeroPosti) {
        this.nome = nome;
        this.numeroPosti = numeroPosti;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.luogo = luogo;
    }

    public void setNumeroPosti(int numeroPosti) {
        this.numeroPosti = numeroPosti;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultAttivita that = (DefaultAttivita) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public String getNome() {
        return nome;
    }

    public int getNumeroPosti() {
        return numeroPosti;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public String getLuogo() {
        return luogo;
    }

    public boolean decrementaPosti(int numPersone) {
        if(numPersone>numeroPosti){
            return false;
        }
        numeroPosti-=numPersone;
        return true;
    }

    @Override
    public String toString() {
        return "DefaultAttivita{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
