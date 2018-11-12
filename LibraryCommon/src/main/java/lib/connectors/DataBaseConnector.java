package lib.connectors;

import lib.PropertyValuesGetter;
import lib.library_entities.BooksFromDB;
import lib.library_entities.Books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseConnector implements DataConnection {
    
    private PropertyValuesGetter valuesGetter = new PropertyValuesGetter();

    public DataBaseConnector() {//SessionFactory sessionFactory
        //this.sessionFactory = sessionFactory;
    }

    private void initializeTable() {
        try (Connection connection = DriverManager.getConnection(valuesGetter.getUrl(), valuesGetter.getUser(), valuesGetter.getPassword());
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS library(id int(11), author varchar(128), title varchar(128), year varchar(128))");
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during table initialization", ex);
        }
    }

    public void initializeTestTables() {
        valuesGetter.getProp().setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/doc_register_test?serverTimezone=UTC&useSSL=false");
        try (Connection connection = DriverManager.getConnection(valuesGetter.getUrl(), valuesGetter.getUser(), valuesGetter.getPassword());
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS library(id int(11) primary key not null, author varchar(128), title varchar(128), year varchar(128), genre_id int(11), isbn_id int(11))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS book_genres(id int(11) primary key not null, genre varchar(128))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS book_isbn(id int(11) primary key not null, isbn varchar(128))");
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during table initialization", ex);
        }
    }

    @Override
    public BooksFromDB read() {
        initializeTable();

        return new BooksFromDB();   //new BooksFromDB(sessionFactory);
    }

    @Override
    public void write(Books myObject) {
        //nothing to do...
    }
}
