package spring_xml_library;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.List;

public class BookDAO implements EntityDAO {

    
    private SessionFactory sessionFactory;

    Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Book addBook) {
        Integer count = (Integer) getSession().createQuery("select max(id) from Book").uniqueResult();
        if (count == null)
            count = 0;
        addBook.setId(count + 1);
        getSession().save(addBook);
    }

    @Override
    public void delete(int bookId) {
        Book book = getSession().get(Book.class, bookId);

        getSession().remove(book);
    }

    @Override
    public void update(int bookId, Book updateBook) {
        Book book = getSession().get(Book.class, bookId);

        if (updateBook.getAuthor() != null)
            book.setAuthor(updateBook.getAuthor());
        if (updateBook.getTitle() != null)
            book.setTitle(updateBook.getTitle());
        if (updateBook.getYear() != null)
            book.setYear(updateBook.getYear());

        getSession().save(book);
    }

    @Override
    public List<Book> search(Book searchBook) {
        Example example = Example.create(searchBook);
        Criteria criteria = getSession().createCriteria(Book.class).add(example);

        List<Book> booksList = criteria.list();

        return booksList;
    }

    @Override
    public List<Book> list() {
        List<Book> booksList = getSession().createQuery("from Book").list();

        return booksList;
    }
}
