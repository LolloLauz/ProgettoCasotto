package ProgettoCasotto.PersonaleDelloChalet;

public class AddettoAllaSpiaggia extends Personale implements Utenti {
    private String nome;
    private String cognome;
    private String email;
    private String password;

    public AddettoAllaSpiaggia(String nome, String cognome, String email, String password) {
        super(nome,cognome,email,password,Privilegio.ADMIN);
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
