package spring_boot_library;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "library")
@Getter
@Setter
@EqualsAndHashCode
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = true)
    private Integer id;

    @Column(name = "author", nullable = true, length = 128)
    private String author;

    @Column(name = "title", nullable = true, length = 128)
    private String title;

    @Column(name = "year", nullable = true, length = 128)
    private String year;

    public Book() { }

    public Book(String author, String title, String year) {
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public Book(Integer id, String author, String title, String year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
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
