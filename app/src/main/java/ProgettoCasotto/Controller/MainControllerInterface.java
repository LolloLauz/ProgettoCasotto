package ProgettoCasotto.Controller;

/**
 * interfaccia per il controllo generale del sistema
 * @param <G> controller del gestore
 * @param <A> controller dell'addetto
 * @param <P>controller del personale
 * @param <C>controller del client
 */
public interface MainControllerInterface<G,A,P,C> {

    public void createChalet(String nome);

    public void addSpiaggia(String nome);

    public void addBar(String nome);

    public void addAreaAttivita(String nome);

}
