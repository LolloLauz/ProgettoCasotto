package com.example.progettocasotto.View;

import com.example.progettocasotto.Controller.DefaultaMasterController;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultBar;

import java.util.Date;
import java.util.Scanner;

public class IgestoreChalet {

    DefaultaMasterController masterController;

        public IgestoreChalet(DefaultaMasterController masterController) {
            this.masterController = masterController;
            Scanner scanner=new Scanner(System.in);
            System.out.println("SE E' IL TUO PRIMO ACCESSO PREMI 2");
            System.out.println("CHE COSA VUOI FARE");
            String coseDaFare="1-inserisci nome spiaggia" +
                    "\n2-crea chalet" +
                    "\n3-creazione chalet di default" +
                    "\n-invio per uscire";
            System.out.println(coseDaFare);
            String operazione=scanner.nextLine();
            while (!operazione.equals("")) {
                switch (operazione) {
                    case "1":
                        System.out.println("HAI SELEZIONATO INSERISCI SPIAGGIA");
                        System.out.println("INSERISCI NOME SPIAGGIA");
                        addSpiaggia();
                        break;
                    case "2":
                        System.out.println("HAI SELEZIONATO CREA CHALET ");
                        System.out.println("INSERISCI NOME CHALET");
                        creaChalet();
                        break;
                    case "3":
                        System.out.println("Hai selezionato crea Bar");
                        creaBar();
                    case "4":
                        creaChaletDefault();
                        System.out.println("chalet di default creato");
                        break;
                    default:
                        break;
                }
                System.out.println("CHE COSA VUOI FARE");
                System.out.println(coseDaFare);
                operazione= scanner.nextLine();
            }
        }

        public boolean addSpiaggia(){
            Scanner scanner=new Scanner(System.in);
            String nome =scanner.nextLine();
            if(nome==null){
                return false;
            }
            masterController.creaSpiaggia(nome);
            return true;
        }

        boolean creaChalet(){
            Scanner scanner=new Scanner(System.in);
            String nome =scanner.nextLine();
            if(nome==null){
                return false;
            }
            masterController.creaChalet(nome);
            return true;
        }

        void setNumOmbrelloni(int num){
            masterController.getChalet().getSpiaggia().setNumeroOmbrelloni(num);
        }

        void setNumSdraio(int num){
            masterController.getChalet().getSpiaggia().setNumeroSdraio(num);
        }

        public boolean createListinoPrezzi(){
            //TODO: da fare
            return false;
        }

        public boolean creaBar(){
            System.out.println("Inserisci il nome del bar");
            Scanner scanner=new Scanner(System.in);
            String bar =scanner.nextLine();
            masterController.getChalet().addBar(new DefaultBar(bar));
            return false;
        }

        public boolean creaBevanda(String nome, String descrizione,int quantita,double prezzo){
            masterController.getChalet().getBar().creaBevanda(nome,descrizione,quantita,prezzo);
            return false;
        }

        public boolean inserisciMenu(){
            //TODO: da fare
            return false;
        }

        public boolean creaAttiva(String nome, String luogo, Date dataInizio, Date dataFine, int numMassimoPersone){
            return false;
        }

        public void creaChaletDefault(){
            masterController.creaChaletDefault();
        }
    }
