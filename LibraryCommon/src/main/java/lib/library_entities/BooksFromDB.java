package lib.library_entities;

import lib.connectors.SessionFactoryGetter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import java.util.List;

public class BooksFromDB implements Books {

    private SessionFactory sessionFactory;

    public BooksFromDB() {
        sessionFactory = SessionFactoryGetter.getSessionFactory();
    }

    public void add(Book addBook) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Integer count = (Integer) session.createQuery("select max(id) from Book").uniqueResult();
            addBook.setId(count + 1);

            session.save(addBook);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(int bookId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Book book = session.get(Book.class, bookId);

            session.remove(book);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void update(int bookId, Book updateBook) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Book book = session.get(Book.class, bookId);

            if (updateBook.getAuthor() != null)
                book.setAuthor(updateBook.getAuthor());
            if (updateBook.getTitle() != null)
                book.setTitle(updateBook.getTitle());
            if (updateBook.getYear() != null)
                book.setYear(updateBook.getYear());

            session.save(book);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Book> search(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Example example = Example.create(book);
            Criteria criteria = session.createCriteria(Book.class).add(example);

            List<Book> booksList = criteria.list();

            return booksList;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Book> list() {
        try (Session session = sessionFactory.openSession()) {
            List<Book> booksList = session.createQuery("from Book").list();

            return booksList;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
