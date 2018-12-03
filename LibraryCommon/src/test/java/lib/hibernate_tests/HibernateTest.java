package lib.hibernate_tests;

import javax.persistence.OptimisticLockException;
import lib.PropertyValuesGetter;
import lib.connectors.DataBaseConnector;
import lib.connectors.SessionFactoryGetter;
import org.hibernate.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.hibernate.cfg.Configuration;

import static org.assertj.core.api.Assertions.assertThat;

public class HibernateTest {

    private SessionFactory sessionFactory;

    @Before
    public void beforeDoThis() {
       // new DataBaseConnector().initializeTestTables();

        PropertyValuesGetter valuesGetter = new PropertyValuesGetter();
        valuesGetter.getProp().setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/doc_register_test?serverTimezone=UTC&useSSL=false");
        valuesGetter.addProperty();

        sessionFactory = SessionFactoryGetter.getSessionFactory(new Configuration().
                addPackage("lib.hibernate_tests").
                addProperties(valuesGetter.getProp()).
                addAnnotatedClass(TestBook.class).
                addAnnotatedClass(TestBookGenres.class).
                addAnnotatedClass(TestBookISBN.class).
                buildSessionFactory());
        /*try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createQuery("DELETE FROM TestBook").executeUpdate();
            session.createQuery("DELETE FROM TestBookGenres").executeUpdate();
            session.createQuery("DELETE FROM TestBookISBN").executeUpdate();
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }*/
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
    public void shouldDeleteOrNotDeleteBook() {     //with cascadeType.All will delete book
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            TestBookGenres genre = new TestBookGenres();
            genre.setId(1);
            genre.setGenre("Fantastic");

            TestBook book = new TestBook(10, "Andrey", "Future World", "2016");
            genre.addBook(book);
            book.setBookGenre(genre);

            session.save(genre);
            session.save(book);
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

    @Test
    public void shouldAddLastChangesWithUpdate() {
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
            session.close();

            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();
            number.setNumber("34580-197439857");
            session2.update(number);
            number.setNumber("123");
            session2.getTransaction().commit();
            assertThat(session2.get(TestBookISBN.class, 1).getNumber()).isEqualTo("123");
            session2.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test
    public void shouldSaveNumberDespiteEvictingBecauseOfFlush() {
        TestBookISBN number = new TestBookISBN();
        number.setId(1);
        number.setNumber("1876504741296");

        TestBook book = new TestBook(1, "Alexandr", "Morning", "1965");
        book.setNumber(number);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //If flush mode is 'AUTO' before firing any query hibernate will check if there are any tables to be updated
            session.setFlushMode(FlushMode.AUTO);   //manual, commit, always +
            System.out.println(session.getFlushMode().name());
            number.setBook(book);
            session.save(number);
            session.createQuery("from TestBook").list().size();

            session.evict(number);
            session.getTransaction().commit();
            assertThat(session.get(TestBookISBN.class, 1)).isNotNull();
            session.close();
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    @Test (expected = OptimisticLockException.class)
    public void shouldThrowAnExceptionBecauseOfTryingToUpdateEntitiesWithOptimisticLockingInParallelTransactions() {
        try {
            Session session0 = sessionFactory.openSession();
            session0.beginTransaction();
                TestBookISBN isbn = new TestBookISBN(1, "123", new TestBook(1, "Alexandr", "Morning", "1965"));
                session0.save(isbn);
            session0.getTransaction().commit();
            session0.close();

            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();
            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();

            TestBookISBN isbn1 = session1.get(TestBookISBN.class, 1);
            session1.lock(isbn1, LockMode.OPTIMISTIC);
            isbn1.setNumber("1234");
            session1.update(isbn1);
            TestBookISBN isbn2 = session2.get(TestBookISBN.class, 1);
            isbn2.setNumber("4321");

            session1.getTransaction().commit();
            session1.close();

            session2.getTransaction().commit();
            session2.close();
        } catch (Exception e) {
            System.out.println("---" + e);
            throw e;
        }
    }

    @Test
    public void shouldTakeTheSecondQueryFromCache() {
        try {
            Session session0 = sessionFactory.openSession();
            session0.beginTransaction();
            TestBookISBN isbn = new TestBookISBN(1, "123", new TestBook(1, "Alexandr", "Morning", "1965"));
            session0.save(isbn);
            session0.getTransaction().commit();
            session0.close();

            Session session1 = sessionFactory.openSession();
            TestBookISBN isbn1 = session1.get(TestBookISBN.class, 1);
            //System.out.println(isbn1.getId() + " " + isbn1.getNumber() + " " + isbn1.getBook());
            session1.close();
            Session session2 = sessionFactory.openSession();
            TestBookISBN isbn2 = session2.get(TestBookISBN.class, 1);
            //System.out.println(isbn2.getId() + " " + isbn2.getNumber() + " " + isbn2.getBook());
            session2.close();
        } catch (Exception e) {
            throw e;
        }
    }

    @Test
    public void shouldReturnNullBecauseFlushWasNotExecutedBeforeQuery() {
        try {
            Session session0 = sessionFactory.openSession();
            session0.beginTransaction();
            TestBookISBN isbn = new TestBookISBN(1, "123", new TestBook(1, "Alexandr", "Morning", "1965"));
            session0.save(isbn);
            session0.getTransaction().commit();
            session0.close();

            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();
            session1.setFlushMode(FlushMode.COMMIT);
            TestBookISBN isbn1 = session1.get(TestBookISBN.class, 1);
            //session1.lock(isbn1, LockMode.OPTIMISTIC);
            isbn1.setNumber("1234");
            session1.update(isbn1);
            TestBookISBN isbn2 = (TestBookISBN) session1.createQuery("from TestBookISBN where number=1234").uniqueResult();
            assertThat(isbn2).isNotNull();
            session1.getTransaction().commit();
            session1.close();
        } catch (Exception e) {
            System.out.println("---" + e);
            throw e;
        }
    }

    @Test (expected = org.hibernate.OptimisticLockException.class)
    public void shouldThrowAnExceptionBecauseOfVersionConflict() {
        try {
            Session session0 = sessionFactory.openSession();
            session0.beginTransaction();
            TestBook book = new TestBook(1, "Alexandr", "Morning", "1965");
            session0.save(book);
            session0.getTransaction().commit();
            session0.close();

            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();
            TestBook book1 = session1.load(TestBook.class, 1, new LockOptions(LockMode.OPTIMISTIC));

            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();
            TestBook book2 = session2.get(TestBook.class, 1);
            book2.setYear("1966");
            session2.getTransaction().commit();
            session2.close();
            
            TestBookISBN isbn1 = new TestBookISBN(1, "18649217", book1);
            session1.persist(isbn1);
            session1.getTransaction().commit();
            session1.close();
        } catch (Exception e) {
            System.out.println("---" + e);
            throw e;
        }
    }

    @Test (expected = org.hibernate.OptimisticLockException.class)
    public void shouldThrowAnExceptionBecauseOfDeletingEntityWithOptimisticLockingEnabled() {
        try {
            Session session0 = sessionFactory.openSession();
            session0.beginTransaction();
            TestBook book = new TestBook(1, "Alexandr", "Morning", "1965");
            session0.save(book);
            session0.getTransaction().commit();
            session0.close();

            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();
            TestBook book1 = session1.load(TestBook.class, 1, new LockOptions(LockMode.OPTIMISTIC));

            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();
            TestBook book2 = session2.get(TestBook.class, 1);
            //session1.lock(isbn1, LockMode.OPTIMISTIC);
            //book2.setYear("1966");
            session2.delete(book2);
            session2.getTransaction().commit();
            session2.close();

            TestBookISBN isbn1 = new TestBookISBN(1, "18649217", book1);
            session1.persist(isbn1);
            session1.getTransaction().commit();
            session1.close();
        } catch (Exception e) {
            System.out.println("---" + e);
            throw e;
        }
    }

    @Test
    public void testLazyInitialization() {

        TestBook book = new TestBook(1, "A", "T", "Y");
        TestBookISBN isbn = new TestBookISBN();
        isbn.setId(1);
        isbn.setNumber("1234");
        book.setNumber(isbn);
        isbn.setBook(book);
        Session session = sessionFactory.openSession();
        Transaction trx = session.beginTransaction();
        session.save(isbn);
        trx.commit();
        session.close();

        Session session2 = sessionFactory.openSession();
        Transaction tx2 = session2.beginTransaction();
        //TestBookISBN b2 = (TestBookISBN) session2.find(TestBookISBN.class, 1);
        TestBook b2 = (TestBook) session2.find(TestBook.class, 1);
        System.out.println(b2.getNumber());
        // at this point book on isbn instance should be a proxy.
        tx2.commit();
        session2.close();

        // if book property on isbn is a proxy, this should cause a
        // LazyInitialization exception.
        //TestBook b2 = b2.getBook();
        TestBookISBN i2 = b2.getNumber();
        System.out.println(i2);
    }

    @Test
    public void shouldThrowAnExceptionBecauseOfVersionConflictWithCustomOptimisticLocking() {
        try {
            Session session0 = sessionFactory.openSession();
            session0.beginTransaction();
            TestBook book = new TestBook(1, "Alexandr", "Morning", "1965");
            session0.save(book);
            session0.getTransaction().commit();
            session0.close();

            Session session1 = sessionFactory.openSession();
            session1.beginTransaction();
            TestBook book1 = session1.load(TestBook.class, 1, new LockOptions(LockMode.OPTIMISTIC));

            Session session2 = sessionFactory.openSession();
            session2.beginTransaction();
            TestBook book2 = session2.get(TestBook.class, 1);
            book2.setYear("1966");
            session2.getTransaction().commit();
            session2.close();

            TestBookISBN isbn1 = new TestBookISBN(1, "18649217", book1);
            session1.persist(isbn1);
            //System.out.println(((TestBook)session1.createQuery("FROM TestBook WHERE id=1 and version=1").uniqueResult()).getYear());
            //before commit
            Session session3 = sessionFactory.openSession();
            TestBook testBook2 = (TestBook)session3.createQuery("FROM TestBook WHERE id=1").uniqueResult();
            //System.out.println(testBook2.getYear() + " + version: " + testBook2.getVersion());
            session3.close();
            if (testBook2 == null || testBook2.getVersion() != book1.getVersion())
                throw new RuntimeException("Newer version was found in database");
            session1.getTransaction().commit();
            session1.close();
        } catch (Exception e) {
            System.out.println("---" + e);
            assertThat("Newer version was found in database").isEqualTo(e.getMessage());
        }
    }
}
