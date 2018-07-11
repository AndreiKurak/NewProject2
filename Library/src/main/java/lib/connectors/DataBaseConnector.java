package lib.connectors;

import common.DataConnection;
import lib.Book;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnector implements DataConnection {

    private static final String url = "jdbc:mysql://localhost:3306/doc_register?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "1234A5";

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;
         //Books
    public ArrayList read() throws IOException, ClassNotFoundException {
        try {
            Books b = new Books();
            connection = DriverManager.getConnection(url, user, password);

            String query = "SELECT * FROM library";
            stmt = connection.createStatement();
            //PreparedStatement ps = connection.prepareStatement(querty);
            rs = stmt.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                b.books.get(i).setAuthor(rs.getString("author"));
                b.books.get(i).setAuthor(rs.getString("title"));
                b.books.get(i).setAuthor(rs.getString("year"));
                System.out.println(rs.getString("author"));               // int count = rs.getInt(1);

            }

        }
        catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        finally {
            try { connection.close(); } catch(SQLException se) { System.out.println(se.getClass()); }
            try { stmt.close(); } catch(SQLException se) { System.out.println(se.getClass()); }
            try { rs.close(); } catch(SQLException se) { System.out.println(se.getClass()); }
        }
        return null;
    }
                    //Books
    public void write(List myObject) throws IOException {

    }

    public static void main(String[] args){
        DataBaseConnector connector = new DataBaseConnector();
        try {
            connector.read();
        }
        catch (Exception ex){

        }

    }



    public void connect(String query){
        try {
            connection = DriverManager.getConnection(url, user, password);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { connection.close(); } catch(SQLException se) { System.out.println(se.getClass()); }
            try { stmt.close(); } catch(SQLException se) { System.out.println(se.getClass()); }
        }
    }
}
