package lib.connectors;

import lib.Book;

import java.sql.*;
import java.util.Objects;

public class DataBaseConnector implements DataConnection {

    private String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "1234A5";

    public DataBaseConnector() {
        url += "doc_register?serverTimezone=UTC";
    }

    public DataBaseConnector(String databaseName) {
        url += databaseName + "?serverTimezone=UTC";
    }

    public Books read(){
        Books books = new Books();

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex){
            throw new DataConnectionException("jdbc driver registration failed", ex);
        }

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM library")) {
            int i = 0;
            while (rs.next()) {
                books.booksList.add(new Book(rs.getString("author"), rs.getString("title"), rs.getString("year")));
                books.booksList.get(i).setId(rs.getInt("id"));
                i++;
            }
        }
        catch (Exception sqlEx) {
            throw new DataConnectionException("Data reading from table failed", sqlEx);
        }

        return books;
    }

    public void write(Books books) {
        PreparedStatement prStmt = null;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM library")) {
            int count=0;
            while(rs.next())
                //count=rs.getInt(1);
                count++;
            System.out.println("count: " + count);

            int bookIndex = 0;
            if (books.booksList.size() > count){
                //insert new book
                prStmt = connection.prepareStatement("INSERT INTO library (id, author, title, year) VALUES(?, ?, ?, ?)");
                prStmt.setInt(1, books.booksList.get(books.booksList.size() - 1).getId());
                prStmt.setString(2, books.booksList.get(books.booksList.size() - 1).getAuthor());
                prStmt.setString(3, books.booksList.get(books.booksList.size() - 1).getTitle());
                prStmt.setString(4, books.booksList.get(books.booksList.size() - 1).getYear());
                prStmt.executeUpdate();
            }
            else if (books.booksList.size() < count) {
                //find id, delete book
                rs.beforeFirst();
                prStmt = connection.prepareStatement("DELETE FROM library WHERE id=?");
                while (rs.next()) {
                    if (bookIndex >= books.booksList.size() || books.booksList.get(bookIndex).getId() != rs.getInt("id")){
                        prStmt.setInt(1, rs.getInt("id"));
                        prStmt.executeUpdate();
                        break;
                    }
                    bookIndex++;
                }
            }
            else {
                //update book info
                rs.first();
                for (int i = 0; i<books.booksList.size(); i++){
                    if (!Objects.equals(books.booksList.get(i).getAuthor(), rs.getString("author"))
                            || !Objects.equals(books.booksList.get(i).getTitle(), rs.getString("title"))
                            || !Objects.equals(books.booksList.get(i).getYear(), rs.getString("year"))) {
                        prStmt = connection.prepareStatement("UPDATE library SET author=?, title=?, year=? WHERE id=?");
                        prStmt.setString(1, books.booksList.get(i).getAuthor());
                        prStmt.setString(2, books.booksList.get(i).getTitle());
                        prStmt.setString(3, books.booksList.get(i).getYear());
                        prStmt.setInt(4, books.booksList.get(i).getId());
                        prStmt.executeUpdate();
                        break;
                    }
                    rs.next();
                }
            }
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during data loading into the table", ex);
        }
        finally {
            try { prStmt.close(); } catch (Exception ex) {}
        }
    }
}
