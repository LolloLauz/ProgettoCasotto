package com.example.progettocasotto.Model.Spiaggia;



import java.util.Calendar;

public class ListinoPrezzi {

    private final double prezzoOmbrellone=12.00;
    private final double prezzoSdraio=3.00;

    private double scontoDueMesi;
    private double scontoUnMese;
    private double scontoUnaSettimana;

    public void setScontoUnMese(double scontoUnMese) {
        this.scontoUnMese = scontoUnMese;
    }

    public void setScontoUnaSettimana(double scontoUnaSettimana) {
        this.scontoUnaSettimana = scontoUnaSettimana;
    }

    public void setScontoDueMesi(double scontoDueMesi) {
        this.scontoDueMesi = scontoDueMesi;
    }

    public double getScontoDueMesi() {
        return scontoDueMesi;
    }

    public double getScontoUnMese() {
        return scontoUnMese;
    }

    public double getScontoUnaSettimana() {
        return scontoUnaSettimana;
    }

    public double calcolaPrezzo(DefaultPrenotazione prenotazione){
        Calendar dataInizio=Calendar.getInstance();
        dataInizio.setTime(prenotazione.getDataInizio());
        Calendar dataFine=Calendar.getInstance();
        dataFine.setTime(prenotazione.getDataFine());
        double totale;
        if(dataFine.get(Calendar.MONTH)-dataInizio.get(Calendar.MONTH)>=2){
            totale = (prenotazione.getListaOmbrelloni().size() * prezzoOmbrellone)-prezzoOmbrellone*scontoDueMesi;
        }else if(dataFine.get(Calendar.MONTH)-dataInizio.get(Calendar.MONTH)>=1){
            totale=(prenotazione.getListaOmbrelloni().size() * prezzoOmbrellone)-prezzoOmbrellone*scontoUnMese;
        }else if(dataFine.get(Calendar.DAY_OF_MONTH)-dataFine.get(Calendar.DAY_OF_MONTH)>7){
            totale=(prenotazione.getListaOmbrelloni().size() * prezzoOmbrellone)-prezzoOmbrellone*scontoUnaSettimana;
        }else{
            totale=prenotazione.getListaOmbrelloni().size()*prezzoOmbrellone;
            totale+=prenotazione.getListaSdraio().size()*prezzoSdraio;
        }
        return totale;
    }
}
