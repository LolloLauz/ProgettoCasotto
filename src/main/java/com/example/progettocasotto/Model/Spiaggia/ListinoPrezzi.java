package com.example.progettocasotto.Model.Spiaggia;



import java.util.Calendar;

public class ListinoPrezzi {

    private final double prezzoOmbrellone=12.00;
    private final double prezzoSdraio=3.00;

    public double calcolaPrezzo(DefaultPrenotazione prenotazione){
        Calendar dataInizio=Calendar.getInstance();
        dataInizio.setTime(prenotazione.getDataInizio());
        Calendar dataFine=Calendar.getInstance();
        dataFine.setTime(prenotazione.getDataFine());
        double totale;
        if(dataFine.get(Calendar.MONTH)-dataInizio.get(Calendar.MONTH)>=2){
            totale = (prenotazione.getListaOmbrelloni().size() * prezzoOmbrellone)-prezzoOmbrellone*0.15;
        }else if(dataFine.get(Calendar.MONTH)-dataInizio.get(Calendar.MONTH)>=1){
            totale=(prenotazione.getListaOmbrelloni().size() * prezzoOmbrellone)-prezzoOmbrellone*0.10;
        }else if(dataFine.get(Calendar.DAY_OF_MONTH)-dataFine.get(Calendar.DAY_OF_MONTH)>7){
            totale=(prenotazione.getListaOmbrelloni().size() * prezzoOmbrellone)-prezzoOmbrellone*0.05;
        }else{
            totale=prenotazione.getListaOmbrelloni().size()*prezzoOmbrellone;
            totale+=prenotazione.getListaSdraio().size()*prezzoSdraio;
        }
        return totale;
    }
}
