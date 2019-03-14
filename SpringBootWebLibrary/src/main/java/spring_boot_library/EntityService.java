package spring_boot_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_boot_library.Book;

import java.util.List;
import java.util.Optional;

public interface EntityService {

    Book add(Book book);

    void delete(int id);

    Book update(Book updateBook);

    List<Book> search(String author, String title, String year);/*(Book searchBook);*/

    List<Book> list();
}
