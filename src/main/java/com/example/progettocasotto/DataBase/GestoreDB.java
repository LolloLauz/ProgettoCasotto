package com.example.progettocasotto.DataBase;

import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.Model.Chalet.Bar.DefaultOrdinazione;
import com.example.progettocasotto.Model.Chalet.DefaultAttivita;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Spiaggia.Sdraio;
import com.example.progettocasotto.Model.Spiaggia.StatoPreOrd;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

public class GestoreDB {

    Connection connection;
    public GestoreDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcasotto", "root", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public String getNomeSpiaggia(){
        String nome="";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT nome FROM spiaggia");
            while(resultSet.next()) {
                nome=(resultSet.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nome;
    }
    public int getNumeroSdraio(){
        int numeroSdraio=0;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT numero_sdraio FROM spiaggia");
            if(resultSet.next()) {
                numeroSdraio=(resultSet.getInt("numero_sdraio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numeroSdraio;
    }
    private void popolaPrenotazioneOmbrelloni(ArrayList<DefaultPrenotazione> listaprenotazioni){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT id_ombrellone FROM dettaglioprenotazione WHERE id_prenotazione=? ");
            for (DefaultPrenotazione prenotazione : listaprenotazioni) {
                preparedStatement.setString(1, prenotazione.getID());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    prenotazione.getListaOmbrelloni().add(new Ombrellone(resultSet.getString("id_ombrellone")));
                }
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public ArrayList<DefaultPrenotazione> getPrenotazioniFromDB(){
        ArrayList<DefaultPrenotazione> listaPrenotazioni=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM prenotazioni");
            while(resultSet.next()) {
                listaPrenotazioni.add(new DefaultPrenotazione(resultSet.getString("id"),resultSet.getDate("data_inizio"),resultSet.getDate("data_fine")));
                for(DefaultPrenotazione prenotazione:listaPrenotazioni){
                    if(prenotazione.getID().equals(resultSet.getString("id"))){
                        prenotazione.setStatoPrenotazione(StatoPreOrd.valueOf(resultSet.getString("StatoPrenotazione")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        popolaPrenotazioneOmbrelloni(listaPrenotazioni);
        popolaPrenotazioneSdraio(listaPrenotazioni);
        return listaPrenotazioni;
    }

    private void popolaPrenotazioneSdraio(ArrayList<DefaultPrenotazione> listaPrenotazioni) {
        Random random=new Random();
        int numeroSdraioSpiaggia=getNumeroSdraio();
        try {
            int i=0;
            int numSdraio=0;
            int numeroRandom=random.nextInt(numeroSdraioSpiaggia);
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT num_sdraio FROM prenotazioni WHERE id=? AND num_sdraio>0");
            for(DefaultPrenotazione prenotazione:listaPrenotazioni){
                preparedStatement.setString(1, prenotazione.getID());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                        numSdraio=resultSet.getInt("num_sdraio");
                            while (i<numSdraio) {
                                if (!prenotazione.getListaSdraio().contains(new Sdraio(String.valueOf(numeroRandom)))){
                                    prenotazione.getListaSdraio().add(new Sdraio(String.valueOf(numeroRandom)));
                                    i++;
                                }
                                numeroRandom=random.nextInt(numeroSdraioSpiaggia);
                            }
                }
                i=0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getNumeroOmbrelloni(){
        int numeroOmbrelloni=0;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT numero_ombrelloni FROM spiaggia");
            while(resultSet.next()) {
                numeroOmbrelloni=(resultSet.getInt("numero_ombrelloni"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numeroOmbrelloni;
    }
    public Privilegio getPrivilegio(String email, String password) {
        String privilegio = null;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT nome,privilegio FROM utenti WHERE email=? AND password=? ");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("LOG IN AVVENUTO CORRETTAMENTE");
                privilegio = resultSet.getString("privilegio");
                String nome = resultSet.getString("nome");
                System.out.println("nome :" + nome + " privilegio: " + privilegio);
            } else {
                System.out.println("LOGIN Errato");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Privilegio.valueOf(privilegio);
    }
    public ArrayList<DefaultCliente> getListaClienti(){
        ArrayList<DefaultCliente> listaClienti=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT nome FROM utenti");
            while (resultSet.next()){
                listaClienti.add(new DefaultCliente(resultSet.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listaClienti;
    }


    public  boolean insertUtente(String nome,String cognome,String email,String password,String pri){
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO utenti (nome,cognome,email,password,privilegio) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1,nome);
            preparedStatement.setString(2,cognome);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,pri);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
                return false;
            }
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    public String getNomeUtente(String email, String password) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT nome,privilegio FROM utenti WHERE email=? AND password=? ");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getString("nome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<Bevanda> getBevande() {
        ArrayList<Bevanda> listaBevande=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM prodotti");
            while (resultSet.next()){
                listaBevande.add(new Bevanda(resultSet.getString("nome"),resultSet.getString("categoria"),resultSet.getInt("quantita_disponibile"),resultSet.getDouble("prezzo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaBevande;
    }

    public ArrayList<String> getOrdinazioneClientedaPagare(String idCLiente){
        ArrayList<String> listaOrdinazioni=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT ordinazioni.id FROM ordinazioni,utenti WHERE ordinazioni.id_cliente=utenti.id AND utenti.nome=? AND ordinazioni.stato='IN_ATTESA_DI_PAGAMENTO'");
            preparedStatement.setString(1, idCLiente);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                 listaOrdinazioni.add(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaOrdinazioni;
    }

    public ArrayList<String> getPrenotazioniClientedaPagare(String idCliente){
        ArrayList<String> listaPrenotazioi=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT prenotazioni.id FROM prenotazioni,utenti WHERE prenotazioni.id_cliente=utenti.id AND utenti.nome=? AND StatoPrenotazione='IN_ATTESA_DI_PAGAMENTO'");
            preparedStatement.setString(1, idCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                listaPrenotazioi.add(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPrenotazioi;
    }
    public void convalidaPagamentoPrenotazione(String idPrenotazione){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE prenotazioni SET StatoPrenotazione = 'PAGATA' WHERE ID = ?");
            preparedStatement.setString(1, idPrenotazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void convalidaPagamentoOrdinazione(String idOrdinazione){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE ordinazioni SET stato = 'PAGATA' WHERE ID = ?");
            preparedStatement.setString(1, idOrdinazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getIdUtente(DefaultCliente cliente){
        String id="";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT id FROM utenti WHERE nome=?");
            preparedStatement.setString(1, cliente.getID());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                id=resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int prenotaOmbrellone(DefaultPrenotazione prenotazione,DefaultCliente currentUser){
        Random random=new Random();
        int numeroPrenotazione=random.nextInt(1000);
        boolean flag=false;
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO prenotazioni (id_cliente,num_sdraio,data_Inizio,data_Fine,StatoPrenotazione,id) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1,getIdUtente(currentUser));
            preparedStatement.setInt(2,prenotazione.getListaSdraio().size());
            Calendar cal = Calendar.getInstance();
            cal.setTime(prenotazione.getDataInizio());
            java.sql.Date dataInizio=new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(3, dataInizio);
            cal.setTime(prenotazione.getDataFine());
            java.sql.Date dataFine=new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(4, dataFine);
            preparedStatement.setString(5, String.valueOf(prenotazione.getStatoPrenotazione()));
            do {
                if(checkNumeroPrenotazione(numeroPrenotazione)){
                    flag=true;
                    numeroPrenotazione=random.nextInt(1000);
                }else{
                    flag=false;
                }
            }while(flag);
            preparedStatement.setInt(6,numeroPrenotazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO dettaglioprenotazione (id_prenotazione,id_ombrellone) VALUES (?,?)");
            for(Ombrellone ombrellone:prenotazione.getListaOmbrelloni()){
                preparedStatement.setInt(1,numeroPrenotazione);
                preparedStatement.setString(2,ombrellone.getID());
                if(preparedStatement.executeUpdate()==0){
                    System.out.println("errore nell'esecuzione della query");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return numeroPrenotazione;
    }
    public void caricaOmbrelloni(){
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO ombrelloni (id_ombrellone,id_qrcode) VALUES (?,?)");
            Random random=new Random();
            for(int i=0;i<getNumeroOmbrelloni();i++){
                preparedStatement.setString(1, String.valueOf(i));
                preparedStatement.setString(2, String.valueOf(random.nextInt(1000)));
                if(preparedStatement.executeUpdate()==0){
                    System.out.println("errore nell'esecuzione della query");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int prenotaSdraio(DefaultPrenotazione prenotaizioneById, DefaultCliente currentCliente) {
        Random random=new Random();
        int numeroPrenotazione=random.nextInt(1000);
        boolean flag=false;
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO prenotazioni (id_cliente,num_sdraio,data_Inizio,data_Fine,StatoPrenotazione,id) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1,getIdUtente(currentCliente));
            preparedStatement.setInt(2,prenotaizioneById.getListaSdraio().size());
            Calendar cal = Calendar.getInstance();
            cal.setTime(prenotaizioneById.getDataInizio());
            java.sql.Date dataInizio=new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(3, dataInizio);
            cal.setTime(prenotaizioneById.getDataFine());
            java.sql.Date dataFine=new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(4, dataFine);
            preparedStatement.setString(5, String.valueOf(prenotaizioneById.getStatoPrenotazione()));
            do {
                if(checkNumeroPrenotazione(numeroPrenotazione)){
                    flag=true;
                    numeroPrenotazione=random.nextInt(1000);
                }else{
                    flag=false;
                }
            }while(flag);
            preparedStatement.setInt(6,numeroPrenotazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return numeroPrenotazione;
    }

    public int ordinazioneBar(DefaultOrdinazione ordinazione, DefaultCliente currentCliente) {
        Random random=new Random();
        int numeroOrdinazione=random.nextInt(1000);
        boolean flag=false;
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO ordinazioni (id_qrcode,id_cliente,stato,id) VALUES (?,?,?,?)");
            preparedStatement.setString(1,ordinazione.getQr_code());
            preparedStatement.setString(2,getIdUtente(currentCliente));
            preparedStatement.setString(3, String.valueOf(ordinazione.getStatoOrdinazione()));
            do{
                if(checkNumeroOrdinazione(numeroOrdinazione)){
                    flag=true;
                    numeroOrdinazione=random.nextInt(1000);
                }else{
                    flag=false;
                }
            }while(flag);
            preparedStatement.setInt(4,numeroOrdinazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO dettagli_ordini (id_prodotto,id_ordinazione,quantita) VALUES (?,?,?)");
            for(Bevanda bevanda:ordinazione.getListaBevande()){
                preparedStatement.setString(1,getIdBevanda(bevanda.getNome()));
                preparedStatement.setInt(2,numeroOrdinazione);
                preparedStatement.setInt(3,ordinazione.getQuantitaOrdinata(bevanda));
                if(preparedStatement.executeUpdate()==0){
                    System.out.println("errore nell'esecuzione della query");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  numeroOrdinazione;
    }

    private boolean checkNumeroOrdinazione(int numeroOrdinazione) {
        try{
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM ordinazioni WHERE id=?");
            preparedStatement.setInt(1, numeroOrdinazione);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private String getIdBevanda(String nome) {
        String bevandaid="";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT id FROM prodotti WHERE nome=?");
            preparedStatement.setString(1, nome);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                bevandaid=resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bevandaid;
    }

    public String getqrCode(String idOmbrellone) {
        String codice="";
        try{
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("SELECT id_qrcode FROM ombrelloni WHERE id_ombrellone=?");
            preparedStatement.setString(1,idOmbrellone);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                codice=resultSet.getString("id_qrcode");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return codice;
    }

    public void decrementaBevandaOrdinata(String bevanda, int quantita) {
        int quantitaDisponibile=getQuantita(bevanda);
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE prodotti SET  quantita_disponibile=? WHERE nome = ?");
            preparedStatement.setInt(1, quantitaDisponibile-quantita);
            preparedStatement.setString(2, bevanda);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getQuantita(String bevanda) {
        int quantita=0;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT quantita_disponibile FROM prodotti WHERE nome = ?");
            preparedStatement.setString(1, bevanda);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                quantita=resultSet.getInt("quantita_disponibile");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantita;

    }

    public void modificaOmbrelloniPrenotazione(String idPrenotazione, ArrayList<String> listaOmbrelloni) {
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO dettaglioprenotazione (id_prenotazione,id_ombrellone) VALUES (?,?)");
            for(String ombrellone:listaOmbrelloni){
                preparedStatement.setString(1,idPrenotazione);
                preparedStatement.setString(2,ombrellone);
                if(preparedStatement.executeUpdate()==0){
                    System.out.println("errore nell'esecuzione della query");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void modificaPeriodoPrenotazione(String idPrenotazione, java.util.Date dataInizio, java.util.Date dataFine){
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("UPDATE prenotazioni SET  data_inizio=?, data_fine=? WHERE id = ?");
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataInizio);
            java.sql.Date datain=new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(1,datain);
            cal.setTime(dataFine);
            java.sql.Date data=new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(2,data);
            preparedStatement.setString(3,idPrenotazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void modificaNumeroSdraio(String idPrenotazione,int numSdraio){
        int numeroSdraio=getNumeroSdarioPrenotazione(idPrenotazione);
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("UPDATE prenotazioni SET  num_sdraio=? WHERE id = ?");
            preparedStatement.setInt(1,numSdraio+numeroSdraio);
            preparedStatement.setString(2,idPrenotazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private int getNumeroSdarioPrenotazione(String idPrenotazione) {
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("SELECT num_sdraio FROM prenotazioni WHERE id=?");
            preparedStatement.setString(1,idPrenotazione);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("num_sdraio");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> getPrenotazioniCliente(String nomeCliente) {
        ArrayList<String> listaPrenotazioi=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT prenotazioni.id FROM prenotazioni,utenti WHERE prenotazioni.id_cliente=utenti.id AND utenti.nome=?");
            preparedStatement.setString(1, nomeCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                listaPrenotazioi.add(resultSet.getString("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPrenotazioi;
    }

    public void prenotazioneManuale(DefaultPrenotazione prenotazione, String idUtente) {
        try {
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("INSERT INTO prenotazioni (id_cliente,num_sdraio,data_Inizio,data_Fine,StatoPrenotazione,id) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1,getIdUtente(idUtente));
            preparedStatement.setInt(2,prenotazione.getListaSdraio().size());
            Calendar cal = Calendar.getInstance();
            cal.setTime(prenotazione.getDataInizio());
            java.sql.Date dataInizio=new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(3, dataInizio);
            cal.setTime(prenotazione.getDataFine());
            java.sql.Date dataFine=new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(4, dataFine);
            preparedStatement.setString(5, String.valueOf(prenotazione.getStatoPrenotazione()));
            checkNumeroPrenotazione(Integer.parseInt(prenotazione.getID()));
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nell'esecuzione della query");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(prenotazione.getListaOmbrelloni().size()>0) {
            Random random = new Random();
            try {
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO dettaglioprenotazione (id_prenotazione,id_ombrellone) VALUES (?,?)");
                for (Ombrellone ombrellone : prenotazione.getListaOmbrelloni()) {
                    preparedStatement.setString(1, prenotazione.getID());
                    preparedStatement.setString(2, ombrellone.getID());
                    if (preparedStatement.executeUpdate() == 0) {
                        System.out.println("errore nell'esecuzione della query");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String getIdUtente(String idUtente) {
        String id="";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT id FROM utenti WHERE nome=?");
            preparedStatement.setString(1, idUtente);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                id=resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void removePrenotazione(String idPrenotazione) {
        try{
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("DELETE FROM dettaglioprenotazione WHERE id_prenotazione=?");
            preparedStatement.setString(1,idPrenotazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nella cancellazione in dettagli prenotazioni");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            PreparedStatement preparedStatement=(PreparedStatement) connection.prepareStatement("DELETE FROM prenotazioni WHERE id=?");
            preparedStatement.setString(1,idPrenotazione);
            if(preparedStatement.executeUpdate()==0){
                System.out.println("errore nella cancellazione in prenotazioni");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int addPrenotazioneToDb(String idUtente, java.util.Date dataInizio, java.util.Date dataFine, ArrayList<String> listaOmbrelloni, int numSdraio) {
        Random random=new Random();
        int numeroPrenotazione=random.nextInt(1000);
        boolean flag=false;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO prenotazioni (id_cliente,num_sdraio,data_Inizio,data_Fine,StatoPrenotazione,id) VALUES (?,?,?,?,?,?)");
            preparedStatement.setString(1, getIdUtente(idUtente));
            preparedStatement.setInt(2, numSdraio);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataInizio);
            java.sql.Date dataIn = new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(3, dataIn);
            cal.setTime(dataFine);
            java.sql.Date dataFi = new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(4, dataFi);
            preparedStatement.setString(5, String.valueOf(StatoPreOrd.IN_ATTESA_DI_PAGAMENTO));
            do {
                if(checkNumeroPrenotazione(numeroPrenotazione)){
                    flag=true;
                    numeroPrenotazione=random.nextInt(1000);
                }else{
                    flag=false;
                }
            }while(flag);
            preparedStatement.setInt(6,numeroPrenotazione);
            if (preparedStatement.executeUpdate() == 0) {
                System.out.println("errore nell'esecuzione della query");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertIntoDettaglioOrdinazioni(numeroPrenotazione,listaOmbrelloni);
        return numeroPrenotazione;
    }

    private boolean checkNumeroPrenotazione(int numeroPrenotazione) {
        try{
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM prenotazioni WHERE id=?");
            preparedStatement.setInt(1, numeroPrenotazione);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private void insertIntoDettaglioOrdinazioni(int numeroPrenotazione,ArrayList<String>listaOmbrelloni){
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO dettaglioprenotazione (id_prenotazione,id_ombrellone) VALUES (?,?)");
            for (String ombrellone : listaOmbrelloni) {
                preparedStatement.setInt(1, numeroPrenotazione);
                preparedStatement.setString(2, ombrellone);
                if (preparedStatement.executeUpdate() == 0) {
                    System.out.println("errore nell'esecuzione della query");
                }
            }
           } catch (SQLException e) {
                e.printStackTrace();
           }
    }

    public ArrayList<DefaultOrdinazione> getOrdinazioniDb() {
        ArrayList<DefaultOrdinazione> listaOrdinazioni=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM ordinazioni");
            while(resultSet.next()) {
                listaOrdinazioni.add(new DefaultOrdinazione(resultSet.getString("id")));
                for(DefaultOrdinazione ordinazione:listaOrdinazioni){
                    if(ordinazione.getID().equals(resultSet.getString("id"))){
                        ordinazione.setStatoOrdinazione(StatoPreOrd.valueOf(resultSet.getString("stato")));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        popolaOrdinazioni(listaOrdinazioni);
        return listaOrdinazioni;
    }

    private void popolaOrdinazioni(ArrayList<DefaultOrdinazione> listaOrdinazioni) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT dettagli_ordini.id_prodotto,dettagli_ordini.quantita FROM dettagli_ordini,prodotti WHERE dettagli_ordini.id_prodotto=prodotti.id AND id_ordinazione=?");
            for (DefaultOrdinazione ordinazione:listaOrdinazioni) {
                preparedStatement.setString(1, ordinazione.getID());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    ordinazione.addBevanda(getBevanda(resultSet.getString("dettagli_ordini.id_prodotto")), resultSet.getInt("quantita"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Bevanda getBevanda(String id) {
        Bevanda bevanda = null;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT * FROM prodotti WHERE id=?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    bevanda=new Bevanda(resultSet.getString("nome"),resultSet.getString("categoria"),resultSet.getInt("quantita_disponibile"),resultSet.getFloat("prezzo"));
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bevanda;
    }

    public void removePrenotazioneFromDettagliOrdinazione(String prenotazione) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("DELETE FROM dettaglioprenotazione WHERE dettaglioprenotazione.id_prenotazione=?");
                preparedStatement.setString(1, prenotazione);
                if(preparedStatement.executeUpdate()==0) {
                    System.out.println("errore nell'esecuzione della delete");
                }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE prenotazioni SET  num_sdraio=0 WHERE id = ?");
            preparedStatement.setString(1, prenotazione);
            if(preparedStatement.executeUpdate()==0) {
                System.out.println("errore nell'esecuzione della update");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void creaAttivita(String nome, String luogo, java.util.Date dataInizio, java.util.Date dataFine, int numMassimoPersone) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO attivita (nome,luogo,data_inizio,data_fine,numero_posti) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, luogo);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataInizio);
            java.sql.Date dataIn = new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(3, dataIn);
            cal.setTime(dataFine);
            java.sql.Date dataFi = new java.sql.Date(cal.getTime().getTime());
            preparedStatement.setDate(4, dataFi);
            preparedStatement.setInt(5, numMassimoPersone);
            if(preparedStatement.executeUpdate()==0) {
                System.out.println("errore nell'esecuzione della delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void prenotaAttivita(String nomeAttivita, int numPersone, String id) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("INSERT INTO prenotazioni_attivita (id_attivita,id_cliente,posti_prenotati) VALUES (?,?,?)");
            preparedStatement.setString(1, getIdAttivita(nomeAttivita));
            preparedStatement.setString(2, getIdUtente(id));
            preparedStatement.setInt(3, numPersone);

            if(preparedStatement.executeUpdate()==0) {
                System.out.println("errore nell'esecuzione della delete");
            }
            decrementaNumeroPostiAttivita(getIdAttivita(nomeAttivita),numPersone);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void decrementaNumeroPostiAttivita(String idAttivita, int numPersone) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("UPDATE attivita SET  numero_posti=? WHERE id = ?");
            preparedStatement.setInt(1, getNewNumeroPosti(numPersone,idAttivita));
            preparedStatement.setString(2, idAttivita);
            if(preparedStatement.executeUpdate()==0) {
                System.out.println("errore nell'esecuzione della delete");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getNewNumeroPosti(int numPersone,String idAttivita) {
        int numeroPosti=0;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT numero_posti FROM attivita WHERE id=?");
            preparedStatement.setString(1, idAttivita);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                numeroPosti=resultSet.getInt("numero_posti")-numPersone;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numeroPosti;

    }

    private String getIdAttivita(String nomeAttivita) {
        String idAttivita="";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT id FROM attivita WHERE nome=?");
            preparedStatement.setString(1, nomeAttivita);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                idAttivita=resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAttivita;
    }

    public ArrayList<DefaultAttivita> getAttivitaFromDb() {
        ArrayList<DefaultAttivita>listaAttivita=new ArrayList<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM attivita");
            while(resultSet.next()) {
                listaAttivita.add(new DefaultAttivita(resultSet.getString("nome"),resultSet.getString("luogo"),resultSet.getDate("data_inizio"),resultSet.getDate("data_fine"),resultSet.getInt("numero_posti")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAttivita;

    }
}


