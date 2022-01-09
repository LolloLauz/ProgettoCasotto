package ProgettoCasotto;

import ProgettoCasotto.PersonaleDelloChalet.Privilegio;

import java.sql.*;

public class GestoreDB {
    Connection connection;
    String email;
    String password;

    public GestoreDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/casottodb", "root", "");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Privilegio getEmailPassw(String email, String password) {
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

}

