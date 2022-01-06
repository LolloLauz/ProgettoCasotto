package ProgettoCasotto.Personale;

public class Cliente implements Utenti {

    private String nome;
    private String cognome;
    private String email;
    private String password;

    public Cliente(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
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
