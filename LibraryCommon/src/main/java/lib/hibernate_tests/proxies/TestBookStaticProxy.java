package lib.hibernate_tests.proxies;

import lib.hibernate_tests.TestBook;
import lib.hibernate_tests.TestBookGenres;
import lib.hibernate_tests.TestBookISBN;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Objects;

public class TestBookStaticProxy extends TestBook {

    private Integer id;
    private TestBook bookEntity;  //real book
    private Session session; //current session

    @Override
    public TestBookISBN getNumber() {
        if (bookEntity == null)  //also check in cash, if the entity of book is initialized already
            bookEntity = session.get(TestBook.class, id);
                  
        return bookEntity.getNumber();
    }

    @Override
    public void setNumber(TestBookISBN number) {
        bookEntity.setNumber(number);
    }

    @Override
    public TestBookGenres getBookGenre() {
        if (bookEntity == null)
            bookEntity = session.get(TestBook.class, id);
        return bookEntity.getBookGenre();
    }

    @Override
    public void setBookGenre(TestBookGenres bookGenre) {
        bookEntity.setBookGenre(bookGenre);
    }

    public TestBookStaticProxy(Integer id, Session session) {
        this.id = id;
        this.session = session;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getAuthor() {
        if (bookEntity == null)
            bookEntity = session.get(TestBook.class, id);
        return bookEntity.getAuthor();
    }

    @Override
    public void setAuthor(String author) { bookEntity.setAuthor(author); }

    @Override
    public String getTitle() {
        if (bookEntity == null)
            bookEntity = session.get(TestBook.class, id);
        return bookEntity.getTitle();
    }

    @Override
    public void setTitle(String title) {
        bookEntity.setTitle(title);
    }

    @Override
    public String getYear() {
        if (bookEntity == null)
            bookEntity = session.get(TestBook.class, id);
        return bookEntity.getYear();
    }

    @Override
    public void setYear(String year) {
        bookEntity.setYear(year);
    }

    @Override
    public long getVersion() {
        if (bookEntity == null)
            bookEntity = session.get(TestBook.class, id);
        return bookEntity.getVersion();
    }

    @Override
    public void setVersion(long version) {
        bookEntity.setVersion(version);
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBook that = (TestBook) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(author, that.author) &&
                Objects.equals(title, that.title) &&
                Objects.equals(year, that.year);
    }*/

    /*@Override
    public int hashCode() {
        return Objects.hash(id, author, title, year);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                '}';
    }*/
}

