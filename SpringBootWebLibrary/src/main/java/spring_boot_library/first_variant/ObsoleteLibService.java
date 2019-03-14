package spring_boot_library.first_variant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_boot_library.Book;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ObsoleteLibService {

    @Autowired
    private ObsoleteBookDAO obsoleteBookDAO;

    public ObsoleteBookDAO getObsoleteBookDAO() {
        return obsoleteBookDAO;
    }

    @Transactional(readOnly = false)
    public void add(Book book) {
        obsoleteBookDAO.add(book);
    }

    @Transactional(readOnly = false)
    public void delete(int bookId) {
        obsoleteBookDAO.delete(bookId);
    }

    @Transactional(readOnly = false)
    public void update(int bookId, Book updateBook) {
        obsoleteBookDAO.update(bookId, updateBook);
    }

    public List<Book> search(Book searchBook) {
        return obsoleteBookDAO.search(searchBook);
    }

    public List<Book> list() {
        return obsoleteBookDAO.list();
    }
}
