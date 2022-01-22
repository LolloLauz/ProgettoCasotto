package com.example.progettocasotto.DataBase;

import com.example.progettocasotto.Model.Chalet.Bar.Bevanda;
import com.example.progettocasotto.Model.Spiaggia.DefaultPrenotazione;
import com.example.progettocasotto.Model.Spiaggia.Ombrellone;
import com.example.progettocasotto.Model.Spiaggia.Sdraio;
import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
            while(resultSet.next()) {
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

    public void getOrdinazioneCliente(String idCLiente){
        String risultato="";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT ordinazioni.id FROM ordinazioni,utenti WHERE ordinazioni.id_cliente=utenti.id AND utenti.nome=?");
            preparedStatement.setString(1, idCLiente);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                 risultato=resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getPrenotazioniCliente(String idCliente){
        String risultato="";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT prenotazioni.id FROM prenotazioni,utenti WHERE prenotazioni.id_cliente=utenti.id AND utenti.nome=?");
            preparedStatement.setString(1, idCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                risultato=resultSet.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("la prenotazione di "+idCliente+" ha numero "+risultato);
    }
}

