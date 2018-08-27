package lib;

import lib.connectors.Books;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnectionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class DataBaseConnectorTest {

    private static final String url = "jdbc:mysql://localhost:3306/doc_register_test?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "1234A5";

    static class Preparer {
        static void fillTable(){
            try (Connection connection = DriverManager.getConnection(url, user, password);
                 Statement stmt = connection.createStatement()) {
                stmt.executeUpdate("INSERT INTO library (id, author, title, year) VALUES(1, 'Author1', 'Magic', 1930)");
                stmt.executeUpdate("INSERT INTO library (id, author, title, year) VALUES(2, 'Author2', 'Freedom', 1948)");
                stmt.executeUpdate("INSERT INTO library (id, author, title, year) VALUES(3, 'Author3', 'Coming', 1990)");
            }
            catch (Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }

    @Test
    public void shouldLoadDataFromDatabaseCorrectly() {
        System.out.println("test");
        DataBaseConnector dbc = new DataBaseConnector();
        Books books = dbc.read();
        assertThat(books.list().get(0).getAuthor()).isEqualTo("auth");
        assertThat(books.list().get(0).getYear()).isEqualTo("1678");
    }

    @Test (expected = DataConnectionException.class)
    public void shouldThrowDataConnectionExceptionIfTheArgumentIsNull() {
        DataBaseConnector dbc = new DataBaseConnector();
        dbc.write(null);
    }

    @Test
    public void shouldNotReturnNull() {
        DataBaseConnector dbc = new DataBaseConnector("doc_register_test");
        assertThat(dbc.read()).isNotNull();
    }

    @Test
    public void shouldAddNewBook() {
        DataBaseConnector dbc = new DataBaseConnector("doc_register_test");
        Books books = new Books();
        books.add(new Book("a", "t", "1980"));
        dbc.write(books);

        Books booksFromTable = dbc.read();
        assertThat(booksFromTable.list().get(0).getAuthor()).isEqualTo("a");
    }

    @Test
    public void shouldDeleteBook() {
        Preparer.fillTable();
        DataBaseConnector dbc = new DataBaseConnector("doc_register_test");
        Books books = dbc.read();
        books.delete(2);
        dbc.write(books);

        Books booksFromTable = dbc.read();
        assertThat(booksFromTable.list().size()).isEqualTo(books.list().size());
    }

    @Test
    public void shouldUpdateBooksCorrectly(){
        Preparer.fillTable();
        DataBaseConnector dbc = new DataBaseConnector("doc_register_test");
        Books books = dbc.read();
        books.update(1, new Book("newAuthor", "Sorrow", "1890"));
        dbc.write(books);

        Books booksFromTable = dbc.read();
        assertThat(booksFromTable.list().get(1).getAuthor()).isEqualTo(books.list().get(1).getAuthor());
    }

    @After
    public void clearTable(){
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DELETE FROM library");
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
        System.out.println("after");
    }
}
