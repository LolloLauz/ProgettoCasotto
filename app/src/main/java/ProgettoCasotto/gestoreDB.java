package ProgettoCasotto;

import java.sql.*;

public class gestoreDB {
    String url="jdbc:mysql://localhost";
    Connection connection;
    ResultSet rs;
    Statement st;

    public gestoreDB(){
        {
            try {
                connection = DriverManager.getConnection(url,"root","");
                String sql="SELECT * FROM Utente;";
                st=connection.createStatement();
                rs=st.executeQuery(sql);
                while(rs.next()==true){
                    System.out.println(rs.getInt("ID")+" "+ rs.getString("nome"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
