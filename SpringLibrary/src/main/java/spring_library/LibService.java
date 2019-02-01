package spring_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LibService {

    @Autowired
    private BookDAO bookDAO;

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    @Transactional(readOnly = false)
    public void add(Book book) {
        bookDAO.add(book);
    }

    public void delete(int bookId) {
        bookDAO.delete(bookId);
    }

    public void update(int bookId, Book updateBook) {
        bookDAO.update(bookId, updateBook);
    }

    public List<Book> search(Book searchBook) {
        return bookDAO.search(searchBook);
    }

    public List<Book> list() {
        return bookDAO.list();
    }
}
