package lib.hibernate_tests;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book_genres")
public class TestBookGenres implements Serializable {

    private int id;
    private String genre;

    private Set<TestBook> books = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookGenre")
    public Set<TestBook> getBooks() {
        return this.books;
    }

    public void setBooks(Set<TestBook> books) {
        this.books = books;
    }

    public void addBook(TestBook book) {
        //book.setBookGenre(this);
        this.books.add(book);
    }

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "genre", nullable = true, length = 128)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
