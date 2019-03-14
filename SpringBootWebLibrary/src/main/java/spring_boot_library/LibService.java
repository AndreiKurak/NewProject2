package spring_boot_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LibService implements EntityService {

    @Autowired
    BookDAO bookDAO;

    @Transactional(readOnly = false)
    public Book add(Book book){
        return bookDAO.save(book);
    }

    @Transactional(readOnly = false)
    public void delete(int id){
        bookDAO.delete(id);
    }

    public List<Book> search(String author, String title, String year) {
        return bookDAO.findAllByAuthorContainsAndTitleContainsAndYearContains(author, title, year);
    }

    @Transactional(readOnly = false)
    public Book update(Book book) {
        return bookDAO.save(book);
    }

    public List<Book> list() {
        return bookDAO.findAll();
    }

    public Book findById(int id) {
        return bookDAO.findOne(id);
    }
}
