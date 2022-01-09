package ProgettoCasotto.PersonaleDelloChalet;

import ProgettoCasotto.ChaletPackage.Ordinazione;
import ProgettoCasotto.Spiaggia.Prenotazione;

import java.util.ArrayList;

public class Cliente implements Utenti {

    private String nome;
    private String cognome;
    private String email;
    private String password;
    private ArrayList<Prenotazione> prenotazioniAssociate;
    private ArrayList<Ordinazione> ordinazioneAssociate;

    public Cliente(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        prenotazioniAssociate=new ArrayList<>();
        ordinazioneAssociate=new ArrayList<>();
    }

    public boolean associaPrenotazioneAlCliente(Prenotazione prenotazione){
        if(prenotazioniAssociate.contains(prenotazione)){
            System.out.println("Prenotazione gia associata al cliente");
            return false;
        }
        return prenotazioniAssociate.add(prenotazione);
    }

    public boolean associaOrdinazioneAssociate(Ordinazione ordinazione){
        if(ordinazioneAssociate.contains(ordinazione)){
            System.out.println("Ordinazione gia associata al cliente");
        }
        return ordinazioneAssociate.add(ordinazione);
    }
    @Override
    public String getNome() {
        return null;
    }

    @Override
    public String getCognome() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
