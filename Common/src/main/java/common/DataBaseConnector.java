package common;

import java.sql.*;

public class DataBaseConnector {

    private static final String url = "jdbc:mysql://localhost:3306/doc_register?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "1234A5";

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;

    public void connect(String query){
        try {
        connection = DriverManager.getConnection(url, user, password);
        stmt = connection.createStatement();
        stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { connection.close(); } catch(SQLException se) { System.out.println(se.getClass()); /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { System.out.println(se.getClass()); /*can't do anything */ }
        }
    }
}
