package lib.hibernate_tests;

import javax.persistence.*;

@Entity
@Table(name = "book_isbn")
public class TestBookISBN {

    private int id;
    private String genre;

    private TestBook book = new TestBook();
    @OneToOne(cascade = {CascadeType.ALL/*, CascadeType.MERGE*/}, mappedBy = "number")
    public TestBook getBook() {
        return this.book;
    }

    public void setBook(TestBook book) {
        this.book = book;
    }

    @Id
    /*@GeneratedValue(strategy = GenerationType.AUTO)*/
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "isbn", nullable = true, length = 128)
    public String getNumber() {
        return genre;
    }

    public void setNumber(String genre) {
        this.genre = genre;
    }
}
