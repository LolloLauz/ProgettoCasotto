package com.example.progettocasotto.Controller;

public interface GestoreContrelloreInterface {
    boolean creaChalet(String nome);
    boolean creaSpiaggia(String spiaggia);
    boolean creaBar(String bar);
    void setNumOmbrelloni(int numOmbrelloni);
    void setNumSdraio(int numSdraio);
}
