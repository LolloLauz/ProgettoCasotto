package com.example.progettocasotto.Model.Chalet;

import java.util.Date;
import java.util.Objects;

public class DefaultAttivita {
    String nome;
    int numeroPosti;
    Date dataInizio;
    Date dataFine;
    String descrizione;

    public DefaultAttivita(String nome, int numeroPosti, Date dataInizio, Date dataFine, String descrizione) {
        this.nome = nome;
        this.numeroPosti = numeroPosti;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.descrizione = descrizione;
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

    public String getDescrizione() {
        return descrizione;
    }
}
