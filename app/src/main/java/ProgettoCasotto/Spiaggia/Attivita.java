package ProgettoCasotto.Spiaggia;

import java.util.Objects;

public class Attivita {
    private String nome;
    private int numPosti;
    private int dataInizio;
    private int dataFine;

    public Attivita(String nome) {
        this.nome = nome;
    }

    public Attivita(String nome, int numPosti, int dataFine, int dataInizio) {
        this.nome = nome;
        this.numPosti = numPosti;
        this.dataFine=dataFine;
        this.dataInizio=dataInizio;
    }

    public int getNumPosti() {
        return numPosti;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attivita attivita = (Attivita) o;
        return numPosti == attivita.numPosti && dataInizio == attivita.dataInizio && dataFine == attivita.dataFine && Objects.equals(nome, attivita.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, numPosti, dataInizio, dataFine);
    }
    public boolean decremetaPosti(int numPosti){
        this.numPosti-=numPosti;
        return numPosti >= 0;
    }
}
