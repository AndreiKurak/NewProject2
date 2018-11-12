package lib.hibernate_tests;

import lib.PropertyValuesGetter;
import lib.connectors.DataBaseConnector;
import lib.connectors.DataConnectionException;
import lib.connectors.SessionFactoryGetter;
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

    private SessionFactory sessionFactory;

    @Before
    public void beforeDoThis() {
        new DataBaseConnector().initializeTestTables();

        PropertyValuesGetter valuesGetter = new PropertyValuesGetter();
        valuesGetter.getProp().setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/doc_register_test?serverTimezone=UTC&useSSL=false");

        SessionFactoryGetter.setSessionFactory(new Configuration().
                addPackage("lib.hibernate_tests").
                addProperties(valuesGetter.getProp()).
                addAnnotatedClass(TestBook.class).
                addAnnotatedClass(TestBookGenres.class).
                addAnnotatedClass(TestBookISBN.class).
                buildSessionFactory());
        sessionFactory = SessionFactoryGetter.getSessionFactory();
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createQuery("DELETE FROM TestBook").executeUpdate();
            session.createQuery("DELETE FROM TestBookGenres").executeUpdate();
            session.createQuery("DELETE FROM TestBookISBN").executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @After
    public void afterDoThis() {
        SessionFactoryGetter.getSessionFactory().close();
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
    public void shouldDeleteOrNotDeleteBook() {     //with cascadeType.All will delete book
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
    public void shouldCreateDifferentObjectWithSessionSaveAfterEvict() {   ///
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            TestBook book = new TestBook(10, "Andrey", "Future World", "2016");
            genre.addBook(book);
            book.setBookGenre(genre);

            Integer id1 = (Integer) session.save(book);

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

    @Test
    public void shouldUpdateBookWithMethodMerge() {
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
            session.close();
            book.setAuthor("Unknown");

            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();
            session2.update(book); //update or merge
            session2.getTransaction().commit();
            assertThat(book.getAuthor()).isEqualTo("Unknown");
            session2.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test
    public void checkExample() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            session.save(genre);
            session.getTransaction().commit();
            session.close();

            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();

            TestBookGenres genre2 = session2.load(TestBookGenres.class, 1);
            genre2.setGenre("Horror");
            session2.getTransaction().commit();
            session2.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test
    public void shouldAddNewGenreWithMethodPersist() { 
        TestBookGenres genre = new TestBookGenres();
        genre.setId(1);
        genre.setGenre("Fantastic");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(genre);
            
            session.getTransaction().commit();
            session.close();
            genre.setId(2);
            genre.setGenre("Thriller");

            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();

            session2.persist(genre);

            session2.getTransaction().commit();
            assertThat(session2.createQuery("from TestBookGenres").list().size()).isEqualTo(2);
            session2.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test
    public void shouldUpdateGenreWithMethodReplicateWhereReplicationModeSetAsLatestVersion() {
        TestBookGenres genre = new TestBookGenres();
        genre.setId(1);
        genre.setGenre("Fantastic");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(genre);

            session.getTransaction().commit();
            session.close();
            genre.setGenre("Horror");
            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();
            session2.replicate(genre, ReplicationMode.LATEST_VERSION);

            session2.getTransaction().commit();
            assertThat(((TestBookGenres)session2.createQuery("from TestBookGenres where id=1").uniqueResult()).getGenre()).
                    isEqualTo("Horror");
            session2.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test
    public void shouldEvictSoNoBooksWillBeSaved() {
        TestBookGenres genre = new TestBookGenres();
        genre.setId(1);
        genre.setGenre("Fantastic");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(genre);
            session.evict(genre);
            session.getTransaction().commit();
            assertThat(session.createQuery("from TestBookGenres").list().size()).
                    isEqualTo(0);
            session.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test
    public void shouldAddGenreWithFlushDespiteOfEvict() {
        TestBookGenres genre = new TestBookGenres();
        genre.setId(1);
        genre.setGenre("Fantastic");

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(genre);
            session.flush();
            session.evict(genre);
            session.getTransaction().commit();
            assertThat(session.createQuery("from TestBookGenres").list().size()).
                    isEqualTo(1);
            session.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test
    public void shouldAddBookWithNumber() {
        TestBookISBN number = new TestBookISBN();
        number.setId(1);
        number.setNumber("1876504741296");

        TestBook book = new TestBook(1, "Alexandr", "Morning", "1965");
        book.setNumber(number);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            number.setBook(book);
            session.save(number);

            session.getTransaction().commit();
            assertThat(((TestBook)session.createQuery("from TestBook").uniqueResult()).getNumber()).
                    isNotNull();
            session.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
