package spring_boot_library.first_variant;

import spring_boot_library.Book;

import java.io.Serializable;
import java.util.List;

public interface ObsoleteEntityDAO extends Serializable {

    void add(Book book);

    void delete(int id);

    void update(int id, Book updateBook);

    List<Book> search(Book searchBook);

    List<Book> list();
}
