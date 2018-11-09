package lib.hibernate_tests;

import lib.PropertyValuesGetter;
import lib.connectors.DataConnectionException;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateTest {

    SessionFactory sessionFactory;

    @Before
    public void beforeDoThis() {
        PropertyValuesGetter valuesGetter = new PropertyValuesGetter();

        try (Connection connection = DriverManager.getConnection(valuesGetter.getUrl(), valuesGetter.getUser(), valuesGetter.getPassword());
             Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS library(id int(11), author varchar(128), title varchar(128), year varchar(128), book_genre varchar(128))");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS book_genres(id int(11), genre varchar(128))");
        }
        catch (Exception ex) {
            throw new DataConnectionException("Error during table initialization", ex);
        }

        valuesGetter.getProp().setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/doc_register_test?serverTimezone=UTC&useSSL=false");
        sessionFactory = new Configuration().
                addPackage("lib.hibernate_tests").
                addProperties(valuesGetter.getProp()).
                addAnnotatedClass(TestBook.class).
                addAnnotatedClass(TestBookGenres.class).
                buildSessionFactory();
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createQuery("DELETE FROM TestBook").executeUpdate();
            session.createQuery("DELETE FROM TestBookGenres").executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @After
    public void afterDoThis() {
        sessionFactory.close();
    }

    @Test
    public void shouldCreateTwoBooksInTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            TestBook book = new TestBook(1, "Andrey", "Future World", "2016");
            genre.addBook(book);
            book.setBookGenre(genre);

            book = new TestBook(2,"Genry", "Hell", "2004");
            genre.addBook(book);

            book.setBookGenre(genre);
            session.save(genre);
            session.getTransaction().commit();

            assertThat(session.createQuery("from TestBook").list().size()).isEqualTo(2);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void shouldAddTwoBooksToOneBookGenre() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            TestBook book = new TestBook(10, "Andrey", "Future World", "2016");
            genre.addBook(book);
            book.setBookGenre(genre);


            book = new TestBook(11,"Genry", "Hell", "2003");
            genre.addBook(book);

            book.setBookGenre(genre);
            session.save(genre);
            session.getTransaction().commit();

            TestBookGenres bookGenre = session.get(TestBookGenres.class, 1);
            assertThat(bookGenre.getBooks().size()).isEqualTo(2);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void shouldDeleteOrNotDeleteBook() {     //???
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            TestBook book = new TestBook(10, "Andrey", "Future World", "2016");
            genre.addBook(book);
            book.setBookGenre(genre);

            session.save(genre);    session.save(book);
            session.getTransaction().commit();

            TestBookGenres bookGenre = session.get(TestBookGenres.class, 1);

            session.beginTransaction();
            session.remove(bookGenre);

            session.getTransaction().commit();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void shouldNotAbleToWorkWithLoadedObjectAfterSessionClosed() {
        try  {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            TestBook book = new TestBook(10, "Andrey", "Future World", "2016");
            genre.addBook(book);
            book.setBookGenre(genre);

            session.save(book);
            session.getTransaction().commit();

            TestBook testBook = session.load(TestBook.class, 10);
            //assertThat(testBook.getAuthor()).isEqualTo("Andrey");
            session.close();

            assertThat(testBook.getAuthor()).isNotEqualTo("Andrey");
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test (expected = ObjectNotFoundException.class)
    public void shouldThrowAnExceptionWhenTryingToGetFieldOfTheObjectLoadedByNonexistentId() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            TestBook book = new TestBook(10, "Andrey", "Future World", "2016");
            genre.addBook(book);
            book.setBookGenre(genre);

            session.save(book);
            session.getTransaction().commit();

            TestBook testBook = session.load(TestBook.class, 100);
            testBook.getAuthor();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test
    public void shouldCreateDifferentObjectWithSessionSaveAfterEvict() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            TestBook book = new TestBook(10, "Andrey", "Future World", "2016");
            genre.addBook(book);
            book.setBookGenre(genre);

            Integer id1 = (Integer) session.save(book);  //save  //persist, refresh, replicate

            session.evict(book);

            Integer id2 = (Integer) session.save(book);
            System.out.println(id1 == id2);
            assertThat(id1).isNotEqualTo(id2);

            session.getTransaction().commit();
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
