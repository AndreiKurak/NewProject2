package lib;

import lib.connectors.Books;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnectionException;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;

public class DataBaseConnectorTest {

    private static final String url = "jdbc:mysql://localhost:3306/doc_register_test?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "1234A5";

    @Test
    public void shouldLoadDataFromDatabaseCorrectly() {
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
    public void shouldReturnBooks() {
        DataBaseConnector dbc = new DataBaseConnector("doc_register_test");
        assertThat(dbc.read()).isInstanceOf(Books.class);
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
