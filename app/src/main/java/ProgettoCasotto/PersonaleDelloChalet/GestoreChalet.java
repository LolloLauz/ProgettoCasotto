package ProgettoCasotto.PersonaleDelloChalet;

public class GestoreChalet  extends Personale implements Utenti{
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Privilegio privilegio;

    public GestoreChalet(String nome, String cognome, String email, String password) {
        super(nome,cognome,email,password,Privilegio.SUPERADMIN);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getCognome() {
        return cognome;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
