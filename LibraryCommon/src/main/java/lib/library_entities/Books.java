package lib.library_entities;

import java.io.Serializable;
import java.util.List;

public interface Books extends Serializable {

    void add(Book book);

    void delete(int id);

    void update(int id, Book updateBook);

    List<Book> search(Book searchBook);

    List<Book> list();
}
