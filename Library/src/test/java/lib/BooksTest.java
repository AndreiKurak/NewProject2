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

    @Test
    public void shouldUpdateBook(){
        Books books = new Books();
        books.add(new Book("Henry", "Destiny", "1982"));
        books.update(0, new Book(null, "Weakness", "1981"));
        assertThat(books.list().get(0).getAuthor()).isEqualTo("Henry");
        assertThat(books.list().get(0).getTitle()).isEqualTo("Weakness");
    }

    @Test
    public void shouldFindRequiredBook(){
        Books books = new Books();
        books.add(new Book("Henry", "Destiny", "1982"));
        books.add(new Book("Henry", "Power", "1982"));
        assertThat(books.search(new Book("Henry", "Power", "1982")).get(0).getTitle()).isEqualTo("Power");
    }

    @Test
    public void shouldNotFindRequiredBookAndReturnNull(){
        Books books = new Books();
        books.add(new Book("Henry", "Destiny", "1982"));
        books.add(new Book("Henry", "Power", "1982"));
        assertThat(books.search(new Book(null, null, null))).isNull();
    }
}
