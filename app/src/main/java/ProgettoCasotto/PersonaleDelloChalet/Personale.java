package ProgettoCasotto.PersonaleDelloChalet;

import ProgettoCasotto.Spiaggia.Prenotazione;

public abstract class Personale {
    String nome;
    private String cognome;
    private String email;
    private String password;
    private Privilegio privilegio;

    public Personale(String nome, String cognome, String email, String password, Privilegio privilegio) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.privilegio = privilegio;
    }

    public void confermaPagamento(Prenotazione prenotazione){
        prenotazione.cambiaStatoPrenotazione();
    }
    public void prenotazioneManuale(){
        //TODO :metodo da implementare
    }
    public void gestisciPrenotazione(Prenotazione prenotazione){
        //TODO :metodo per modificare i dati di un prenotazione
    }
}
