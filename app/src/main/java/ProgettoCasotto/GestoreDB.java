package ProgettoCasotto;

import java.sql.*;

public class GestoreDB {
    Connection connection;
    String nome;
    String passwd;

    public GestoreDB(String nome, String passwd) {
        this.nome = nome;
        this.passwd = passwd;
    }

    public void connettiaDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/casottodb", "root", "");
            PreparedStatement preparedStatement=(PreparedStatement)connection.prepareStatement("SELECT email,privilegio FROM utente WHERE nome=? AND password=? ");
            preparedStatement.setString(1,nome);
            preparedStatement.setString(2,passwd);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("LOG IN AVVENUCO CORRETTAMENTE");
                String privilegio = resultSet.getString("privilegio");
                String email = resultSet.getString("email");
                System.out.println("email:" + nome + " priviledio: " + privilegio);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

