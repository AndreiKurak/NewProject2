package lib.library_entities;

import lib.PropertyValuesGetter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

import java.util.List;

public class BooksFromDB implements Books {

    private SessionFactory sessionFactory;

    public BooksFromDB() {
        sessionFactory = new Configuration().
                addPackage("lib.library_entities").
                addProperties(new PropertyValuesGetter().getProp()).
                addAnnotatedClass(Book.class).buildSessionFactory();
    }

    public void add(Book addBook) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Integer count = (Integer) session.createQuery("select max(id) from Book").uniqueResult();
        addBook.setId(count + 1);

        session.save(addBook);
        session.getTransaction().commit();

        session.close();
    }

    public void delete(int bookId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Book book = session.get(Book.class, bookId);

        session.remove(book);
        session.getTransaction().commit();

        session.close();
    }

    public void update(int bookId, Book updateBook) {
        Session session = sessionFactory.openSession();
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

        session.close();
    }

    @Override
    public List<Book> search(Book book) {
        Session session = sessionFactory.openSession();

        Example example = Example.create(book);
        Criteria criteria = session.createCriteria(Book.class).add(example);
        
        List<Book> booksList = criteria.list();

        session.close();
        return booksList;
    }

    @Override
    public List<Book> list() {
        Session session = sessionFactory.openSession();

        List<Book> booksList = session.createQuery("from Book").list();

        session.close();
        return booksList;
    }
}
