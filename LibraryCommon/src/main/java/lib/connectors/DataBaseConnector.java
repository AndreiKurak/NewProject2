package lib.connectors;

import lib.PropertyValuesGetter;
import lib.library_entities.BooksFromDB;
import lib.library_entities.Books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseConnector implements DataConnection {

    private String url = "jdbc:mysql://localhost:3306/doc_register?serverTimezone=UTC&useSSL=false";
    private PropertyValuesGetter valuesGetter = new PropertyValuesGetter();

    private void initializeTable() {
        try (Connection connection = DriverManager.getConnection(url, valuesGetter.getUser(), valuesGetter.getPassword());
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS library(id int(11), author varchar(128), title varchar(128), year varchar(128))");
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during table initialization", ex);
        }
    }

    @Override
    public BooksFromDB read() {
        initializeTable();

        return new BooksFromDB();
    }

    @Override
    public void write(Books myObject) {
        //nothing to do...
    }
}
