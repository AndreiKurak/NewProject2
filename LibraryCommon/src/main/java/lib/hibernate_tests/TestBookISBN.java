package lib.hibernate_tests;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "book_isbn")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate(true)
public class TestBookISBN implements Serializable {

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

    public TestBookISBN() { }

    public TestBookISBN(int id, String genre, TestBook book) {
        this.id = id;
        this.genre = genre;
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
