package lib.connectors;

import lib.Book;

import java.sql.*;

public class DataBaseConnector implements DataConnection {

    private final String name = "database";
    private static final String url = "jdbc:mysql://localhost:3306/doc_register?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "1234A5";

    public String getName() {
        return name;
    }

    public Books read(){
        Books books = new Books();
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
        catch (SQLException sqlEx) {
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
                count=rs.getInt(1);
            System.out.println("count " + count);

            int ii = 0;
            boolean stop = false;
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
                rs.first();
                prStmt = connection.prepareStatement("DELETE FROM library WHERE id=?");
                while (rs.next()){
                    if (!stop && books.booksList.get(ii).getId() != rs.getInt("id")){       //когда произойдёт, перестать сравнивать
                        prStmt.setInt(1, rs.getInt("id"));
                        prStmt.executeUpdate();
                        stop = true;
                    }
                    ii++;
                    if (stop){
                        prStmt = connection.prepareStatement("UPDATE library SET id=id-1 WHERE id>?");
                        prStmt.setInt(1, rs.getInt("id") - 1);
                        prStmt.executeUpdate();
                        break;
                    }
                }
            }
            else {
                //update
                rs.first();
                for (int i = 0; i<books.booksList.size(); i++){
                    rs.next();
                    if (!books.booksList.get(i).getAuthor().equals(rs.getString("author"))
                            || !books.booksList.get(i).getTitle().equals(rs.getString("title"))
                            || !books.booksList.get(i).getYear().equals(rs.getString("year"))){
                        prStmt = connection.prepareStatement("UPDATE library SET author=?, title=?, year=? WHERE id=?");
                        prStmt.setString(1, books.booksList.get(i).getAuthor());
                        prStmt.setString(2, books.booksList.get(i).getTitle());
                        prStmt.setString(3, books.booksList.get(i).getYear());
                        prStmt.setInt(4, books.booksList.get(i).getId());
                        prStmt.executeUpdate();
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during data loading into the table", ex);
        }
        finally {
            try { prStmt.close(); } catch(SQLException se) { /*...*/ }
        }
    }
}
