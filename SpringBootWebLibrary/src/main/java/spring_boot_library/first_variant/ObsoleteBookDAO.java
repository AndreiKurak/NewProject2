package spring_boot_library.first_variant;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring_boot_library.Book;

import java.util.List;

@Repository
public class ObsoleteBookDAO implements ObsoleteEntityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Book addBook) {
        /*Integer count = (Integer) getSession().createQuery("select max(id) from Book").uniqueResult();
        if (count != null)
            addBook.setId(count + 1);
        else
            addBook.setId(1);*/
        getSession().save(addBook);
    }

    @Override
    public void delete(int bookId) {
        Book book = getSession().get(Book.class, bookId);

        getSession().delete(book);
    }

    @Override
    public void update(int bookId, Book updateBook) {
        Book book = getSession().get(Book.class, bookId);

        if (!updateBook.getAuthor().equals(""))
            book.setAuthor(updateBook.getAuthor());
        if (!updateBook.getTitle().equals(""))
            book.setTitle(updateBook.getTitle());
        if (!updateBook.getYear().equals(""))
            book.setYear(updateBook.getYear());

        getSession().save(book);
    }

    @Override
    public List<Book> search(Book searchBook) {
        Example example = Example.create(searchBook);
        if (searchBook.getAuthor().equals(""))
            searchBook.setAuthor(null);
        if (searchBook.getTitle().equals(""))
            searchBook.setTitle(null);
        if (searchBook.getYear().equals(""))
            searchBook.setYear(null);

        Criteria criteria = getSession().createCriteria(Book.class).add(example);

        List<Book> booksList = criteria.list();

        return booksList;
    }

    @Override
    public List<Book> list() {
        return getSession().createQuery("from Book").list();
    }
}
