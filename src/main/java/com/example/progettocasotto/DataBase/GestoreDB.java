package com.example.progettocasotto.DataBase;

import com.example.progettocasotto.Model.Utenti.DefaultCliente;

import java.sql.*;
import java.util.ArrayList;

public class GesoreDB {

    Connection connection;
    public GesoreDB() {
public class GestoreDB {

    Connection connection;
    public GestoreDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/casottodb", "root", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Privilegio getPrivilegio(String email, String password) {
        String privilegio = null;
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT nome,privilegio FROM utente WHERE email=? AND password=? ");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                System.out.println("LOG IN AVVENUTO CORRETTAMENTE");
                privilegio = resultSet.getString("privilegio");
                String nome = resultSet.getString("nome");
                System.out.println("email:" + nome + " privilegio: " + privilegio);
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
            ResultSet resultSet=statement.executeQuery("SELECT nome FROM utente");
            while (resultSet.next()){
                listaClienti.add(new DefaultCliente(resultSet.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listaClienti;
    }

    public String getNomeUtente(String email, String password) {
        try {
            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement("SELECT nome,privilegio FROM utente WHERE email=? AND password=? ");
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
}
