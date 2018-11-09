package lib.hibernate_tests;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "library")
public class TestBook {

    private Integer id;
    private String author;
    private String title;
    private String year;

    private TestBookGenres bookGenre;
    @ManyToOne
    @JoinColumn(name = "book_genre", referencedColumnName = "genre")
    public TestBookGenres getBookGenre() {
        return this.bookGenre;
    }

    public void setBookGenre(TestBookGenres bookGenre) {
        this.bookGenre = bookGenre;
    }

    public TestBook() { }

    public TestBook(String author, String title, String year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public TestBook(Integer id, String author, String title, String year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    @Id //@Basic
    @Column(name = "book_id", nullable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "author", nullable = true, length = 128)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 128)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "year", nullable = true, length = 128)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestBook that = (TestBook) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(author, that.author) &&
                Objects.equals(title, that.title) &&
                Objects.equals(year, that.year);
    }

    @Override
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
    }
}
