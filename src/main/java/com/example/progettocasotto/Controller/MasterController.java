package com.example.progettocasotto.Controller;

/**
 * interfaccia di controllo per tutto il sistema della spiaggia
 * @param <G> ControllerGestore
 * @param <A>AddettoASController
 * @param <P>PersonaleController
 * @param <U>UserController
 */
public interface MasterController <G,A,P,U>{

    boolean creaChalet(String nome);

    boolean creaSpiaggia(String nome);

    boolean creaBar(String nome);
}
