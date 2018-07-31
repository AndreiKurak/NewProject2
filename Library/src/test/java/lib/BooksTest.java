package lib;

import lib.connectors.Books;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BooksTest {

    @Test
    public void shouldAddNewBook(){
        Books books = new Books();
        books.add(new Book());
        assertThat(books.list().size()).isNotZero();
    }

    @Test
    public void shouldDeleteBookFromList(){
        Books books = new Books();
        books.add(new Book());
        books.delete(0);
        assertThat(books.list().size()).isZero();
    }
}
